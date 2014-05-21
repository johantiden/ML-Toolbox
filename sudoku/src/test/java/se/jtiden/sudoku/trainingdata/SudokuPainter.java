package test.java.se.jtiden.sudoku.trainingdata;

import main.java.se.jtiden.sudoku.domain.Board;
import main.java.se.jtiden.sudoku.domain.HardNode;
import main.java.se.jtiden.sudoku.domain.Node;
import main.java.se.jtiden.sudoku.domain.UnsolvedNode;
import main.java.se.jtiden.sudoku.struct.Coordinate;

import java.awt.*;

public class SudokuPainter {
    private final Board board;
    private final int width;
    private final int height;

    public SudokuPainter(Board board, int width, int height) {
        this.board = board;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);

        float adjustmentForBoxMarginXCoefficient = width/500f;
        float adjustmentForBoxMarginYCoefficient = height/500f;

        float nodeWidth = ((float) width) / board.getOrder() / board.getOrder() - adjustmentForBoxMarginXCoefficient;
        float nodeHeight = ((float) height) / board.getOrder() / board.getOrder() - adjustmentForBoxMarginYCoefficient;

        for (Coordinate coordinate : board.getAllCoordinates()) {
            Node node = board.getNode(coordinate);

            float adjustXForBoxMargin = board.groupId(node.getCoordinate().x) * adjustmentForBoxMarginXCoefficient*5;
            float adjustYForBoxMargin = board.groupId(node.getCoordinate().y) * adjustmentForBoxMarginXCoefficient*5;
            drawNode(graphics, node, adjustXForBoxMargin + nodeWidth * (coordinate.x - 1), adjustYForBoxMargin + nodeHeight * (coordinate.y - 1), nodeWidth, nodeHeight);
        }
    }



    private void drawNode(Graphics graphics, Node node, float x, float y, float nodeWidth, float nodeHeight) {
        graphics.setColor(Color.WHITE);
        float margin = nodeWidth/100f;
        graphics.fillRoundRect(
                Math.round(x+margin),
                Math.round(y+margin),
                Math.round(nodeWidth-2*margin),
                Math.round(nodeHeight-2*margin),
                10,
                10);


        graphics.setColor(Color.BLACK);
        graphics.draw3DRect(Math.round(x), Math.round(y), Math.round(nodeWidth), Math.round(nodeHeight), true);

        if (node.isSolved()) {
            if (node instanceof HardNode) {
                graphics.setColor(Color.BLACK);
            } else {
                graphics.setColor(Color.BLUE);
            }
            graphics.setFont(new Font("Arial", 1, 70));
            String s = String.valueOf(node.getValue());
            graphics.drawString(s, (int) (x + nodeWidth / 2 - 17), (int) (y + nodeHeight / 2 + 20));
        } else {
            if (node instanceof UnsolvedNode) {
                UnsolvedNode unsolvedNode = (UnsolvedNode) node;
                for (Integer candidateValue : unsolvedNode.getCandidates()) {
                    drawCandidate(graphics, x, y, nodeWidth, nodeHeight, candidateValue);
                }
            }
        }
    }

    private void drawCandidate(Graphics graphics, float x, float y, float nodeWidth, float nodeHeight, Integer candidateValue) {
        float biasOut = 0.4f;
        float x2 = candidateCenterX(x, nodeWidth, biasOut, candidateValue);
        float y2 = candidateCenterY(y, nodeHeight, biasOut, candidateValue);

        float dotRadius = Math.min(nodeHeight, nodeWidth) / 15f;

        fillCircleCentered(graphics, x2, y2, dotRadius);

    }

    private static void fillCircleCentered(Graphics graphics, float x, float y, float radius) {
        graphics.fillOval(
                Math.round(x - radius),
                Math.round(y - radius),
                Math.round(radius * 2),
                Math.round(radius * 2));
    }

    private float candidateCenterX(float x, float nodeWidth, float biasOut, int candidateValue) {
        switch (candidateValue) {
            case 1:
            case 3:
            case 4:
                return x + nodeWidth / 2 - biasOut*nodeWidth;
            case 2:
            case 5:
            case 8:
                return x + nodeWidth / 2;
            default:
                return x + nodeWidth / 2 + biasOut*nodeWidth;
        }
    }

    private float candidateCenterY(float y, float nodeHeight, float biasOut, int candidateValue) {
        switch (candidateValue) {
            case 1:
            case 2:
            case 3:
                return y + nodeHeight / 2 - biasOut*nodeHeight;
            case 4:
            case 5:
            case 6:
                return y + nodeHeight / 2;
            default:
                return y + nodeHeight / 2 + biasOut*nodeHeight;
        }
    }

}
