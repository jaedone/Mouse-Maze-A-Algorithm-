package ui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JLabel currentNodeLabel, gLabel, hLabel, fLabel;

    public ControlPanel(MazePanel mazePanel) {

        setPreferredSize(new Dimension(200, 600));
        setBackground(new Color(25, 25, 25));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 15));

        // ===== TITLE =====
        JLabel title = new JLabel("A* Visualizer", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // ===== BUTTON CARD =====
        CardPanel buttonCard = new CardPanel("Controls");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setOpaque(false);

        JButton nextBtn = createButton("Next");
        JButton runBtn = createButton("Run");

        nextBtn.addActionListener(e -> mazePanel.stepAStar());
        runBtn.addActionListener(e -> mazePanel.runAStar());

        buttonPanel.add(nextBtn);
        buttonPanel.add(runBtn);

        buttonCard.add(buttonPanel, BorderLayout.CENTER);

        // ===== INFO CARD =====
        CardPanel infoCard = new CardPanel("Node Info");

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.setOpaque(false);

        currentNodeLabel = createLabel("Current: -");
        gLabel = createLabel("g: -");
        hLabel = createLabel("h: -");
        fLabel = createLabel("f: -");

        infoPanel.add(currentNodeLabel);
        infoPanel.add(gLabel);
        infoPanel.add(hLabel);
        infoPanel.add(fLabel);

        infoCard.add(infoPanel, BorderLayout.CENTER);

        // ===== LEGEND CARD =====
        CardPanel legendCard = new CardPanel("Legend");

        JPanel legendPanel = new JPanel(new GridLayout(5, 1));
        legendPanel.setOpaque(false);

        legendPanel.add(createLabel("🔵 Open Set"));
        legendPanel.add(createLabel("⚫ Closed Set"));
        legendPanel.add(createLabel("🟡 Current"));
        legendPanel.add(createLabel("🟢 Path"));
        legendPanel.add(createLabel("🔴 Nodes"));

        legendCard.add(legendPanel, BorderLayout.CENTER);

        // ===== STACK =====
        JPanel stack = new JPanel();
        stack.setLayout(new BoxLayout(stack, BoxLayout.Y_AXIS));
        stack.setOpaque(false);

        stack.add(buttonCard);
        stack.add(Box.createVerticalStrut(15));
        stack.add(infoCard);
        stack.add(Box.createVerticalStrut(15));
        stack.add(legendCard);

        add(title, BorderLayout.NORTH);
        add(stack, BorderLayout.CENTER);

        // Connect UI updater
        mazePanel.setUIUpdater(this);
    }

    public void updateNodeInfo(String name, double g, double h, double f) {
        currentNodeLabel.setText("Current: " + name);
        gLabel.setText("g: " + String.format("%.2f", g));
        hLabel.setText("h: " + String.format("%.2f", h));
        fLabel.setText("f: " + String.format("%.2f", f));
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return label;
    }
}