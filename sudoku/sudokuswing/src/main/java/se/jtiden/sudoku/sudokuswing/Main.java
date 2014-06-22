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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame {

    public static final int IMAGE_SIZE = 600;
    private final Board board;
    private final MultiSolver solver;
    private final SudokuBoardComponent boardPanel;
    private final SudokuBigNumbersComponent bigNumbersPanel;
    private final ActionListener buttonActionListener;

    public static void main(String[] args) {
        Main main = new Main();
    }


    public Main() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // _q1  ´¨Ä
        // Ä´098765431  §§  1QA> 2 this.board = getBoard(11);
        this.board = getBoard(9); // small
        //this.board = getBoard(13); // big

        this.solver = new SudokuSolverFactory().newSudokuSolver(board);
        final int margin = 10;
        Container contentPane = this.getContentPane();
        setSize(IMAGE_SIZE + 200, IMAGE_SIZE);

        boardPanel = new SudokuBoardComponent(board);
        add(boardPanel);

        this.buttonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(e.getActionCommand());
                boardPanel.setHighlightedNumber(value);
                boardPanel.repaint();
            }
        };
        bigNumbersPanel = new SudokuBigNumbersComponent(board.getOrder(), this.buttonActionListener);
        //bigNumbersPanel.setSize(new Dimension(300, 300));
        add(bigNumbersPanel);

        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        springLayout.putConstraint(SpringLayout.WEST, boardPanel, margin, SpringLayout.WEST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, boardPanel, margin, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.SOUTH, boardPanel, -margin, SpringLayout.SOUTH, contentPane);
        springLayout.putConstraint(SpringLayout.WIDTH, boardPanel, 0, SpringLayout.HEIGHT, boardPanel);

        springLayout.putConstraint(SpringLayout.NORTH, bigNumbersPanel, margin, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.EAST, bigNumbersPanel, -margin, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, bigNumbersPanel, margin, SpringLayout.EAST, boardPanel);
        springLayout.putConstraint(SpringLayout.HEIGHT, bigNumbersPanel, 0, SpringLayout.WIDTH, bigNumbersPanel);

        setVisible(true);

        startSimulating();
        startPainting();
    }

    private Board randomBoard() {
        SudokuTrainingDataManager manager = new SudokuTrainingDataManager();
        ListDecorator<SudokuTrainingData> trainingData = manager.getAll();

        int index = new Random().nextInt(trainingData.size());
        System.out.println("size:" + trainingData.size());
        System.out.println("chosen:" + index);
        SudokuTrainingData sudokuTrainingData = trainingData.get(index);

        System.out.println("Using board " + sudokuTrainingData);
        return sudokuTrainingData.getBoard();
    }

    private static Board getBoard(int i) {
        SudokuTrainingDataManager manager = new SudokuTrainingDataManager();
        ListDecorator<SudokuTrainingData> trainingData = manager.getAll();
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
                    Thread.sleep(1000);
                    boardPanel.repaint();
                    //bigNumbersPanel.repaint();
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
