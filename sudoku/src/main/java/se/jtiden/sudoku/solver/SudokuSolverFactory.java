package main.java.se.jtiden.sudoku.solver;

import main.java.se.jtiden.sudoku.domain.Board;

import java.util.stream.IntStream;

public class SudokuSolverFactory {

    public MultiSolver newSudokuSolver(Board board) {
        MultiSolverBuilder builder = new MultiSolverBuilder().withBoard(board);

        builder
                .withSolver(new FailIfNodeHasZeroCandidates())
                .withSolver(new RemoveCandidatesOfSolvedNeighbors())
                .withSolver(new NakedSingle())
                .withSolver(new HiddenSingle());

        IntStream.range(1, board.getOrder() * board.getOrder() + 1).forEach(i -> {
            builder.withSolver(new LockedCandidates(i));
        });

        return builder.build();
    }


}
