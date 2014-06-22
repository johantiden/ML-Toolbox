package se.jtiden.sudoku.trainingdata;

import se.jtiden.sudoku.struct.Coordinate;
import se.jtiden.sudoku.BoardFactory;


public class SudokuTrainingDataIntImpl extends SudokuTrainingDataAbs implements SudokuTrainingData {
    private final int[][] solution;

    public SudokuTrainingDataIntImpl(String name, Difficulty difficulty, int[][] board, int[][] solution) {
        super(difficulty, name);
        this.solution = solution;
        super.setBoard(BoardFactory.parse(3, board));
    }

    public SudokuTrainingDataIntImpl(String name, Difficulty difficulty, int order, int[][] board, int[][] solution) {
        super(difficulty, name);
        this.solution = solution;
        super.setBoard(BoardFactory.parse(order, board));
    }

    @Override
    public void assertSolved() {
        assertEquals("Couldn't solve " + getDifficulty().name() + ": " + getName(), 0, getBoard().getUnsolvedNodes().size());

        if (solution != null) {
            final int size = getBoard().getOrder() * getBoard().getOrder();
            for (int y = 1; y <= size; ++y) {
                for (int x = 1; x <= size; ++x) {
                    assertEquals("(" + x + "," + y + ")", solution[y - 1][x - 1], getBoard().getValue(new Coordinate(x, y)));
                }
            }
        }
    }
}
