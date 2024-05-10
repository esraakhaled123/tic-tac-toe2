import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame menuFrame = new JFrame("Tic Tac Toe Menu");
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setSize(300, 200);
            menuFrame.setLayout(new FlowLayout());

            JButton guiButton = new JButton("Single Player");
            JButton aiButton = new JButton("Server Mode");
            JButton pvpButton = new JButton("Player vs Player Mode");

            menuFrame.add(guiButton);
            menuFrame.add(aiButton);
            menuFrame.add(pvpButton);

            guiButton.addActionListener(e -> {
                menuFrame.setVisible(false);
                new TicTacToeGUI().setVisible(true);
            });

            aiButton.addActionListener(e -> {
                menuFrame.setVisible(false);
                // Initialize GUI with AI mode
            });

            pvpButton.addActionListener(e -> {
                menuFrame.setVisible(false);
                new TicTacToeFrame().setVisible(true);
            });

            menuFrame.setVisible(true);
        });
    }
}
