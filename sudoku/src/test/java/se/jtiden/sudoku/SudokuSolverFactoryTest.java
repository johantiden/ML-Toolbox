package test.java.se.jtiden.sudoku;

import main.java.se.jtiden.sudoku.domain.Board;
import main.java.se.jtiden.sudoku.solver.MultiSolver;
import main.java.se.jtiden.sudoku.solver.SudokuSolverFactory;
import main.java.se.jtiden.sudoku.struct.Coordinate;
import org.junit.Test;
import test.java.se.jtiden.sudoku.trainingdata.SudokuTrainingData;
import test.java.se.jtiden.sudoku.trainingdata.SudokuTrainingDataManager;

import static org.junit.Assert.assertEquals;

public class SudokuSolverFactoryTest {


    @Test
    public void testSolveSingleCandidate() {

        Board board = new Board(2);

        SudokuSolverFactory sudokuSolverFactory = new SudokuSolverFactory();

        MultiSolver solver = sudokuSolverFactory.newSudokuSolver(board);


        Coordinate topLeft = new Coordinate(1, 1);

        board.removeCandidate(topLeft, 4);
        board.removeCandidate(topLeft, 3);
        board.removeCandidate(topLeft, 2);
        solver.processOne();

        assertEquals(1, board.getValue(topLeft));
    }

    @Test
    public void testRemoveSingleCandidateInRow() {

        Board board = new Board(2);

        SudokuSolverFactory sudokuSolverFactory = new SudokuSolverFactory();

        MultiSolver solver = sudokuSolverFactory.newSudokuSolver(board);


        Coordinate topLeft = new Coordinate(1, 1);
        Coordinate topRight = new Coordinate(4, 1);

        board.setHardDigit(topRight, 1);


        assertEquals(4, board.numCandidatesLeft(topLeft));
        solver.processOne();
        assertEquals(3, board.numCandidatesLeft(topLeft));
    }



    @Test(expected = IllegalStateException.class)
    public void testMultipleSolution() {
        int[][] begin = new int[][]{
                {0,0,3,4},
                {0,0,0,0},
                {1,0,0,0},
                {2,0,0,0}
        };
        Board board = BoardFactory.parse(2, begin);

        SudokuSolverFactory sudokuSolverFactory = new SudokuSolverFactory();

        MultiSolver solver = sudokuSolverFactory.newSudokuSolver(board);

        boolean stop = false;
        int count = 0;
        while (!stop && board.getUnsolvedNodes().size() > 0) {
            stop  = !solver.processOne();
            count++;
        }

        System.out.println("Steps: " + count);
        assertEquals(0, board.getUnsolvedNodes().size());
        //assertSolution(solution, board);
    }



    @Test
    public void testSolveAll() {
        new SudokuTrainingDataManager().getAll().forEach(this::solveAndAssert);
    }


    private void solveAndAssert(SudokuTrainingData d) {
        solve(d.getBoard());
        d.assertSolved();
    }


    private void solve(String[] array) {
        Board board = BoardFactory.parse(3, array);
        solve(board);
    }

    private void solve(int[][] array) {
        Board board = BoardFactory.parse(3, array);
        solve(board);
    }

    private void solve(Board board) {
        SudokuSolverFactory sudokuSolverFactory = new SudokuSolverFactory();

        MultiSolver solver = sudokuSolverFactory.newSudokuSolver(board);

        boolean stop = false;
        int count = 0;
        while (!stop && board.getUnsolvedNodes().size() > 0) {
            stop  = !solver.processOne();
            count++;
        }

        System.out.println("Steps: " + count);
    }
}
