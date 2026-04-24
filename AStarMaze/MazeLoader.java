

public class MazeLoader {

    public static Node[][] buildNodes(String[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Node[][] nodes = new Node[rows][cols];

        Node start = null;
        Node goal = null;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                String value = grid[r][c].trim();
                Node node = new Node(r, c, value);

                nodes[r][c] = node;

                // 🎯 detect start & goal
                if (value.equals("S")) {
                    start = node;
                } else if (value.equals("G")) {
                    goal = node;
                }
            }
        }

        // ⚠️ Safety check
        if (start == null || goal == null) {
            throw new RuntimeException("Maze must contain S (start) and G (goal)");
        }

        return nodes;
    }
}