package main.java.se.jtiden.sudoku.solver;

import main.java.se.jtiden.sudoku.domain.Node;

public interface SudokuNodeSet {
    boolean containsCandidate(int value);

    SudokuNodeSet getComplementInBox();

    SudokuNodeSet getComplementInRow();

    SudokuNodeSet getComplementInColumn();

    void removeCandidate(int value);
}
