package test.java.se.jtiden.sudoku;

import main.java.se.jtiden.sudoku.Board;
import main.java.se.jtiden.sudoku.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    public static final Coordinate COORDINATE = new Coordinate(1, 1);
    private Board board;

    @Test
    public void testHard(){
        board = new Board(1);
        board.setHardDigit(COORDINATE, 3);
        assertEquals(true, board.isHard(COORDINATE));
    }

    @Test
    public void testNotHard(){
        board = new Board(1);
        assertEquals(false, board.isHard(COORDINATE));
    }

    @Test
    public void testCandidatesShouldBeFullWhenNotTouched_1() {
        board = new Board(1);
        assertEquals(1, board.numCandidatesLeft(COORDINATE));
    }

    @Test
    public void testCandidatesShouldBeFullWhenNotTouched_2() {
        board = new Board(2);
        assertEquals(4, board.numCandidatesLeft(COORDINATE));
    }

    @Test
    public void testEraseCandidate() {
        board = new Board(2);

        board.removeCandidate(COORDINATE, 1);

        assertEquals(3, board.numCandidatesLeft(COORDINATE));
    }


    @Test
    public void testSolve() {
        board = new Board(2);

        board.solveNode(COORDINATE, 1);

        assertEquals(0, board.numCandidatesLeft(COORDINATE));
        assertEquals(1, board.getValue(COORDINATE));
    }
}
