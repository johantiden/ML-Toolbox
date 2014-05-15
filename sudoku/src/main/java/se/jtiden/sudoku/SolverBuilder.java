package main.java.se.jtiden.sudoku;

public abstract class SolverBuilder {
    protected Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public abstract Solver build();
}
