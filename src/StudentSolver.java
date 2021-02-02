import java.util.HashMap;
import java.util.Map;

public class StudentSolver {
    public static void main(String[] args) {
        byte[] alienMaze = solve(2);
        
        for (int i = 0; i < alienMaze.length; i++) {
            System.out.println(alienMaze[i]);
        }

    }

    public static byte[] solve(int n) {
        int mazeSize = (int) Math.pow(n, 4);

        // array to fit all walls
        byte[] maze = new byte[mazeSize];

        // array to fit all cell objects
        Cell[] cells = new Cell[mazeSize];
        int index = 0;

        // hashmap loc:index
        Map cellMap = new HashMap<>();

        // makes all cells with proper locations
        for (int t = 0; t < n; t++) {
            for (int z = 0; z < n; z++) {
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {         
                        cells[index] = new Cell('z', 0b11111111, new int[]{t, z, y, x}, null);
                        // points to itself afters its created
                        cells[index].setParent(cells[index]);
                        
                        // gives each cell a name
                        cells[index].setName((char) ('A' + index));

                        // location : index
                        // hashmap loc:index
                        cellMap.put(t + "" + z + "" + y + "" + x, index);
                        System.out.println(cells[index].name);
                        System.out.println(t + "" + z + "" + y + "" + x);
                        System.out.println("index = " + index);
                        index += 1;
                    }
                }
            }
        }


        // starting cell
        Cell start = cells[1];
        System.out.println(start.getLocation());
        System.out.println(start.getWalls());

        int testIndex = (int) cellMap.get("0011");
        System.out.println("testIndex = " + testIndex);



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

LOOP
if cell.parent == itself, you've found the loop

MULTIDIMENSIONAL ARRAY
*/
