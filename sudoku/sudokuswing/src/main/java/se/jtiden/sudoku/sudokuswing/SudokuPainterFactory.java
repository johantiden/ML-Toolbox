package se.jtiden.sudoku.sudokuswing;

import se.jtiden.sudoku.domain.Board;

public class SudokuPainterFactory {

    public SudokuPainter newPainter(Board board, int width, int height) {
        return new SudokuPainter(board, width, height);
    }
}
