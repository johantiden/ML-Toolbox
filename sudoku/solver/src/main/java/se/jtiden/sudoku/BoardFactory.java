package se.jtiden.sudoku;

import se.jtiden.sudoku.domain.Board;
import se.jtiden.sudoku.domain.BoardImpl;
import se.jtiden.sudoku.struct.Coordinate;

public class BoardFactory {
    public static Board parse(int order, int[][] array) {
        Board board = new BoardImpl(order);

        for (Coordinate coordinate : board.getAllCoordinates()) {
            if(array[coordinate.y-1][coordinate.x-1] != 0) {
                board.setHardDigit(coordinate, array[coordinate.y-1][coordinate.x-1]);
            }
        }

        return board;
    }

    public static Board parse(int order, String[] array) {
        Board board = new BoardImpl(order);

        for (Coordinate coordinate : board.getAllCoordinates()) {
            if(array[coordinate.y-1].charAt(coordinate.x-1) != 'x') {
                char c = array[coordinate.y-1].charAt(coordinate.x - 1);
                int value = charToInt(c);
                board.setHardDigit(coordinate, value);
            }
        }

        return board;
    }

    public static int charToInt(char c) {
        return c - 48;
    }
}
