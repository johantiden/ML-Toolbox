package se.jtiden.sudoku.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.jtiden.sudoku.solver.SudokuNodeSet;
import se.jtiden.sudoku.struct.*;

public class BoardImpl implements Board {

    private final Array2d<Node> array2d;
    private final int order;

    public BoardImpl(int order) {
        this.order = order;
        array2d = new Array2d<Node>(order * order, order * order);

        initEmptySquares(order);
    }

    private void initEmptySquares(int order) {
        for (Coordinate coordinate : getAllCoordinates()) {
            array2d.set(coordinate, UnsolvedNode.newFullCandidates(coordinate, order));
        }
    }

    @Override
    public void setHardDigit(Coordinate coordinate, int value) {
        array2d.set(coordinate, new HardNode(coordinate, value));
    }

    @Override
    public int numCandidatesLeft(Coordinate coordinate) {
        return array2d.get(coordinate).numCandidatesLeft();
    }

    @Override
    public void removeCandidate(Coordinate coordinate, int value) {
        array2d.get(coordinate).removeCandidate(value);
    }

    @Override
    public void solveNode(Coordinate coordinate, int value) {
        //System.out.println("[BOARD] Setting " + coordinate + " to " + value);
        array2d.set(coordinate, new SolvedNode(coordinate, value));
        System.out.println("[BOARD] Remaining: " + getUnsolvedNodes().size());
    }

    @Override
    public int getValue(Coordinate coordinate) {
        return array2d.get(coordinate).getValue();
    }


    @Override
    public Collection<? extends UnsolvedNode> getUnsolvedNodes() {
        List<UnsolvedNode> unsolvedNodes = new ArrayList<>();

        for (Node node : array2d) {
            if (node instanceof UnsolvedNode) {
                unsolvedNodes.add((UnsolvedNode) node);
            }
        }
        return unsolvedNodes;
    }

    @Override
    public Iterable<? extends Coordinate> getAllCoordinates() {
        List<Coordinate> allCoordinates = new ArrayList<>(array2d.getHeight()*array2d.getWidth());
        for (int y = 1; y <= array2d.getHeight(); ++y) {
            for (int x = 1; x <= array2d.getHeight(); ++x) {
                allCoordinates.add(new Coordinate(x, y));
            }
        }
        return allCoordinates;
    }

    @Override
    public Node getNode(Coordinate coordinate) {
        return array2d.get(coordinate);
    }

    @Override
    public Iterable<? extends Node> getNeighborsFor(Node node) {
        List<Node> neighbors = new ArrayList<>(3 * (order-1));

        addRow(node, neighbors);
        addColumn(node, neighbors);
        addBox(node, neighbors);

        return neighbors;
    }

    private void addBox(Node node, final List<Node> neighbors) {
        getBoxNodes(node.getCoordinate()).forEach(new Consumer<Node>() {
            @Override
            public void apply(final Node boxNode) {
                neighbors.add(boxNode);
            }
        });
    }

    private CollectionDecorator<Coordinate> getBox(Coordinate coordinate) {
        List<Coordinate> box = new ArrayList<>(order);

        int groupIdX = groupId(coordinate.x);
        int groupIdY = groupId(coordinate.y);

        for (int x = 1 + groupIdX * order; x < 1 + (groupIdX + 1) * order; ++x) {
            for (int y = 1 + groupIdY * order; y < 1 + (groupIdY + 1) * order; ++y) {
                box.add(new Coordinate(x, y));
            }
        }

        return new CollectionDecoratorImpl<>(box);
    }

