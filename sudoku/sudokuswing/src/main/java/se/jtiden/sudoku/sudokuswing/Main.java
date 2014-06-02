package se.jtiden.sudoku.sudokuswing;

import se.jtiden.sudoku.domain.Board;
import se.jtiden.sudoku.domain.Node;
import se.jtiden.sudoku.solver.MultiSolver;
import se.jtiden.sudoku.solver.SudokuSolverFactory;
import se.jtiden.sudoku.struct.ListDecorator;
import se.jtiden.sudoku.trainingdata.SudokuTrainingData;
import se.jtiden.sudoku.trainingdata.SudokuTrainingDataManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame {

    public static final int IMAGE_SIZE = 600;
    private final Board board;
    private final MultiSolver solver;
    private final JPanel boardPanel;

    public static void main(String[] args) {
        Main main = new Main();
    }


    public Main() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.board = getBoard(8);
        this.solver = new SudokuSolverFactory().newSudokuSolver(board);
        final int margin = 50;
        Container contentPane = this.getContentPane();
        setSize(IMAGE_SIZE, IMAGE_SIZE);
        boardPanel = new SudokuBoardComponent(board);

        add(boardPanel);

        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        springLayout.putConstraint(SpringLayout.WEST, boardPanel, margin, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, boardPanel, margin, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.SOUTH, boardPanel, -margin, SpringLayout.SOUTH, contentPane);
        springLayout.putConstraint(SpringLayout.WIDTH, boardPanel, 0, SpringLayout.HEIGHT, boardPanel);

        setVisible(true);

        startSimulating();
        startPainting();
    }

    private Board randomBoard() {
        SudokuTrainingDataManager sudokuTrainingDataManager = new SudokuTrainingDataManager();
        ListDecorator<SudokuTrainingData> trainingData = sudokuTrainingDataManager.getAll();

        int index = new Random().nextInt(trainingData.size());
        System.out.println("size:" + trainingData.size());
        System.out.println("chosen:" + index);
        SudokuTrainingData sudokuTrainingData = trainingData.get(index);

        System.out.println("Using board " + sudokuTrainingData);
        return sudokuTrainingData.getBoard();
    }

    private Board getBoard(int i) {
        SudokuTrainingDataManager sudokuTrainingDataManager = new SudokuTrainingDataManager();
        ListDecorator<SudokuTrainingData> trainingData = sudokuTrainingDataManager.getAll();
        return trainingData.get(i).getBoard();
    }

    private void startPainting() {
        new Thread(new PaintRunnable()).start();
    }


    private void startSimulating() {
        new Thread(new AlgorithmRunnable()).start();
    }

    private int countCandidates() {
        int count = 0;
        for (Node node : board.getUnsolvedNodes()) {
            count += node.numCandidatesLeft();
        }
        return count;
    }


    private class PaintRunnable implements Runnable {


        @Override
        public void run() {
            while (isVisible()  && board.getUnsolvedNodes().size() > 0) {
                try {
                    Thread.sleep(30);
                    boardPanel.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class AlgorithmRunnable implements Runnable {

        @Override
        public void run() {
            boolean failed = false;
            while (isVisible() && board.getUnsolvedNodes().size() > 0 && !failed) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                failed = !solver.processOne();
            }
        }
    }

}
