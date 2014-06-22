package se.jtiden.sudoku.sudokuswing;

import se.jtiden.sudoku.domain.Board;
import se.jtiden.sudoku.domain.Node;

import java.awt.*;

public class SudokuPainterHighlightCandidates extends SudokuPainter {
    private final int highLightedCandidates;

    public SudokuPainterHighlightCandidates(Board board, int highLightedCandidates, int width, int height) {
        super(board, width, height);
        this.highLightedCandidates = highLightedCandidates;
    }

    @Override
    protected Color getNodeBackgroundColor(Node node) {
        if (node.isSolved() && node.getValue() == highLightedCandidates) {
            return Color.YELLOW;
        } else {
            if (node.candidatesContains(highLightedCandidates)) {
                return Color.PINK;
            } else {
                return Color.WHITE;
            }
        }
    }
}
