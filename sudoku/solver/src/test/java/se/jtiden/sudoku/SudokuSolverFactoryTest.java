package se.jtiden.sudoku;

import org.junit.Test;
import se.jtiden.sudoku.domain.Board;
import se.jtiden.sudoku.domain.BoardImpl;
import se.jtiden.sudoku.solver.MultiSolver;
import se.jtiden.sudoku.solver.SudokuSolverFactory;
import se.jtiden.sudoku.struct.Consumer;
import se.jtiden.sudoku.struct.Coordinate;
import se.jtiden.sudoku.trainingdata.SudokuTrainingData;
import se.jtiden.sudoku.trainingdata.SudokuTrainingDataManager;

import static org.junit.Assert.assertEquals;

public class SudokuSolverFactoryTest {


    @Test
    public void testSolveSingleCandidate() {

        Board board = new BoardImpl(2);

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

        Board board = new BoardImpl(2);

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
                {0, 0, 3, 4},
                {0, 0, 0, 0},
                {1, 0, 0, 0},
                {2, 0, 0, 0}
        };
        Board board = BoardFactory.parse(2, begin);

        SudokuSolverFactory sudokuSolverFactory = new SudokuSolverFactory();

        MultiSolver solver = sudokuSolverFactory.newSudokuSolver(board);

        boolean stop = false;
        int count = 0;
        while (!stop && board.getUnsolvedNodes().size() > 0) {
            stop = !solver.processOne();
            count++;
        }

        System.out.println("Steps: " + count);
        assertEquals(0, board.getUnsolvedNodes().size());
        //assertSolution(solution, board);
    }


    @Test
    public void testSolveAll() {
        new SudokuTrainingDataManager().getAll().forEach(
                new Consumer<SudokuTrainingData>() {
                    @Override
                    public void apply(final SudokuTrainingData sudokuTrainingData) {
                        solveAndAssert(sudokuTrainingData);
                    }
                }
        );
    }

    @Test
    public void testSolve0() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(0));
    }

    @Test
    public void testSolve1() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(1));
    }

    @Test
    public void testSolve2() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(2));
    }

    @Test
    public void testSolve3() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(3));
    }

    @Test
    public void testSolve4() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(4));
    }

    @Test
    public void testSolve5() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(5));
    }

    @Test
    public void testSolve6() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(6));
    }

    @Test
    public void testSolve7() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(7));
    }

    @Test
    public void testSolve8() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(8));
    }

    @Test
    public void testSolve9() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(9));
    }

    @Test
    public void testSolve10() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(10));
    }

    @Test
    public void testSolve11() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(11));
    }

    @Test
    public void testSolve12() {
        solveAndAssert(new SudokuTrainingDataManager().getAll()
                .get(12));
    }


    private void solveAndAssert(SudokuTrainingData d) {
        if (d.isIgnore()) {
            System.out.println("Ignoring test " + d);
        } else {
            System.out.println("Will solve " + d);
            solve(d.getBoard());
            d.assertSolved();
        }

    }


    private void solve(Board board) {
        SudokuSolverFactory sudokuSolverFactory = new SudokuSolverFactory();

        MultiSolver solver = sudokuSolverFactory.newSudokuSolver(board);

        boolean stop = false;
        int count = 0;
        while (!stop && board.getUnsolvedNodes().size() > 0) {
            stop = !solver.processOne();
            count++;
        }

        System.out.println("Steps: " + count);
    }
}
