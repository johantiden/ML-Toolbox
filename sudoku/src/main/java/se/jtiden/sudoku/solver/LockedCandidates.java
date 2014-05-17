package main.java.se.jtiden.sudoku.solver;

public class LockedCandidates extends SolverBuilder {
    @Override
    public Solver build() {
        return new Solver() {
            @Override
            public boolean trySolve() {
                boolean success = lockedCandidatesInRows();
                return success || lockedCandidatesInColumns();

            }

            private boolean lockedCandidatesInRows() {
                /*for (int y = 0; y < board.getOrder() * board.getOrder(); ++y) {

                } */
                return false;
            }

            private boolean lockedCandidatesInColumns() {
                return false;  //To change body of created methods use File | Settings | File Templates.
            }


        };
    }
}
