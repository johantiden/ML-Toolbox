package test.java.se.jtiden.sudoku.trainingdata;

import main.java.se.jtiden.sudoku.domain.Board;

public interface SudokuTrainingData {
    Board getBoard();

    void assertSolved();

    Difficulty getDifficulty();

    String getName();
}
