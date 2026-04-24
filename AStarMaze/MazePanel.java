import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazePanel extends JPanel {

    Node[][] grid;
    List<Node> path = new ArrayList<>();

    int size = 25;

    public MazePanel(Node[][] grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(
                grid[0].length * size,
                grid.length * size
        ));
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {

                Node n = grid[r][c];

                if (!n.walkable) g.setColor(Color.BLACK);
                else g.setColor(Color.WHITE);

                g.fillRect(c * size, r * size, size, size);

                if (path.contains(n)) {
                    g.setColor(Color.GREEN);
                    g.fillRect(c * size, r * size, size, size);
                }

                g.setColor(Color.GRAY);
                g.drawRect(c * size, r * size, size, size);
            }
        }
    }
}