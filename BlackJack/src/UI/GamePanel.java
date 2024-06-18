package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private JLabel playerLabel;
    private JLabel dealerLabel;
    private BufferedImage woodTexture;
    private JPanel cardHolderPanel;
    private RoundRectangle2D[] cardHolders;

    private JLabel deckLabel;


    public GamePanel() {
        setLayout(new BorderLayout());

        playerLabel = new JLabel("Player's Hand:");
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER); // Move label text to the center
        playerLabel.setForeground(Color.WHITE); // Change label text color
        playerLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); // Change font and size
        add(playerLabel, BorderLayout.CENTER); // Add label to the center

        dealerLabel = new JLabel("Dealer's Hand:");
        dealerLabel.setHorizontalAlignment(SwingConstants.CENTER); // Move label text to the center
        dealerLabel.setForeground(Color.WHITE); // Change label text color
        dealerLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); // Change font and size
        add(dealerLabel, BorderLayout.NORTH); // Add label to the top

        cardHolders = new RoundRectangle2D[8]; // Extend to hold 8 rectangles

        // Set bounds for card placeholders for the first set
        int x1 = 200;
        int y1 = 60;
        int width = 60; // Adjusted width to accommodate rounded corners
        int height = 80; // Adjusted height to accommodate rounded corners
        for (int i = 0; i < 4; i++) {
            cardHolders[i] = new RoundRectangle2D.Double(x1 + i * (width + 10), y1, width, height, 10, 10); // Rounded corners with arc width and height both 10
        }

        // Set bounds for card placeholders for the second set
        int x2 = 200;
        int y2 = 320;
        for (int i = 4; i < 8; i++) {
            cardHolders[i] = new RoundRectangle2D.Double(x2 + (i - 4) * (width + 10), y2, width, height, 10, 10); // Rounded corners with arc width and height both 10
        }

        try {
            BufferedImage deckImage = ImageIO.read(new File("/Users/pkale123/IdeaProjects/BlackJack/src/cardd.png"));
            ImageIcon scaledDeckIcon = new ImageIcon(deckImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH)); // Adjust size of the image
            deckLabel = new JLabel(scaledDeckIcon);
            deckLabel.setBounds(200, 100, 100, 150);
            add(deckLabel, BorderLayout.EAST);


            // Rotate and resize the deck image
            Image rotatedDeckImage = rotateAndResizeImage(deckImage, Math.toRadians(45));
            ImageIcon rotatedDeckIcon = new ImageIcon(rotatedDeckImage);
            deckLabel.setIcon(rotatedDeckIcon);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            woodTexture = ImageIO.read(new File("/Users/pkale123/IdeaProjects/BlackJack/src/wood2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private Image rotateAndResizeImage(BufferedImage image, double angle) {
        int width = 50; // Adjusted width
        int height = 75; // Adjusted height
        double radians = Math.abs(angle);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        int rotatedWidth = (int) Math.floor(width * cos + height * sin);
        int rotatedHeight = (int) Math.floor(height * cos + width * sin);

        BufferedImage rotatedImage = new BufferedImage(rotatedWidth, rotatedHeight, image.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((rotatedWidth - width) / 2 , (rotatedHeight - height) / 2);
        at.rotate(angle, width / 2, height / 2);
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return rotatedImage;
    }
    // Method to update player's hand display
    public void updatePlayerHand(String hand) {
        playerLabel.setText("Player's Hand: " + hand);
    }

    // Method to update dealer's hand display
    public void updateDealerHand(String hand) {
        dealerLabel.setText("Dealer's Hand: " + hand);
    }

    // Method to reset the game panel
    public void reset() {
        playerLabel.setText("Player's Hand:");
        dealerLabel.setText("Dealer's Hand:");
    }

    // Override paintComponent to paint the gradient background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Create a 2-color gradient (dark green to black) from top to bottom
        float[] fractions = {0.0f, 0.5f, 1.0f};
        Color[] colors = {new Color(29, 82, 63), new Color(0, 114, 73), new Color(29, 82, 63)};

        // Create a gradient from left to right across the panel using the specified colors
        LinearGradientPaint gradient = new LinearGradientPaint(0, 0, getWidth(), 0, fractions, colors);

        // Create a Graphics2D object from the Graphics object
        Graphics2D g2d = (Graphics2D) g;

        // Set the paint to the gradient
        g2d.setPaint(gradient);

        // Paint the gradient onto the panel
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int curveWidth = getWidth(); // Set the width of the curve
        int curveHeight = 980; // Set the height of the curve

        // Create the curve shape using the specified width and height
        Path2D.Double curve = new Path2D.Double();
        curve.moveTo(-80, 385);
        curve.curveTo(0, 0, (curveWidth / 3) - 70, curveHeight, curveWidth , 280);
        curve.lineTo(curveWidth, curveHeight);
        curve.lineTo(0, curveHeight);
        curve.closePath();

        Path2D.Double curve2 = new Path2D.Double();
        curve2.moveTo(-80, 385);
        curve2.curveTo(0, 0, (curveWidth / 3) - 70, curveHeight - 60, curveWidth , 280);
        curve2.lineTo(curveWidth, curveHeight - 60);
        curve2.lineTo(0, curveHeight - 60);
        curve2.closePath();

        // Create a semi-transparent gradient
        GradientPaint woodGradient = new GradientPaint(0, 0, new Color(0, 0, 0, 150), 0, getHeight(), new Color(103, 30, 16, 165));

        if (woodTexture != null) {
            Rectangle bounds = new Rectangle(0, 0, woodTexture.getWidth(), woodTexture.getHeight());
            TexturePaint texturePaint = new TexturePaint(woodTexture, bounds);

            g2d.setPaint(texturePaint);
            g2d.fill(curve);
            g2d.fill(curve2);

            g2d.setPaint(woodGradient);
            g2d.fill(curve);
            g2d.fill(curve2);
        } else {
            g2d.setColor(new Color(94, 28, 14));
            g2d.fill(curve);
            g2d.fill(curve2);
        }


        // Paint card placeholders
        g.setColor(new Color(34, 82, 55));
        for (RoundRectangle2D cardHolder : cardHolders) {
            g.fillRoundRect((int) cardHolder.getX(), (int) cardHolder.getY(), (int) cardHolder.getWidth(), (int) cardHolder.getHeight(), 10, 10); // Rounded corners with arc width and height both 10
            g.setColor(new Color(31, 71, 48));
            g.drawRoundRect((int) cardHolder.getX(), (int) cardHolder.getY(), (int) cardHolder.getWidth(), (int) cardHolder.getHeight(), 10, 10); // Rounded corners with arc width and height both 10
            g.setColor(new Color(34, 82, 55));
        }
    }
}
