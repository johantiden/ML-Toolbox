package test.java.se.jtiden.sudoku.trainingdata;

import main.java.se.jtiden.sudoku.domain.Board;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Ignore implements SudokuTrainingData {
    private final SudokuTrainingData inner;

    public Ignore(SudokuTrainingData inner) {
        this.inner = inner;
    }

    @Override
    public Board getBoard() {
        return inner.getBoard();
    }

    @Override
    public void assertSolved() {
        throw new NotImplementedException();
    }

    @Override
    public Difficulty getDifficulty() {
        return inner.getDifficulty();
    }

    @Override
    public String getName() {
        return inner.getName();
    }

    @Override
    public boolean isIgnore() {
        return true;
    }
}
