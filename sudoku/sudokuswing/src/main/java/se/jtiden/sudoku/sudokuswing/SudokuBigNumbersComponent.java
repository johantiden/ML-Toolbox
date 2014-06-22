package se.jtiden.sudoku.sudokuswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SudokuBigNumbersComponent extends JPanel {

    private static final long serialVersionUID = 1L;

    private final ActionListener externalActionListener;
    //private final List<MyButton> buttons = new ArrayList<>();
    //private final ActionListener myActionListener;


    public SudokuBigNumbersComponent(int order, ActionListener actionListener) {

        this.externalActionListener = actionListener;
        /*this.myActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (MyButton b : buttons) {
                    b.setActivated(b.getActionCommand().equals(e.getActionCommand()));
                    b.repaint();
                }
            }
        }; */

        GridLayout gridLayout = new GridLayout(0, order);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        for (int i = 1; i <= order * order; ++i) {
            addButton(i);
        }

        this.setLayout(gridLayout);
    }

    /*@Override
    public void paint(Graphics g) {
        //g.setColor(Color.RED);
        //g.fillRect(0,0, getWidth(), getHeight());

        //super.paint(g);
    }   */


    private void addButton(int value) {
        Button button = new Button(String.valueOf(value));
        button.setActionCommand(String.valueOf(value));
        button.addActionListener(this.externalActionListener);
        //button.addActionListener(this.myActionListener);
        add(button);

        //buttons.add(button);
    }



}
