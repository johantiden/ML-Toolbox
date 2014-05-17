package test.java.se.jtiden.sudoku;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardFactoryTest {
    @Test
    public void testCharToInt() {
        assertEquals(0, BoardFactory.charToInt('0'));
        assertEquals(1, BoardFactory.charToInt('1'));
        assertEquals(2, BoardFactory.charToInt('2'));
        assertEquals(3, BoardFactory.charToInt('3'));
        assertEquals(4, BoardFactory.charToInt('4'));
        assertEquals(5, BoardFactory.charToInt('5'));
        assertEquals(6, BoardFactory.charToInt('6'));
        assertEquals(7, BoardFactory.charToInt('7'));
        assertEquals(8, BoardFactory.charToInt('8'));
        assertEquals(9, BoardFactory.charToInt('9'));
    }

}
