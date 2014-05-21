package main.java.se.jtiden.sudoku.domain;

import main.java.se.jtiden.sudoku.solver.SudokuNodeSet;
import main.java.se.jtiden.sudoku.struct.Coordinate;

import java.util.Collection;

public interface Board {
    void setHardDigit(Coordinate coordinate, int value);

    int numCandidatesLeft(Coordinate coordinate);

    void removeCandidate(Coordinate coordinate, int value);

    void solveNode(Coordinate coordinate, int value);

    int getValue(Coordinate coordinate);

    Collection<? extends UnsolvedNode> getUnsolvedNodes();

    Iterable<? extends Node> getNeighborsFor(Node node);

    int countCandidatesInRowFor(Coordinate coordinate, int value);

    int countCandidatesInColumnFor(Coordinate coordinate, int value);

    int countCandidatesInBoxFor(Coordinate coordinate, int value);

    int getOrder();

    Iterable<? extends SudokuNodeSet> getAllRowSubGroups();

    Iterable<? extends SudokuNodeSet> getAllColumnSubGroups();

    Iterable<? extends Coordinate> getAllCoordinates();

    Node getNode(Coordinate coordinate);

    int groupId(int coordinate);
}
