package test.java.se.jtiden.sudoku.trainingdata;

import main.java.se.jtiden.sudoku.struct.Coordinate;
import test.java.se.jtiden.sudoku.BoardFactory;

import static org.junit.Assert.assertEquals;

public class SudokuTrainingDataStringImpl extends SudokuTrainingDataAbs implements SudokuTrainingData {
    private final String[] solution;

    public SudokuTrainingDataStringImpl(String name, int order, Difficulty difficulty, String[] board, String[] solution) {
        super(difficulty, name);
        this.solution = solution;
        super.setBoard(BoardFactory.parse(order, board));
    }

    @Override
    public void assertSolved() {
        assertEquals(0, getBoard().getUnsolvedNodes().size());

        final int size = getBoard().getOrder() * getBoard().getOrder();
        for (int y = 1; y <= size; ++y) {
            for (int x = 1; x <= size; ++x) {
                int expected = BoardFactory.charToInt(solution[y - 1].charAt(x - 1));
                assertEquals("(" + x + "," + y + ")", expected, getBoard().getValue(new Coordinate(x, y)));
            }
        }
    }
}

