package test.java.se.jtiden.sudoku.trainingdata;

import main.java.se.jtiden.sudoku.domain.Board;

public class SudokuTrainingDataAbs {
    protected Board board;
    private Difficulty difficulty;
    private String name;

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

    public String getName() {
        return name;
    }
}
