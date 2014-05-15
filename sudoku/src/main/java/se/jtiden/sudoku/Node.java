package main.java.se.jtiden.sudoku;

public abstract class Node {
    private final Coordinate coordinate;

    public Node(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isHard() {
        return false;
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
