package main.java.se.jtiden.sudoku.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardImplTest {

    @Test
    public void testGroupId3() {
        BoardImpl board = new BoardImpl(3);
        assertEquals(0, board.groupId(1));
        assertEquals(0, board.groupId(2));
        assertEquals(0, board.groupId(3));
        assertEquals(1, board.groupId(4));
        assertEquals(1, board.groupId(5));
        assertEquals(1, board.groupId(6));
        assertEquals(2, board.groupId(7));
        assertEquals(2, board.groupId(8));
        assertEquals(2, board.groupId(9));
    }

    @Test
    public void testGroupId2() {
        BoardImpl board = new BoardImpl(2);
        assertEquals(0, board.groupId(1));
        assertEquals(0, board.groupId(2));
        assertEquals(1, board.groupId(3));
        assertEquals(1, board.groupId(4));
    }
}
