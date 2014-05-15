package main.java.se.jtiden.sudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Board {

    private final Array2d<Node> array2d;
    private final int order;

    public Board(int order) {
        this.order = order;
        array2d = new Array2d<Node>(order * order, order * order);

        initEmptySquares(order);
    }

    private void initEmptySquares(int order) {
        for (Coordinate coordinate : getAllCoordinates()) {
            array2d.set(coordinate, UnsolvedNode.newFullCandidates(coordinate, order));
        }
    }

    public void setHardDigit(Coordinate coordinate, int value) {
        array2d.set(coordinate, new HardNode(coordinate, value));
    }

    public boolean isHard(Coordinate coordinate) {
        return array2d.get(coordinate).isHard();
    }

    public int numCandidatesLeft(Coordinate coordinate) {
        return array2d.get(coordinate).numCandidatesLeft();
    }

    public void removeCandidate(Coordinate coordinate, int value) {
        array2d.get(coordinate).removeCandidate(value);
    }

    public void solveNode(Coordinate coordinate, int value) {
        //System.out.println("[BOARD] Setting " + coordinate + " to " + value);
        array2d.set(coordinate, new SolvedNode(coordinate, value));
        System.out.println("[BOARD] Remaining: " + getUnsolvedNodes().size());
    }

    public int getValue(Coordinate coordinate) {
        return array2d.get(coordinate).getValue();
    }


    public Collection<? extends UnsolvedNode> getUnsolvedNodes() {
        List<UnsolvedNode> unsolvedNodes = new ArrayList<UnsolvedNode>();

        for (Node node : array2d) {
            if (node instanceof UnsolvedNode) {
                unsolvedNodes.add((UnsolvedNode)node);
            }
        }
        return unsolvedNodes;
    }

    public Iterable<? extends Coordinate> getAllCoordinates() {
        List<Coordinate> allCoordinates = new ArrayList<Coordinate>();
        for (int y = 1; y <= array2d.getHeight(); ++y) {
            for (int x = 1; x <= array2d.getHeight(); ++x) {
                allCoordinates.add(new Coordinate(x, y));
            }
        }
        return allCoordinates;
    }

    public Iterable<? extends Node> getNeighborsFor(Node node) {
        List<Node> neighbors = new ArrayList<Node>();

        addRow(node, neighbors);
        addColumn(node, neighbors);
        addBox(node, neighbors);

        return neighbors;
    }

    private void addBox(Node node, List<Node> neighbors) {
        for (Coordinate coordinateInBox : getBox(node.getCoordinate())) {
            neighbors.add(array2d.get(coordinateInBox));
        }
    }

    private Iterable<Coordinate> getBox(Coordinate coordinate) {
        List<Coordinate> box = new ArrayList<Coordinate>();

        int groupIdX = (coordinate.x - 1) / order;
        int groupIdY = (coordinate.y - 1) / order;

        for (int x = 1 + groupIdX * order; x < 1 + (groupIdX + 1 ) * order; ++x) {
            for (int y = 1 + groupIdY * order; y < 1 + (groupIdY + 1 ) * order; ++y) {
                box.add(new Coordinate(x, y));
            }
        }

        return box;
    }

    private void addColumn(Node node, List<Node> neighbors) {
        for (Node that : array2d.getColumn(node.getCoordinate().x)) {
            if (that != node) {
                neighbors.add(that);
            }
        }
    }

    private void addRow(Node node, List<Node> neighbors) {
        for (Node that : array2d.getRow(node.getCoordinate().y)) {
            if (that != node) {
                neighbors.add(that);
            }
        }
    }

    public int countCandidatesInRowFor(Coordinate coordinate, int value) {
        int count = 0;
        for (Node node : array2d.getRow(coordinate.y)) {
            if (node.candidatesContains(value)) {
                count++;
            }
        }
        return count;
    }

    public int countCandidatesInColumnFor(Coordinate coordinate, int value) {
        int count = 0;
        for (Node node : array2d.getColumn(coordinate.x)) {
            if (node.candidatesContains(value)) {
                count++;
            }
        }
        return count;
    }

    public int countCandidatesInBoxFor(Coordinate coordinate, int value) {
        int count = 0;
        for (Coordinate coordinateInBox : getBox(coordinate)) {
            if (array2d.get(coordinateInBox).candidatesContains(value)) {
                count++;
            }
        }
        return count;
    }

    public int getOrder() {
        return order;
    }
}
