
package UI;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private JLabel messageLabel;

    public MessagePanel() {
        setLayout(new FlowLayout());
        setBackground(new Color(253, 255, 253));
        messageLabel = new JLabel("Welcome to Blackjack!");
        messageLabel.setForeground(Color.BLACK);
        add(messageLabel);
    }

    // Method to update the message displayed
    public void setMessage(String message) {
        messageLabel.setText(message);
    }


}