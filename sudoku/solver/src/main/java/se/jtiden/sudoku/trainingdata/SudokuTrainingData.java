package se.jtiden.sudoku.trainingdata;

import se.jtiden.sudoku.domain.Board;

public interface SudokuTrainingData {
    Board getBoard();

    void assertSolved();

    Difficulty getDifficulty();

    String getName();

    boolean isIgnore();
}
