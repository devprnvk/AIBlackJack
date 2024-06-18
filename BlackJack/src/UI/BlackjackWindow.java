package UI;

import javax.swing.*;
import java.awt.*;

public class BlackjackWindow extends JFrame {
    private GamePanel gamePanel;
    private MessagePanel messagePanel;

    public BlackjackWindow() {
        setTitle("Blackjack");
        setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Create and add the MessagePanel
        messagePanel = new MessagePanel();
        add(messagePanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to update the message displayed in the MessagePanel
    public void updateMessage(String message) {
        messagePanel.setMessage(message);
    }

    // Method to update player's hand display in the GamePanel
    public void updatePlayerHand(String hand) {
        gamePanel.updatePlayerHand(hand);
    }

    // Method to update dealer's hand display in the GamePanel
    public void updateDealerHand(String hand) {
        gamePanel.updateDealerHand(hand);
    }

    // Method to reset the game panel
    public void resetGame() {
        gamePanel.reset();
    }

    // Main method to create and run the blackjack game window
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BlackjackWindow::new);
    }
}