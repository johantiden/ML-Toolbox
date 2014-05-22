package se.jtiden.sudoku.trainingdata;

import se.jtiden.sudoku.domain.Board;

public abstract class SudokuTrainingDataAbs implements SudokuTrainingData {
    private Board board;
    private final Difficulty difficulty;
    private final String name;

    public SudokuTrainingDataAbs(Difficulty difficulty, String name) {
        this.difficulty = difficulty;
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }


    protected void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public abstract void assertSolved();

    public String getName() {
        return name;
    }

    @Override
    public boolean isIgnore() {
        return false;
    }

    @Override
    public String toString() {
        return difficulty + ": " + name;
    }
}
