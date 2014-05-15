package main.java.se.jtiden.sudoku.domain;

import main.java.se.jtiden.sudoku.struct.Coordinate;

public class HardNode extends Node {
    private final int value;

    public HardNode(Coordinate coordinate, int value) {
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
    public boolean isHard() {
        return true;
    }

    @Override
    public int numCandidatesLeft() {
        throw new IllegalStateException("You cannot count candidates on predefined nodes.");
    }

    @Override
    public void removeCandidate(int value) {
        throw new IllegalStateException("You cannot remove candidates from predefined nodes.");
    }
}
