package ui;

import model.*;
import algorithm.AStar;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import javax.imageio.ImageIO;

public class MazePanel extends JPanel {

    BufferedImage mazeImage;

    List<Node> nodes = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    private AStar astar;
    private Node startNode;
    private Node goalNode;
    private ControlPanel uiUpdater;

    public MazePanel() {
        loadMazeImage();
        initializeGraph();
        initializeAStar();

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                System.out.println("Node -> (" + x + ", " + y + ")");
            }
        });
    }

    private void loadMazeImage() {
        try {
            mazeImage = ImageIO.read(new File("resources/maze.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeGraph() {
        initializeNodes();
        initializeEdges();
        assignHeuristics();
    }

    private void initializeNodes() {
        nodes.add(new Node("1", 85, 526));
        nodes.add(new Node("2", 34, 526));
        nodes.add(new Node("3", 85, 460));
        nodes.add(new Node("4", 265, 460));
        nodes.add(new Node("5", 264, 529));
        nodes.add(new Node("6", 34, 316));
        nodes.add(new Node("7", 34, 201));
        nodes.add(new Node("8", 95, 316));
        nodes.add(new Node("9", 95, 388));
        nodes.add(new Node("10", 179, 388));
        nodes.add(new Node("11", 262, 388));
        nodes.add(new Node("12", 179, 250));
        nodes.add(new Node("13", 91, 250));
        nodes.add(new Node("14", 92, 201));
        nodes.add(new Node("15", 92, 150));
        nodes.add(new Node("16", 33, 150));
        nodes.add(new Node("17", 33, 30));
        nodes.add(new Node("18", 150, 201));
        nodes.add(new Node("19", 149, 94));
        nodes.add(new Node("20", 91, 94));
        nodes.add(new Node("21", 88, 31));
        nodes.add(new Node("22", 271, 32));
        nodes.add(new Node("23", 152, 31));
        nodes.add(new Node("24", 158, 529));
        nodes.add(new Node("25", 397, 531));
        nodes.add(new Node("26", 397, 491));
        nodes.add(new Node("27", 324, 491));
        nodes.add(new Node("28", 324, 436));
        nodes.add(new Node("29", 324, 388));
        nodes.add(new Node("30", 395, 388));
        nodes.add(new Node("31", 394, 298));
        nodes.add(new Node("32", 468, 436));
        nodes.add(new Node("33", 534, 436));
        nodes.add(new Node("34", 534, 523));
        nodes.add(new Node("35", 464, 521));
        nodes.add(new Node("36", 467, 378));
        nodes.add(new Node("37", 467, 325));
        nodes.add(new Node("38", 532, 378));
        nodes.add(new Node("39", 532, 272));
        nodes.add(new Node("40", 469, 272));
        nodes.add(new Node("41", 469, 137));
        nodes.add(new Node("42", 469, 38));
        nodes.add(new Node("43", 532, 36));
        nodes.add(new Node("44", 531, 219));
        nodes.add(new Node("45", 318, 38));
        nodes.add(new Node("46", 318, 84));
        nodes.add(new Node("47", 394, 137));
        nodes.add(new Node("48", 394, 92));
        nodes.add(new Node("49", 394, 242));
        nodes.add(new Node("50", 323, 142));
        nodes.add(new Node("51", 327, 242));
        nodes.add(new Node("52", 327, 313));
        nodes.add(new Node("53", 275, 316));
        nodes.add(new Node("54", 271, 203));
        nodes.add(new Node("55", 271, 144));
        nodes.add(new Node("56", 210, 143));
        nodes.add(new Node("57", 210, 84));
    }

    private Node getNode(String name) {
        for (Node n : nodes) {
            if (n.name.equals(name))
                return n;
        }
        return null;
    }

    private void initializeEdges() {
        edges.add(new Edge(getNode("1"), getNode("3"), 3));
        edges.add(new Edge(getNode("3"), getNode("4"), 8));
        edges.add(new Edge(getNode("4"), getNode("5"), 3));
        edges.add(new Edge(getNode("4"), getNode("11"), 3));
        edges.add(new Edge(getNode("11"), getNode("10"), 4));
        edges.add(new Edge(getNode("10"), getNode("12"), 8));
        edges.add(new Edge(getNode("1"), getNode("2"), 2));
        edges.add(new Edge(getNode("2"), getNode("6"), 35));
        edges.add(new Edge(getNode("6"), getNode("7"), 15));
        edges.add(new Edge(getNode("6"), getNode("8"), 4));
        edges.add(new Edge(getNode("8"), getNode("9"), 8));
        edges.add(new Edge(getNode("9"), getNode("10"), 10));
        edges.add(new Edge(getNode("12"), getNode("13"), 6));
        edges.add(new Edge(getNode("13"), getNode("14"), 2));
        edges.add(new Edge(getNode("14"), getNode("18"), 3));
        edges.add(new Edge(getNode("14"), getNode("15"), 4));
        edges.add(new Edge(getNode("15"), getNode("16"), 3));
        edges.add(new Edge(getNode("16"), getNode("17"), 5));
        edges.add(new Edge(getNode("18"), getNode("19"), 10));
        edges.add(new Edge(getNode("19"), getNode("20"), 4));
        edges.add(new Edge(getNode("19"), getNode("23"), 3));
        edges.add(new Edge(getNode("20"), getNode("21"), 4));
        edges.add(new Edge(getNode("5"), getNode("25"), 5));
        edges.add(new Edge(getNode("25"), getNode("26"), 1));
        edges.add(new Edge(getNode("23"), getNode("22"), 7));
        edges.add(new Edge(getNode("26"), getNode("27"), 2));
        edges.add(new Edge(getNode("28"), getNode("29"), 3));
        edges.add(new Edge(getNode("28"), getNode("32"), 8));
        edges.add(new Edge(getNode("29"), getNode("30"), 2));
        edges.add(new Edge(getNode("30"), getNode("31"), 2));
        edges.add(new Edge(getNode("32"), getNode("33"), 2));
        edges.add(new Edge(getNode("33"), getNode("34"), 3));
        edges.add(new Edge(getNode("34"), getNode("35"), 2));
        edges.add(new Edge(getNode("32"), getNode("36"), 2));
        edges.add(new Edge(getNode("36"), getNode("37"), 2));
        edges.add(new Edge(getNode("27"), getNode("28"), 2));
        edges.add(new Edge(getNode("36"), getNode("38"), 2));
        edges.add(new Edge(getNode("38"), getNode("39"), 4));
        edges.add(new Edge(getNode("39"), getNode("40"), 1));
        edges.add(new Edge(getNode("40"), getNode("41"), 5));
        edges.add(new Edge(getNode("41"), getNode("42"), 3));
        edges.add(new Edge(getNode("42"), getNode("43"), 2));
        edges.add(new Edge(getNode("43"), getNode("44"), 10));
        edges.add(new Edge(getNode("42"), getNode("45"), 10));
        edges.add(new Edge(getNode("45"), getNode("46"), 2));
        edges.add(new Edge(getNode("41"), getNode("47"), 3));
        edges.add(new Edge(getNode("47"), getNode("48"), 1));
        edges.add(new Edge(getNode("47"), getNode("49"), 6));
        edges.add(new Edge(getNode("49"), getNode("51"), 2));
        edges.add(new Edge(getNode("51"), getNode("50"), 6));
        edges.add(new Edge(getNode("51"), getNode("52"), 2));
        edges.add(new Edge(getNode("18"), getNode("54"), 8));
        edges.add(new Edge(getNode("54"), getNode("55"), 2));
        edges.add(new Edge(getNode("55"), getNode("56"), 5));
        edges.add(new Edge(getNode("55"), getNode("50"), 5));
        edges.add(new Edge(getNode("53"), getNode("52"), 2));
        edges.add(new Edge(getNode("53"), getNode("54"), 6));
        edges.add(new Edge(getNode("46"), getNode("57"), 9));
    }

    private void assignHeuristics() {
        getNode("42").h = 0;

        getNode("1").h = 120;
        getNode("2").h = 130;
        getNode("3").h = 110;
        getNode("4").h = 100;
        getNode("5").h = 95;

        getNode("6").h = 140;
        getNode("7").h = 150;
        getNode("8").h = 120;
        getNode("9").h = 110;
        getNode("10").h = 95;
        getNode("11").h = 85;

        getNode("12").h = 100;
        getNode("13").h = 110;
        getNode("14").h = 120;
        getNode("15").h = 130;

        getNode("16").h = 140;
        getNode("17").h = 150;

        getNode("18").h = 90;
        getNode("19").h = 80;
        getNode("20").h = 90;

        getNode("21").h = 100;
        getNode("22").h = 70;
        getNode("23").h = 75;

        getNode("24").h = 110;
        getNode("25").h = 85;
        getNode("26").h = 80;
        getNode("27").h = 75;
        getNode("28").h = 70;
        getNode("29").h = 65;
        getNode("30").h = 60;
        getNode("31").h = 55;

        getNode("32").h = 45;
        getNode("33").h = 40;
        getNode("34").h = 35;
        getNode("35").h = 30;

        getNode("36").h = 25;
        getNode("37").h = 20;
        getNode("38").h = 18;
        getNode("39").h = 15;
        getNode("40").h = 12;

        getNode("41").h = 8;

        getNode("43").h = 5;
        getNode("44").h = 10;

        getNode("45").h = 20;
        getNode("46").h = 25;

        getNode("47").h = 10;
        getNode("48").h = 12;
        getNode("49").h = 15;

        getNode("50").h = 20;
        getNode("51").h = 18;
        getNode("52").h = 22;

        getNode("53").h = 25;
        getNode("54").h = 30;
        getNode("55").h = 35;
        getNode("56").h = 40;
        getNode("57").h = 45;
    }

    private void initializeAStar() {
        startNode = getNode("1");
        goalNode = getNode("42");

        astar = new AStar(startNode, goalNode, edges);
    }

    public void stepAStar() {
        if (astar != null) {
            astar.step();

            if (astar.current != null && uiUpdater != null) {
                uiUpdater.updateNodeInfo(
                        astar.current.name,
                        astar.current.g,
                        astar.current.h,
                        astar.current.f);
            }

            repaint();
        }
    }

    public void runAStar() {
        Timer timer = new Timer(300, e -> {
            if (!astar.finished) {
                astar.step();
                repaint();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (mazeImage != null) {
            g2.drawImage(mazeImage, 0, 0, getWidth(), getHeight(), this);
        }

        drawEdges(g2);
        drawNodes(g2);
        drawAStar(g2);
    }

    private void drawNodes(Graphics2D g2) {

        int size = 18; // 👈 bigger nodes

        for (Node n : nodes) {

            // 🟤 Brown node
            g2.setColor(new Color(139, 69, 19));
            g2.fillOval(n.x - size / 2, n.y - size / 2, size, size);

            // ⚫ Black border
            g2.setColor(Color.BLACK);
            g2.drawOval(n.x - size / 2, n.y - size / 2, size, size);

            // 🎯 CENTERED TEXT
            String text = String.valueOf((int) n.h);

            g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
            FontMetrics fm = g2.getFontMetrics();

            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();

            int textX = n.x - textWidth / 2;
            int textY = n.y + textHeight / 2 - 2;

           
            // 🔵 text color (high contrast)
            g2.setColor(Color.WHITE);
            g2.drawString(text, textX, textY);
        }
    }

    private void drawEdges(Graphics2D g2) {

        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(180, 180, 180));

        for (Edge e : edges) {
            g2.drawLine(e.from.x, e.from.y, e.to.x, e.to.y);

            int midX = (e.from.x + e.to.x) / 2;
            int midY = (e.from.y + e.to.y) / 2;

            g2.setColor(Color.BLACK);
            g2.drawString(String.valueOf((int) e.cost), midX, midY);

            g2.setColor(new Color(180, 180, 180));
        }

        g2.setStroke(new BasicStroke(1)); // reset
    }

    public void setUIUpdater(ControlPanel panel) {
        this.uiUpdater = panel;
    }

    private void drawAStar(Graphics2D g2) {
        if (astar == null)
            return;

        // Closed set
        g2.setColor(new Color(120, 120, 120));
        for (Node n : astar.closedSet) {
            g2.fillOval(n.x - 6, n.y - 6, 12, 12);
        }

        // Open set
        g2.setColor(new Color(70, 130, 255));
        for (Node n : astar.openSet) {
            g2.fillOval(n.x - 6, n.y - 6, 12, 12);
        }

        // Current node
        if (astar.current != null) {
            g2.setColor(Color.YELLOW);
            g2.fillOval(astar.current.x - 8, astar.current.y - 8, 16, 16);
        }

        // 🟢 Final path (THICK)
        if (astar.finished) {
            g2.setStroke(new BasicStroke(4)); // 👈 thick path
            g2.setColor(new Color(0, 200, 0));

            Node temp = goalNode;
            while (temp.parent != null) {
                g2.drawLine(temp.x, temp.y, temp.parent.x, temp.parent.y);
                temp = temp.parent;
            }

            g2.setStroke(new BasicStroke(1)); // reset
        }
    }
}