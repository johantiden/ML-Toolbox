package main.java.se.jtiden.sudoku.domain;

import main.java.se.jtiden.sudoku.struct.Coordinate;

public abstract class Node {
    private final Coordinate coordinate;

    public Node(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public abstract int numCandidatesLeft();

    public abstract void removeCandidate(int value);

    public abstract int getValue();

    public abstract boolean isSolved();

    public abstract boolean candidatesContains(int value);
}
