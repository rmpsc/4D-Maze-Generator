public class StudentSolver {
    public static void main(String[] args) {
        byte[] alien = solve(2);
        
        for (int i = 0; i < alien.length; i++) {
            System.out.println(alien[i]);
        }
        
    }

    public static byte[] solve(int n) {
        int mazeSize = (int) Math.pow(n, 4);
        
        // array to fit all walls
        byte[] maze = new byte[mazeSize];

        // array to fit all cell objects
        Cell[] cells = new Cell[mazeSize];
        int loc = 0;

        // makes all cells with proper locations
        for (int t = 0; t < n; t++) {
            for (int z = 0; z < n; z++) {
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {         
                        cells[loc] = new Cell(0b11111111, new int[]{t, z, y, x});
                        loc += 1;
                    }
                }
            }
        }

        // transfers walls to maze array
        for (int i = 0; i < mazeSize; i++) {
            maze[i] = (byte) cells[i].walls;
        }
        

        return maze;
    }
    

}
/*
BOUNDARY RULES
if coordinate == 0, you cant move in the negative direction
if coordinate == n, you cant move in the positive direction
*/
