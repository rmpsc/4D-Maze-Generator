import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class StudentSolver {
    public static void main(String[] args) {
        byte[] alienMaze = solve(3);
        
        for (int i = 0; i < alienMaze.length; i++) {
            // System.out.println(alienMaze[i]);
        }
        System.out.println("done");

    }

    public static byte[] solve(int n) {
        int mazeSize = (int) Math.pow(n, 4);
        // array to fit all walls
        byte[] maze = new byte[mazeSize];
        // array to fit all cell objects
        Cell[] cells = new Cell[mazeSize];
        // hashmap (location : index)
        Map cellMap = new HashMap<>();
        // hashmap for linking direction with coordinate (dir : coord)
        Map coordMap = Map.of(
            1, 3,
            2, 3,
            3, 2,
            4, 2,
            5, 1,
            6, 1,
            7, 0,
            8, 0
        );

        int index = 0;

        // makes all cells with proper locations
        for (int t = 0; t < n; t++) {
            for (int z = 0; z < n; z++) {
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {         
                        cells[index] = new Cell('z', 0b11110111, new int[]{t, z, y, x}, null);
                        // points to itself afters its created
                        cells[index].setParent(cells[index]);
                        
                        // gives each cell a name
                        cells[index].setName((char) ('A' + index));

                        // allows cell to be reached by location
                        // location : index
                        cellMap.put(t + "" + z + "" + y + "" + x, index);
                        // System.out.println(cells[index].name);
                        // System.out.println(t + "" + z + "" + y + "" + x);
                        // System.out.println("index = " + index);
                        index += 1;
                    }
                }
            }
        }

        // starting cell
        Cell current = cells[40];

        // obtains wall inside boundaries
        // odd = pos dir    even = neg dir
        int randDir = ThreadLocalRandom.current().nextInt(1, 9);
        int coordinate = (int) coordMap.get(randDir);
        while ((current.location[coordinate] == 0 && randDir % 2 == 0) || (current.location[coordinate] == n - 1 && randDir % 2 == 1)) {
            randDir = ThreadLocalRandom.current().nextInt(1, 9);
            coordinate = (int) coordMap.get(randDir);
        }
        System.out.println("randDir = " + randDir);
        System.out.println("coordinate = " + coordinate);
        current.changeWall(randDir);

        /*
        NEIGHBOR CODE
        // get neighbors of starting cell making sure to skip boundaries
        // for every var tzyx, if valid add to neighbors
        for (int i = 0; i < 4; i++) {
            if (current.location[i] == 0) {
                if (i == 0) { int nIndex = (int) cellMap.get(current.location[i] + 1 + "" + current.location[1] + "" + current.location[2] + "" + current.location[3]); }

            }
        }
        */

        // transfers walls to maze array
        System.out.println("transferring");
        for (int i = 0; i < mazeSize; i++) {
            maze[i] = (byte) cells[i].walls;
        }


        return maze;
    }
    

}
