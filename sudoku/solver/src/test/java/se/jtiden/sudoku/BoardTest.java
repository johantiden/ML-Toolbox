package se.jtiden.sudoku;

import se.jtiden.sudoku.domain.Board;
import se.jtiden.sudoku.domain.BoardImpl;
import se.jtiden.sudoku.struct.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    public static final Coordinate COORDINATE = new Coordinate(1, 1);
    private Board board;

    @Test
    public void testCandidatesShouldBeFullWhenNotTouched_1() {
        board = new BoardImpl(1);
        assertEquals(1, board.numCandidatesLeft(COORDINATE));
    }

    @Test
    public void testCandidatesShouldBeFullWhenNotTouched_2() {
        board = new BoardImpl(2);
        assertEquals(4, board.numCandidatesLeft(COORDINATE));
    }

    @Test
    public void testEraseCandidate() {
        board = new BoardImpl(2);

        board.removeCandidate(COORDINATE, 1);

        assertEquals(3, board.numCandidatesLeft(COORDINATE));
    }


    @Test
    public void testSolve() {
        board = new BoardImpl(2);

        board.solveNode(COORDINATE, 1);

        assertEquals(0, board.numCandidatesLeft(COORDINATE));
        assertEquals(1, board.getValue(COORDINATE));
    }
}
