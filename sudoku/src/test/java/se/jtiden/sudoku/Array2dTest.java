package test.java.se.jtiden.sudoku;

import main.java.se.jtiden.sudoku.struct.Array2d;
import main.java.se.jtiden.sudoku.struct.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Array2dTest {

    @Test
    public void testConstructor() {
        Array2d<Integer> target = new Array2d<>(3, 4);

        assertEquals(3, target.getWidth());
        assertEquals(4, target.getHeight());
    }

    @Test
    public void testConstructorZero() {
        Array2d<Integer> target = new Array2d<>(0, 0);

        assertEquals(0, target.getWidth());
        assertEquals(0, target.getHeight());
    }

    @Test
    public void testSetGet() {
        Array2d<Integer> target = new Array2d<>(2, 2);

        Coordinate coordinate = new Coordinate(1, 1);
        target.set(coordinate, 1);

        assertEquals(new Integer(1), target.get(coordinate));
    }

}
