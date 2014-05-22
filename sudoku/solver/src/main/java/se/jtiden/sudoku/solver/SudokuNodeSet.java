package se.jtiden.sudoku.solver;

public interface SudokuNodeSet {
    boolean containsCandidate(int value);

    SudokuNodeSet getComplementInBox();

    SudokuNodeSet getComplementInRow();

    SudokuNodeSet getComplementInColumn();

    void removeCandidate(int value);
}
