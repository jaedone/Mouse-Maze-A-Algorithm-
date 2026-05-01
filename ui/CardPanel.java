package ui;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {

    private String title;

    public CardPanel(String title) {
        this.title = title;
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // Card background
        g2.setColor(new Color(45, 45, 45));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Title
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(title, 15, 20);

        super.paintComponent(g);
    }
}