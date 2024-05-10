
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class TicTacToeGUI extends JFrame {
    private char[][] board = new char[3][3];
    private JButton[][] buttons = new JButton[3][3];
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem singlePlayerMode;
    private JMenuItem serverMode;
    

    public TicTacToeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        // Initialize the board and buttons
        initializeBoard();
        initializeButtons();
        initializeMenu();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                add(buttons[i][j]);
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        if (clickedButton.getText().equals("")) {
                            clickedButton.setText("X");
                            clickedButton.setEnabled(false);
                            board[getButtonRow(clickedButton)][getButtonCol(clickedButton)] = 'X';
                            if (!checkWin()) {
                                computerTurn();
                            }
                        }
                    }
                });
            }
        }
    }
    private void initializeMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        singlePlayerMode = new JMenuItem("Single Player Mode");
        serverMode = new JMenuItem("Server Mode");

        menu.add(singlePlayerMode);
        menu.add(serverMode);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        singlePlayerMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSinglePlayerMode();
            }
        });

        serverMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServerMode();
            }
        });
    }

    private void startSinglePlayerMode() {
        // Placeholder for single player mode logic
        JOptionPane.showMessageDialog(null, "Single Player Mode selected.");
    }

    private void startServerMode() {
        // Placeholder for server mode logic
        JOptionPane.showMessageDialog(null, "Server Mode selected.");
    }

    private int getButtonRow(JButton button) {
        // Determine the row of the button in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == button) {
                    return i;
                }
            }
        }
        return -1; // Should never happen
    }

    private int getButtonCol(JButton button) {
        // Determine the column of the button in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == button) {
                    return j;
                }
            }
        }
        return -1; // Should never happen
    }

    private void computerTurn() {
        // Simple AI to make the computer move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    buttons[i][j].setText("O");
                    buttons[i][j].setEnabled(false);
                    board[i][j] = 'O';
                    return;
                }
            }
        }
    }

    private char checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        // No winner yet
        return ' ';
    }

    private boolean checkWin() {
        char winner = checkWinner();
        if (winner == 'X') {
            JOptionPane.showMessageDialog(null, "Congratulations! You've won!");
            playWinningVideo();
            return true;
        } else if (winner == 'O') {
            JOptionPane.showMessageDialog(null, "You lost. Try again!");
            playLosingVideo();
            return true;
        }
        // Check for a draw
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    draw = false; // There's still an empty spot
                    break;
                }
            }
            if (!draw) {
                break;
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(null, "It's a draw!");
            return true;
        }
        return false; // No one has won and it's not a draw
    }

    public void playWinningVideo() {
        try {
            File videoFile = new File("E:\\sefo\\Tom and Jerry 2018 Ball Tom Cartoon For Kids.mp4"); // Replace with the actual video file path
            Desktop.getDesktop().open(videoFile);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sorry, the bonus video could not be played.");
        }
    }

    public void playLosingVideo() {
        try {
            File videoFile = new File("E:\\sefo\\Tom & Jerry Tuffy, the Cutest Classic Cartoon Compilation @WB Kids.mp4"); // Replace with the actual losing video file path
            Desktop.getDesktop().open(videoFile);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sorry, the losing video could not be played.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create a simple initial menu frame to choose the game mode
            JFrame initialMenu = new JFrame("Tic Tac Toe - Choose Mode");
            initialMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initialMenu.setSize(200, 100);
            initialMenu.setLayout(new FlowLayout());

            JButton singlePlayerButton = new JButton("Single Player");
            JButton serverModeButton = new JButton("Server Mode");

            initialMenu.add(singlePlayerButton);
            initialMenu.add(serverModeButton);

            initialMenu.setVisible(true);

            singlePlayerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialMenu.setVisible(false);
                    new TicTacToeGUI().setVisible(true);
                }
            });

            serverModeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialMenu.setVisible(false);
                    // Here you would initialize your server mode
                    JOptionPane.showMessageDialog(null, "Server Mode selected.");
                }
            });
        }
    });
}
}
   
