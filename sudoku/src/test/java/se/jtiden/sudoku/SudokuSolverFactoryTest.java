package test.java.se.jtiden.sudoku;

import main.java.se.jtiden.sudoku.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SudokuSolverFactoryTest {

    private final static int x = 0;


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


    @Test
    public void testSolve32WithParse() {
        int[][] begin = new int[][]{
                {1,2,3,4},
                {4,3,1,2},
                {2,1,0,3},
                {3,4,2,1}
        };
        int[][] solution = new int[][]{
                {1,2,3,4},
                {4,3,1,2},
                {2,1,4,3},
                {3,4,2,1}
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
        assertSolution(solution, board);
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

    private void assertSolution(int[][] solution, Board board) {
        final int size = board.getOrder() * board.getOrder();
        for (int y = 1; y <= size; ++y) {
            for (int x = 1; x <= size; ++x) {
                assertEquals("("+x+","+y+")", solution[y-1][x-1], board.getValue(new Coordinate(x, y)));
            }
        }
    }

    @Test
    public void testSolveRealEasiest_180_38_36() {
        int[][] array = new int[][]{
                {1,3,7 , x,5,9 , 4,x,2},
                {8,6,4 , x,3,2 , 9,5,x},
                {5,9,2 , 8,4,7 , 3,x,6},

                {3,8,x , 5,x,4 , 6,x,9},
                {6,x,x , 3,x,1 , 8,4,5},
                {7,x,5 , 9,x,x , 2,3,1},

                {9,7,8 , x,1,3 , 5,6,4},
                {4,x,6 , 7,x,x , x,x,3},
                {2,1,3 , 4,6,5 , 7,9,8}
        };

        int[][] solution = new int[][]{
                {1,3,7 , 6,5,9 , 4,8,2},
                {8,6,4 , 1,3,2 , 9,5,7},
                {5,9,2 , 8,4,7 , 3,1,6},

                {3,8,1 , 5,2,4 , 6,7,9},
                {6,2,9 , 3,7,1 , 8,4,5},
                {7,4,5 , 9,8,6 , 2,3,1},

                {9,7,8 , 2,1,3 , 5,6,4},
                {4,5,6 , 7,9,8 , 1,2,3},
                {2,1,3 , 4,6,5 , 7,9,8}
        };
        Board board = solve(array);
        assertEquals(0, board.getUnsolvedNodes().size());
        assertSolution(solution, board);
    }

    @Test
    public void testSolveRealEasyAsPie_270_70_47() {
        int[][] array = new int[][]{
                {x,2,5 , x,7,6 , 8,1,x},
                {8,7,3 , x,x,5 , 6,2,9},
                {6,x,1 , x,x,2 , 5,4,7},

                {1,8,2 , 7,x,x , x,x,5},
                {x,x,x , x,5,9 , x,x,1},
                {9,x,x , 8,1,3 , 2,7,x},

                {2,x,9 , 4,3,x , 7,5,8},
                {x,4,x , 5,9,x , 1,x,x},
                {5,1,7 , 6,x,8 , 9,3,x},
        };
        int[][] solution = new int[][]{
                {4,2,5 , 9,7,6 , 8,1,3},
                {8,7,3 , 1,4,5 , 6,2,9},
                {6,9,1 , 3,8,2 , 5,4,7},

                {1,8,2 , 7,6,4 , 3,9,5},
                {7,3,6 , 2,5,9 , 4,8,1},
                {9,5,4 , 8,1,3 , 2,7,6},

                {2,6,9 , 4,3,1 , 7,5,8},
                {3,4,8 , 5,9,7 , 1,6,2},
                {5,1,7 , 6,2,8 , 9,3,4},
        };
        Board board = solve(array);
        assertEquals(0, board.getUnsolvedNodes().size());
        assertSolution(solution, board);
    }

    @Test
    public void testSolveRealPicnic_360_147_105() {
        int[][] array = new int[][]{
                {x,x,3,2,5,9,x,x,4},
                {x,1,x,6,7,x,x,x,8},
                {2,7,x,x,1,4,x,3,9},

                {9,3,x,x,x,5,7,8,2},
                {x,2,x,9,8,x,x,x,x},
                {x,x,8,3,x,7,x,x,6},

                {x,x,x,7,4,8,x,5,3},
                {x,x,4,x,9,x,8,6,7},
                {x,x,2,5,x,x,x,9,x},
        };
        Board board = solve(array);
        assertEquals(0, board.getUnsolvedNodes().size());
    }


    @Test
    public void testSolveRealSimple441_207_189() {
        int[][] array = new int[][]{
                {x,x,x,x,x,x,1,8,x},
                {x,x,x,1,x,3,x,4,7},
                {x,3,9,8,x,x,x,2,5},

                {x,x,6,x,8,7,2,x,x},
                {x,7,x,x,x,x,x,9,x},
                {x,x,2,4,9,x,7,x,x},

                {7,8,x,x,x,1,4,6,x},
                {3,6,x,5,x,2,x,x,x},
                {x,2,4,x,x,x,x,x,x},
        };
        Board board = solve(array);
        assertEquals(0, board.getUnsolvedNodes().size());
    }

    @Test
    public void testSolveRealEasy495Now245Now216() {
        int[][] array = new int[][]{
                {3,x,x,x,9,2,6,x,x},
                {9,x,x,5,x,x,x,x,x},
                {1,5,x,x,x,x,x,4,x},

                {x,x,x,x,2,x,8,9,x},
                {x,x,9,x,x,x,2,x,x},
                {x,1,8,x,3,x,x,x,x},

                {x,3,x,x,x,x,x,5,8},
                {x,x,x,x,x,8,x,x,2},
                {x,x,4,3,7,x,x,x,9},
        };
        Board board = solve(array);
        assertEquals(0, board.getUnsolvedNodes().size());
    }

    @Test
    public void testSolveRealModerate_236_211() {
        int[][] array = new int[][]{
                {x,x,x,6,3,2,5,x,7},
                {x,7,x,x,5,x,x,6,x},
                {x,x,x,x,1,x,x,9,x},

                {3,5,x,x,x,x,x,x,x},
                {1,x,x,x,x,x,x,x,9},
                {x,x,x,x,x,x,x,5,1},

                {x,6,x,x,8,x,x,x,x},
                {x,1,x,x,6,x,x,4,x},
                {4,x,8,3,7,1,x,x,x},
        };
        Board board = solve(array);
        assertEquals(0, board.getUnsolvedNodes().size());
    }


    @Test
    public void testSolveRealDifficult_fail_337() {
        int[][] array = new int[][]{
                {x,x,x,5,x,1,x,2,6},
                {x,x,x,x,2,x,x,x,x},
                {x,5,x,8,x,x,7,9,x},

                {x,x,1,x,6,x,x,3,7},
                {x,6,x,x,x,x,x,5,x},
                {2,7,x,x,1,x,6,x,x},

                {x,2,5,x,x,9,x,7,x},
                {x,x,x,x,7,x,x,x,x},
                {7,4,x,3,x,8,x,x,x},
        };
        Board board = solve(array);
        assertEquals(0, board.getUnsolvedNodes().size());
    }

    private Board solve(int[][] array) {
        Board board = BoardFactory.parse(3, array);

        SudokuSolverFactory sudokuSolverFactory = new SudokuSolverFactory();

        MultiSolver solver = sudokuSolverFactory.newSudokuSolver(board);

        boolean stop = false;
        int count = 0;
        while (!stop && board.getUnsolvedNodes().size() > 0) {
            stop  = !solver.processOne();
            count++;
        }

        System.out.println("Steps: " + count);
        return board;
    }
}
