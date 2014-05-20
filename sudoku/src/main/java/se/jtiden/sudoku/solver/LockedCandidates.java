package main.java.se.jtiden.sudoku.solver;

public class LockedCandidates extends SolverBuilder {

    private final int value;

    public LockedCandidates(int value) {
        this.value = value;
    }

    @Override
    public Solver build() {
        return new Solver() {
            @Override
            public boolean trySolve() {
                boolean success = lockedCandidatesInRows();
                return success || lockedCandidatesInColumns();
            }

            private boolean lockedCandidatesInRows() {
                for (SudokuNodeSet subSet : board.getAllRowSubGroups()) {
                    if (subSet.containsCandidate(value)) {
                        SudokuNodeSet complementInGroup = subSet.getComplementInBox();
                        SudokuNodeSet complementInRow = subSet.getComplementInRow();
                        if (!complementInGroup.containsCandidate(value) &&
                                complementInRow.containsCandidate(value)) {
                            System.out.println("[SOLVER] "+value+" Locked candidates row (pointing) in subset " + subSet + ".");
                            complementInRow.removeCandidate(value);
                            return true;
                        } else if (!complementInRow.containsCandidate(value) &&
                                complementInGroup.containsCandidate(value)) {
                            System.out.println("[SOLVER] "+value+" Locked candidates row (claiming) in subset " + subSet + ".");
                            complementInGroup.removeCandidate(value);
                            return true;
                        }
                    }
                }
                return false;
            }

            private boolean lockedCandidatesInColumns() {
                for (SudokuNodeSet subSet : board.getAllColumnSubGroups()) {
                    if (subSet.containsCandidate(value)) {
                        SudokuNodeSet complementInGroup = subSet.getComplementInBox();
                        SudokuNodeSet complementInColumn = subSet.getComplementInColumn();
                        if (!complementInGroup.containsCandidate(value) &&
                                complementInColumn.containsCandidate(value)) {
                            System.out.println("[SOLVER] "+value+" Locked candidates column (pointing) in subset " + subSet + ".");
                            complementInColumn.removeCandidate(value);
                            return true;
                        } else if (!complementInColumn.containsCandidate(value) &&
                                complementInGroup.containsCandidate(value)) {
                            System.out.println("[SOLVER] "+value+" Locked candidates column (claiming) in subset " + subSet + ".");
                            complementInGroup.removeCandidate(value);
                            return true;
                        }
                    }
                }
                return false;
            }
        };
    }
}
