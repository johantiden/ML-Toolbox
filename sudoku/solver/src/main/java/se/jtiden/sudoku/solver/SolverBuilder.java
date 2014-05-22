package se.jtiden.sudoku.solver;

import se.jtiden.sudoku.domain.Board;
import se.jtiden.sudoku.solver.Solver;

public abstract class SolverBuilder {
    protected Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public abstract Solver build();
}
