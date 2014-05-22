package se.jtiden.sudoku.solver;

import se.jtiden.sudoku.domain.UnsolvedNode;
import se.jtiden.sudoku.domain.Node;

public class RemoveCandidatesOfSolvedNeighbors extends SolverBuilder {
    @Override
    public Solver build() {
        return new Solver() {
            @Override
            public boolean trySolve() {
                for (UnsolvedNode unsolvedNode : board.getUnsolvedNodes()) {
                    for (Node neighbor : board.getNeighborsFor(unsolvedNode)) {
                        if (neighbor.isSolved()) {
                            int neighborValue = neighbor.getValue();
                            if (unsolvedNode.getCandidates().contains(neighborValue)) {
                                //System.out.println("[SOLVER] " + unsolvedNode.getCoordinate() + " Removing candidate " + neighborValue  + " because " + neighbor.getCoordinate() + " is " + neighborValue + ".");
                                unsolvedNode.removeCandidate(neighborValue);
                                return true;
                            }
                        }
                    }
                }

                return false;
            }
        };
    }
}
