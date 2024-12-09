import java.util.Stack;
/**
 * Name:Brandyvie Mbuyi Mayombo
 * Class Group: sd2B
 */
public class Question10 {


    enum DIRECTION {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

        int dx, dy;

        DIRECTION(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }


    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }



    public static void display(int[][] image) {
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[0].length; y++) {
                System.out.printf("%4d", image[x][y]);
            }
            System.out.println();
        }
    }


    public void solve(int[][] maze, int startX, int startY) {
        Stack<Position> stack = new Stack<>();
        stack.push(new Position(startX, startY)); 

        while (!stack.isEmpty()) {
            Position current = stack.pop();
            int x = current.x, y = current.y;


            if (maze[x][y] == 9) {
                System.out.println("Exit found at (" + x + ", " + y + ")");
                return;
            }


            maze[x][y] = 2;


            for (DIRECTION dir : DIRECTION.values()) {
                int newX = x + dir.dx;
                int newY = y + dir.dy;
                if (isValidMove(maze, newX, newY)) {
                    stack.push(new Position(newX, newY));
                }
            }
        }

        System.out.println("No exit found.");
    }


    private boolean isValidMove(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && (maze[x][y] == 0 || maze[x][y] == 9);
    }


    public static void main(String[] args) {
        int[][] maze = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 0, 1, 1},
                {1, 0, 0, 9, 1},
                {1, 1, 1, 1, 1}
        };

        System.out.println("Maze:");
        display(maze);

        Question10 solver = new Question10();
        solver.solve(maze, 1, 1);

        System.out.println("\nMaze After Solving:");
        display(maze);
    }
}

