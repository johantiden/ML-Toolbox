package se.jtiden.sudoku.solver;

import se.jtiden.sudoku.domain.UnsolvedNode;
import se.jtiden.sudoku.struct.Coordinate;

class NakedSingle extends SolverBuilder {
    @Override
    public Solver build() {
        return new Solver() {
            @Override
            public boolean trySolve() {
                for (UnsolvedNode unsolvedNode : board.getUnsolvedNodes()) {
                    if (unsolvedNode.numCandidatesLeft() == 1) {
                        int value = unsolvedNode.getCandidates().iterator().next();
                        Coordinate coordinate = unsolvedNode.getCoordinate();
                        System.out.println("[SOLVER] " + coordinate + " Solving as " + value + " because it has only one candidate left.");
                        board.solveNode(coordinate, value);
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
