package se.jtiden.sudoku.sudokuswing;

import se.jtiden.sudoku.domain.Board;

public class SudokuPainterFactory {

    private int highLightedCandidates;
    private PaintMode paintMode = PaintMode.DEFAULT;

    public SudokuPainter newPainter(Board board, int width, int height) {
        switch (paintMode) {
            case HIGHLIGHT_CANDIDATES:
                return new SudokuPainterHighlightCandidates(board, highLightedCandidates, width, height);
            default:
                return new SudokuPainter(board, width, height);
        }
    }

    public void setHighlightedCandidates(int value) {
        this.highLightedCandidates = value;
        this.paintMode = PaintMode.HIGHLIGHT_CANDIDATES;
    }
}
