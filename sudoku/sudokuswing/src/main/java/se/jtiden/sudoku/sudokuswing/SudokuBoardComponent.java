package se.jtiden.sudoku.sudokuswing;

import se.jtiden.sudoku.domain.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SudokuBoardComponent extends JPanel {

    private static final long serialVersionUID = 1L;
    private final Board board;
    private final SudokuPainterFactory sudokuPainterFactory = new SudokuPainterFactory();

    public SudokuBoardComponent(final Board board) {
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        int bufferSize = mySize()*4;

        if (bufferSize > 0) {
            Image backBuffer = new BufferedImage(bufferSize, bufferSize, BufferedImage.TYPE_INT_RGB);

            sudokuPainterFactory.newPainter(board, bufferSize, bufferSize).paint(backBuffer.getGraphics());
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(backBuffer, 0, 0, mySize(), mySize(), null);
        }
    }

    private int mySize() {
        return this.getWidth();
    }


    public void setHighlightedNumber(int value) {
        this.sudokuPainterFactory.setHighlightedCandidates(value);
    }
}
