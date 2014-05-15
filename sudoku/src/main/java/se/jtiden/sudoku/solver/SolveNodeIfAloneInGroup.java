package main.java.se.jtiden.sudoku.solver;

import main.java.se.jtiden.sudoku.domain.UnsolvedNode;

class SolveNodeIfAloneInGroup extends SolverBuilder {
    @Override
    public Solver build() {
        return new Solver() {
            @Override
            public boolean trySolve() {
                boolean success = solveIfCandidateIsAloneInRow();
                if (success) {
                    return true;
                }

                success = solveIfCandidateIsAloneInColumn();
                if (success) {
                    return true;
                }

                success = solveIfCandidateIsAloneInBox();
                return success;
            }

            private boolean solveIfCandidateIsAloneInBox() {
                for (UnsolvedNode unsolvedNode : board.getUnsolvedNodes()) {
                    for (Integer value : unsolvedNode.getCandidates()) {
                        if (board.countCandidatesInBoxFor(unsolvedNode.getCoordinate(), value) == 1) {
                            System.out.println("[SOLVER] " + unsolvedNode.getCoordinate() + " Solving as " + value + " because it is the only candidate in the box.");
                            board.solveNode(unsolvedNode.getCoordinate(), value);
                            return true;
                        }
                    }
                }
                return false;
            }

            private boolean solveIfCandidateIsAloneInColumn() {
                for (UnsolvedNode unsolvedNode : board.getUnsolvedNodes()) {
                    for (Integer value : unsolvedNode.getCandidates()) {
                        if (board.countCandidatesInColumnFor(unsolvedNode.getCoordinate(), value) == 1) {
                            System.out.println("[SOLVER] " + unsolvedNode.getCoordinate() + " Solving as " + value + " because it is the only candidate in the column.");
                            board.solveNode(unsolvedNode.getCoordinate(), value);
                            return true;
                        }
                    }
                }
                return false;
            }

            private boolean solveIfCandidateIsAloneInRow() {
                for (UnsolvedNode unsolvedNode : board.getUnsolvedNodes()) {
                    for (Integer value : unsolvedNode.getCandidates()) {
                        if (board.countCandidatesInRowFor(unsolvedNode.getCoordinate(), value) == 1) {
                            System.out.println("[SOLVER] " + unsolvedNode.getCoordinate() + " Solving as " + value + " because it is the only candidate in the row.");
                            board.solveNode(unsolvedNode.getCoordinate(), value);
                            return true;
                        }
                    }
                }
                return false;
            }
        };
    }


}
