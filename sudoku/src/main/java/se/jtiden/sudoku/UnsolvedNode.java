package main.java.se.jtiden.sudoku;

import java.util.*;

public class UnsolvedNode extends Node {

    private final Map<Integer, Boolean> candidates;

    private UnsolvedNode(Coordinate coordinate) {
        super(coordinate);
        candidates = new HashMap<>();
    }

    @Override
    public int numCandidatesLeft() {
        return getCandidates().size();
    }

    @Override
    public void removeCandidate(int value) {
        if (!candidates.containsKey(value)) {
            throw new IllegalStateException("Index out of range: " + value);
        }
        candidates.put(value, false);
    }

    @Override
    public int getValue() {
        throw new IllegalStateException("This node doesn't have a value, it's unsolved!");
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean candidatesContains(int value) {
        return candidates.containsKey(value) && candidates.get(value);
    }

    public static UnsolvedNode newFullCandidates(Coordinate coordinate, int order) {
        UnsolvedNode node = new UnsolvedNode(coordinate);
        for (int i = 1; i <= order*order; ++i) {
            node.candidates.put(i, true);
        }
        return node;
    }

    public Collection<Integer> getCandidates() {
        List<Integer> list = new ArrayList<>();
        for (Integer value : candidates.keySet()) {
            if (candidates.get(value)) {
                list.add(value);
            }
        }
        return list;
    }
}
