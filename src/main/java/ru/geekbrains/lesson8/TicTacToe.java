package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel bottom_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[][] buttons = new JButton[3][3];
    boolean human_turn = true;
    boolean end_game = false;

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 255));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(10, 150, 150));

        bottom_panel.setLayout(new BorderLayout());
        bottom_panel.setBounds(0,0,800,50);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y] = new JButton();
                button_panel.add(buttons[x][y]);
                buttons[x][y].setFont(new Font("Ink Free", Font.BOLD, 150));
                buttons[x][y].setFocusable(false);
                buttons[x][y].addActionListener(this);
            }
        }

        title_panel.add(textField);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        while (end_game == false) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (end_game!=true) textField.setText("Human´s turn");
            if (human_turn==false && end_game == false) {
                textField.setText("Robot´s turn");
                randomComputerTurn(buttons);
                check();
                human_turn = true;
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (e.getSource() == buttons[x][y]) {
                    if (buttons[x][y].getText() == "") {
                        buttons[x][y].setForeground(new Color(255, 0, 0));
                        buttons[x][y].setText("X");
                        check();
                        human_turn = false;
                    }
                }
            }
        }
    }

    public void check() {

        if (checkRows("X", buttons) || checkColumns("X", buttons) || checkDiagonals("X", buttons)) {
            endGame(buttons);
            textField.setText("Human wins!");
            end_game = true;
        }
        else if (checkRows("O", buttons) || checkColumns("O", buttons) || checkDiagonals("O", buttons)) {
            endGame(buttons);
            textField.setText("Robot wins!");
            end_game = true;
        }
        else if (checkEnd(buttons)) {
            endGame(buttons);
            textField.setText("Draw!");
            end_game = true;
        }
    }

    public static boolean checkRows(String symbol, JButton[][] buttons) {
        for (int x = 0; x < 3; x++) {
            int counterChecker = 0;
            for (int y = 0; y < 3; y++) {
                if (buttons[x][y].getText() == symbol) {
                    counterChecker++;
                    if (counterChecker == 3) {
                        return true;
                    }
                } else {
                    counterChecker = 0;
                }
            }
        }
        return false;
    }
    private static boolean checkColumns(String symbol, JButton[][] buttons) {
        for (int y=0; y < 3; y++) {
            int counterChecker = 0;
            for (int x= 0; x < 3; x++) {
                if (buttons[x][y].getText() == symbol) {
                    counterChecker ++;
                    if (counterChecker == 3) {
                        return true;
                    }
                }
                else {
                    counterChecker = 0;
                }
            }
        }
        return false;
    }

    private static boolean checkEnd(JButton[][] buttons) {
        int counterChecker = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (buttons[x][y].getText()!="") {
                    counterChecker ++;
                }
            }
        }
        if (counterChecker == 9) {
            return true;
        }
        return false;
    }

    public static boolean checkDiagonals(String symbol, JButton[][] buttons) {
        if ((buttons[0][0].getText() == symbol && buttons[1][1].getText() == symbol && buttons[2][2].getText() == symbol) ||
        (buttons[0][2].getText() == symbol && buttons[1][1].getText() == symbol && buttons[2][0].getText() == symbol)) {
            return true;
        }
        return false;
    }
    public static void endGame(JButton[][] buttons) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y].setEnabled(false);
            }
        }
    }

    public void randomComputerTurn(JButton[][] buttons) {

        int rowIndex = 0;
        int colIndex = 0;
        if (cellsEmpty(buttons) > 0) {
            t1: for (int x=0; x <3; x++) {
                for (int y=0; y <3; y++) {
                    if (buttons[x][y].getText() == "") {
                        rowIndex = x;
                        colIndex = y;
                        if (nextStepWin(x,y,buttons,"O")) break t1;
                        if (nextStepWin(x,y,buttons,"X")) break t1;
                    }
                }
            }
            // Priority 1: X,Y coordinates lead to Robot´s win, if not:
            // Priority 2: X,Y coordinates lead to Human´s win, if not:
            // Priority 3: X,Y coordinates are an empty cell
            buttons[rowIndex][colIndex].setText("O");
        }
        else {
            endGame(buttons);
            textField.setText("Draw!");
        }
    }

    public boolean nextStepWin(int a, int b, JButton[][] buttons, String symbol) {
        buttons[a][b].setText(symbol);
        if (checkRows(symbol, buttons) || checkColumns(symbol, buttons) || checkDiagonals(symbol, buttons)) return true;
        else {
            buttons[a][b].setText("");
            return false;
        }
    }

    public static int cellsEmpty(JButton[][] buttons) {
        int cells = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (buttons[x][y].getText() == "") {
                    cells++;
                }
            }
        }
        return cells;
    }


}

