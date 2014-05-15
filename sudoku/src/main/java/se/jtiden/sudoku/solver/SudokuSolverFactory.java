package main.java.se.jtiden.sudoku.solver;

import main.java.se.jtiden.sudoku.domain.Board;
import main.java.se.jtiden.sudoku.solver.*;

public class SudokuSolverFactory {

    public MultiSolver newSudokuSolver(Board board) {
        MultiSolverBuilder builder = new MultiSolverBuilder().withBoard(board);

        return builder
                .withSolver(new FailIfNodeHasZeroCandidates())
                .withSolver(new RemoveCandidatesOfSolvedNeighbors())
                .withSolver(new SolveNodeIfOnlyOneCandidate())
                .withSolver(new SolveNodeIfAloneInGroup())
                .withSolver(new LockedCandidates())
                .build();
    }


}
