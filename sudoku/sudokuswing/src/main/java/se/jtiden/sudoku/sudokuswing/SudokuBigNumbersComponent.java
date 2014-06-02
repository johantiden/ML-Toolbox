package se.jtiden.sudoku.sudokuswing;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import se.jtiden.sudoku.domain.Board;

public class SudokuBigNumbersComponent extends JPanel {

    private static final long serialVersionUID = 1L;
    private final Board board;


    public SudokuBigNumbersComponent(final Board board) {
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        int bufferSize = mySize()*2;

        if (bufferSize > 0) {
            Image backBuffer = new BufferedImage(bufferSize, bufferSize, BufferedImage.TYPE_INT_RGB);

            SudokuPainter.createSudokuPainter(board, bufferSize, bufferSize).paint(backBuffer.getGraphics());
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(backBuffer, 0, 0, mySize(), mySize(), null);
        }
    }

    private int mySize() {
        return this.getWidth();
    }
}
