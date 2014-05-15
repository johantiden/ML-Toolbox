package test.java.se.jtiden.sudoku;

import main.java.se.jtiden.sudoku.domain.Board;
import main.java.se.jtiden.sudoku.struct.Coordinate;

public class BoardFactory {
    public static Board parse(int order, int[][] array) {
        Board board = new Board(order);

        for (int y = 1; y <= order*order; ++y) {
            for (int x = 1; x <= order*order; ++x) {
                if(array[y-1][x-1] != 0) {
                    board.setHardDigit(new Coordinate(x, y), array[y-1][x-1]);
                }
            }
        }

        return board;
    }
}
