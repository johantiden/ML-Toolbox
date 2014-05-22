package se.jtiden.sudoku.solver;

import se.jtiden.sudoku.domain.Board;
import se.jtiden.sudoku.struct.Consumer;
import se.jtiden.sudoku.struct.IntStream;

public class SudokuSolverFactory {

    public MultiSolver newSudokuSolver(Board board) {
        final MultiSolverBuilder builder = new MultiSolverBuilder().withBoard(board);

        builder
                .withSolver(new FailIfNodeHasZeroCandidates())
                .withSolver(new RemoveCandidatesOfSolvedNeighbors())
                .withSolver(new NakedSingle())
                .withSolver(new HiddenSingle());

        IntStream.range(1, board.getOrder() * board.getOrder() + 1).forEach(new Consumer<Integer>() {
            @Override
            public void apply(final Integer i) {
                builder.withSolver(new LockedCandidates(i));
            }
        });

        return builder.build();
    }


}
