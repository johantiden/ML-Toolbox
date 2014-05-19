package test.java.se.jtiden.sudoku.solver;

import main.java.se.jtiden.sudoku.domain.Board;
import main.java.se.jtiden.sudoku.solver.LockedCandidates;
import main.java.se.jtiden.sudoku.solver.Solver;
import main.java.se.jtiden.sudoku.struct.Coordinate;
import org.junit.Test;
import test.java.se.jtiden.sudoku.BoardFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LockedCandidatesTest {

    @Test
    public void test() {
        Board board = BoardFactory.parse(2, new String[]{
                "xx xx",
                "xx xx",

                "xx xx",
                "xx xx",
        });
        board.removeCandidate(new Coordinate(1, 1), 1);
        board.removeCandidate(new Coordinate(1, 2), 1);

        LockedCandidates lockedCandidates = new LockedCandidates(1);
        lockedCandidates.setBoard(board);
        Solver solver = lockedCandidates.build();

        assertEquals(4, board.numCandidatesLeft(new Coordinate(2, 3)));
        boolean success = solver.trySolve();
        assertTrue("trySolve must return true when changing the board", success);
        assertEquals(3, board.numCandidatesLeft(new Coordinate(2, 3)));
    }
}
