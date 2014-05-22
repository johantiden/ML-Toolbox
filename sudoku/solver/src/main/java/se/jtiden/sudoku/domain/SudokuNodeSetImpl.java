package se.jtiden.sudoku.domain;

import se.jtiden.sudoku.solver.SudokuNodeSet;
import se.jtiden.sudoku.struct.Consumer;
import se.jtiden.sudoku.struct.CollectionDecoratorImpl;
import se.jtiden.sudoku.struct.Predicate;

import java.util.HashSet;
import java.util.Set;

public class SudokuNodeSetImpl implements SudokuNodeSet {
    private final Set<Node> mainNodes = new HashSet<Node>();

    private SudokuNodeSet complementInRow;
    private SudokuNodeSet complementInBox;
    private SudokuNodeSet complementInColumn;

    @Override
    public boolean containsCandidate(final int value) {
        return new CollectionDecoratorImpl<Node>(mainNodes).anyMatch(new Predicate<Node>() {
            @Override
            public boolean evaluate(Node n) {
                return n.candidatesContains(value);
            }
        });
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
    public void removeCandidate(final int value) {
        new CollectionDecoratorImpl<Node>(mainNodes).filter(new Predicate<Node>() {
            @Override
            public boolean evaluate(final Node n) {
                return n.candidatesContains(value);
            }
        }).forEach(new Consumer<Node>() {
            @Override
            public void apply(final Node node) {
                node.removeCandidate(value);
            }
        });
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
        final StringBuilder sb = new StringBuilder();
        new CollectionDecoratorImpl<Node>(mainNodes).forEach(new Consumer<Node>() {
            @Override
            public void apply(final Node node) {
                sb.append(node.getCoordinate()).append(",");
            }
        });
        sb.setLength(sb.length()-1);
        return sb.toString();
    }
}
