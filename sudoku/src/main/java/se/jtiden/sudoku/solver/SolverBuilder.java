package main.java.se.jtiden.sudoku.solver;

import main.java.se.jtiden.sudoku.domain.Board;
import main.java.se.jtiden.sudoku.solver.Solver;

public abstract class SolverBuilder {
    protected Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public abstract Solver build();
}
