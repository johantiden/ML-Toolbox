package main.java.se.jtiden.sudoku;

public class SolvedNode extends Node {
    private final int value;

    public SolvedNode(Coordinate coordinate, int value) {
        super(coordinate);
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public boolean candidatesContains(int value) {
        return false;
    }

    @Override
    public int numCandidatesLeft() {
        return 0;
    }

    @Override
    public void removeCandidate(int value) {
        throw new IllegalStateException("You cannot remove candidates node that is already solved.");
    }
}
