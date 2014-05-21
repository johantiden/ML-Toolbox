import main.java.se.jtiden.sudoku.domain.Board;
import main.java.se.jtiden.sudoku.domain.Node;
import main.java.se.jtiden.sudoku.solver.MultiSolver;
import main.java.se.jtiden.sudoku.solver.SudokuSolverFactory;
import test.java.se.jtiden.sudoku.trainingdata.SudokuPainter;
import test.java.se.jtiden.sudoku.trainingdata.SudokuTrainingDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends JFrame {

    public static final int IMAGE_WIDTH = 600;
    public static final int IMAGE_HEIGHT = 600;
    private final Board board;
    private final MultiSolver solver;
    private static final int OFFSET = 23;
    private final JPanel panel;

    public static void main(String[] args) {
        Main main = new Main();
    }


    public Main() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.board = new SudokuTrainingDataManager().getAll().get(11).getBoard();
        this.solver = new SudokuSolverFactory().newSudokuSolver(board);

        setSize(IMAGE_WIDTH, IMAGE_HEIGHT+OFFSET);
        panel = new CustomComponent();
        panel.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        add(panel);
        setMinimumSize(getSize());

        setVisible(true);

        startSimulating();
        startPainting();
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
                    panel.repaint();
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
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                failed = !solver.processOne();
            }
        }
    }

    class CustomComponent extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        public Dimension getMinimumSize() {
            return new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(IMAGE_WIDTH+1, IMAGE_HEIGHT+1);
        }

        @Override
        public void paint(Graphics g) {
            int bufferWidth = (int) (IMAGE_WIDTH*2);
            int bufferHeight = (int) (IMAGE_HEIGHT*2);
            Image backBuffer = new BufferedImage(bufferWidth, bufferHeight, BufferedImage.TYPE_INT_RGB);

            new SudokuPainter(board, bufferWidth, bufferHeight).paint(backBuffer.getGraphics());
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(backBuffer, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        }
    }
}
