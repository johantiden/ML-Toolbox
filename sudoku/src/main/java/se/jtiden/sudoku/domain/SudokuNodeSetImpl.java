package main.java.se.jtiden.sudoku.domain;

import main.java.se.jtiden.sudoku.solver.SudokuNodeSet;

import java.util.HashSet;
import java.util.Set;

public class SudokuNodeSetImpl implements SudokuNodeSet {
    private final Set<Node> mainNodes = new HashSet<>();

    private SudokuNodeSet complementInRow;
    private SudokuNodeSet complementInBox;
    private SudokuNodeSet complementInColumn;

    @Override
    public boolean containsCandidate(int value) {
        return mainNodes.stream().anyMatch(n -> n.candidatesContains(value));
    }

    @Override
    public SudokuNodeSet getComplementInBox() {
        return complementInBox;
    }

    @Override
    public SudokuNodeSet getComplementInRow() {
        return complementInRow;
    }

    @Override
    public SudokuNodeSet getComplementInColumn() {
        return complementInColumn;
    }

    @Override
    public void removeCandidate(int value) {
        mainNodes.stream().filter(n -> n.candidatesContains(value)).forEach(n -> n.removeCandidate(value));
    }

    public void putNode(Node node) {
        mainNodes.add(node);
    }

    public void setComplementInRow(SudokuNodeSet nodeSet) {
        complementInRow = nodeSet;
    }

    public void setComplementInBox(SudokuNodeSet complementInBox) {
        this.complementInBox = complementInBox;
    }

    public void setComplementInColumn(SudokuNodeSet complementInColumn) {
        this.complementInColumn = complementInColumn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        mainNodes.forEach(n -> {
            sb.append(n.getCoordinate()).append(",");
        });
        sb.setLength(sb.length()-1);
        return sb.toString();
    }
}
