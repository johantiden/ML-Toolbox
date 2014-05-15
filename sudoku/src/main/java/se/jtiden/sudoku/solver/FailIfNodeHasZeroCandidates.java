package main.java.se.jtiden.sudoku.solver;

import main.java.se.jtiden.sudoku.domain.UnsolvedNode;

class FailIfNodeHasZeroCandidates extends SolverBuilder {
    @Override
    public Solver build() {
        return new Solver() {
            @Override
            public boolean trySolve() {
                for (UnsolvedNode node : board.getUnsolvedNodes()) {
                    if (node.numCandidatesLeft() == 0) {
                        throw new IllegalStateException(node.getCoordinate() + " has no candidates left. Either the sudoku has no solution, or there is an error in the solver");
                    }
                }
                return false;
            }
        };
    }
}
