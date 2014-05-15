package main.java.se.jtiden.sudoku;

import java.util.ArrayList;
import java.util.Collection;

public class MultiSolverBuilder {

    private Board board;
    private Collection<SolverBuilder> solverBuilders = new ArrayList<SolverBuilder>();

    public MultiSolverBuilder withBoard(Board board) {
        this.board = board;
        return this;
    }

    public MultiSolverBuilder withSolver(SolverBuilder solverBuilder) {
        solverBuilders.add(solverBuilder);
        return this;
    }

    public MultiSolver build() {
        Collection<Solver> solvers = new ArrayList<Solver>();

        for (SolverBuilder solverBuilder : solverBuilders) {
            solverBuilder.setBoard(board);
            solvers.add(solverBuilder.build());
        }

        return new MultiSolverImpl(solvers);
    }
}