    public int groupId(int coordinate) {
        return (coordinate - 1) / order;
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

    @Override
    public int countCandidatesInRowFor(Coordinate coordinate, int value) {
        int count = 0;
        for (Node node : array2d.getRow(coordinate.y)) {
            if (node.candidatesContains(value)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countCandidatesInColumnFor(Coordinate coordinate, int value) {
        int count = 0;
        for (Node node : array2d.getColumn(coordinate.x)) {
            if (node.candidatesContains(value)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countCandidatesInBoxFor(Coordinate coordinate, final int value) {
        final int[] count = {0};
        getBox(coordinate).forEach(new Consumer<Coordinate>() {
            @Override
            public void apply(final Coordinate coordinateInBox) {
                if (array2d.get(coordinateInBox).candidatesContains(value)) {
                    count[0]++;
                }
            }
        });

        return count[0];
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public Iterable<? extends SudokuNodeSet> getAllRowSubGroups() {
        List<SudokuNodeSet> allRowSubsets = new ArrayList<>(order*order*order);
        for (int y = 1; y <= array2d.getHeight(); ++y) {
            final int myY = y;
            for (int groupId = 0; groupId < order; ++groupId) {
                se.jtiden.sudoku.domain.SudokuNodeSetImpl subSet = new se.jtiden.sudoku.domain.SudokuNodeSetImpl();

                se.jtiden.sudoku.domain.SudokuNodeSetImpl complementInRow = new se.jtiden.sudoku.domain.SudokuNodeSetImpl();
                subSet.setComplementInRow(complementInRow);
                for (Node node : getRow(y)) {
                    if (groupId(node.getCoordinate().x) == groupId) {
                        subSet.putNode(node);
                    } else {
                        complementInRow.putNode(node);
                    }
                }

                final SudokuNodeSetImpl complementInBox = new SudokuNodeSetImpl();
                subSet.setComplementInBox(complementInBox);
                getBoxNodes(new Coordinate(groupId * order + 1, y)).filter(new Predicate<Node>() {
                    @Override
                    public boolean evaluate(final Node node) {
                        return node.getCoordinate().y != myY;
                    }
                }).forEach(new Consumer<Node>() {
                    @Override
                    public void apply(final Node node) {
                        complementInBox.putNode(node);
                    }
                });


                allRowSubsets.add(subSet);
            }
        }
        return allRowSubsets;
    }

    private CollectionDecorator<Node> getBoxNodes(Coordinate coordinate) {
        final List<Node> nodes = new ArrayList<>();
        getBox(coordinate).forEach(new Consumer<Coordinate>() {
            @Override
            public void apply(final Coordinate c) {
                nodes.add(array2d.get(c));
            }
        });
        return new CollectionDecoratorImpl<>(nodes);
    }


    @Override
    public Iterable<? extends SudokuNodeSet> getAllColumnSubGroups() {
        List<SudokuNodeSet> allColSubsets = new ArrayList<SudokuNodeSet>();
        for (int x = 1; x <= array2d.getWidth(); ++x) {
            final int myX = x;
            for (int groupId = 0; groupId < order; ++groupId) {
                se.jtiden.sudoku.domain.SudokuNodeSetImpl subSet = new se.jtiden.sudoku.domain.SudokuNodeSetImpl();

                se.jtiden.sudoku.domain.SudokuNodeSetImpl complementInCol = new se.jtiden.sudoku.domain.SudokuNodeSetImpl();
                subSet.setComplementInColumn(complementInCol);
                for (Node node : getColumn(x)) {
                    if (groupId(node.getCoordinate().y) == groupId) {
                        subSet.putNode(node);
                    } else {
                        complementInCol.putNode(node);
                    }
                }

                final SudokuNodeSetImpl complementInBox = new SudokuNodeSetImpl();
                subSet.setComplementInBox(complementInBox);

                getBoxNodes(new Coordinate(x, groupId * order + 1)).filter(new Predicate<Node>() {
                    @Override
                    public boolean evaluate(final Node node) {
                        return node.getCoordinate().x != myX;
                    }
                }).forEach(new Consumer<Node>() {
                    @Override
                    public void apply(final Node node) {
                        complementInBox.putNode(node);
                    }
                });


                allColSubsets.add(subSet);
            }
        }
        return allColSubsets;
    }

    private Iterable<? extends Node> getColumn(int x) {
        List<Node> column = new ArrayList<Node>();
        for (int y = 1; y <= array2d.getHeight(); ++y) {
            column.add(array2d.get(new Coordinate(x, y)));
        }
        return column;
    }

    private Iterable<Node> getRow(int y) {
        List<Node> row = new ArrayList<Node>();
        for (int x = 1; x <= array2d.getWidth(); ++x) {
            row.add(array2d.get(new Coordinate(x, y)));
        }
        return row;
    }
}
