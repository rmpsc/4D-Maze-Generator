import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class StudentSolver {
    public static void main(String[] args) {
        byte[] alienMaze = solve(40);
        System.out.println("done");
    }

    public static byte[] solve(int n) {
        
        int mazeSize = (int) Math.pow(n, 4);
        System.out.println("mazeSize = " + mazeSize);
        // array to fit all walls
        byte[] maze = new byte[mazeSize];
        // array to fit all cell objects
        Cell[] cells = new Cell[mazeSize];
        // hashmap (location : index)
        Map cellMap = new HashMap<>();
        // hashmap for linking direction with coordinate (dir : coord)
        Map coordMap = Map.of(1, 3, 2, 3, 3, 2, 4, 2, 5, 1, 6, 1, 7, 0, 8, 0);

        int index = 0;
        int nIndex = 0;
        int randDir = 0;
        int coordinate = 0;
        int wallState = 0;
        int wallsHit = 0;

        // makes all cells with proper locations
        for (int t = 0; t < n; t++) {
            for (int z = 0; z < n; z++) {
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {         
                        cells[index] = new Cell(0b11111111, null, t, z, y, x);
                        // points to itself
                        cells[index].setParent(cells[index]);
                        // allows cell to be reached by location
                        cellMap.put(t + ", " + z + ", " + y + ", " + x, index);

                        index += 1;
                    }
                }
            }
        }

        // starting cell
        Cell current = cells[0];
        current.parent = cells[1];

        while (wallsHit < mazeSize - 1) {
            // obtains wall inside boundaries
            // odd = pos dir    even = neg dir
            randDir = ThreadLocalRandom.current().nextInt(1, 9);
            coordinate = (int) coordMap.get(randDir);
            wallState = current.getWall(randDir);
    
            while ((current.getExactCoord(coordinate) == 0 && randDir % 2 == 0) || (current.getExactCoord(coordinate) == n - 1 && randDir % 2 == 1)) {
                randDir = ThreadLocalRandom.current().nextInt(1, 9);
                coordinate = (int) coordMap.get(randDir);
                wallState = current.getWall(randDir);
            }
            // obtains next cell
            String neigh = current.getNeighbor(randDir, coordinate);
            nIndex = (int) cellMap.get(neigh);

            // if wall is closed, check if you can break
            if (cells[nIndex].parent == cells[nIndex] && wallState == 1) {
                // break wall in cell
                current.changeWall(randDir);

                // must break neighbors wall as well
                if (randDir % 2 == 0) { cells[nIndex].changeWall(randDir - 1); }
                else { cells[nIndex].changeWall(randDir + 1); }

                // change neighbors parent to current cell
                cells[nIndex].parent = current;

                // increment walls hit
                wallsHit += 1;
            }
            // get next cell
            current = cells[nIndex];
        }

        // transfers walls to maze array
        for (int i = 0; i < mazeSize; i++) {
            maze[i] = (byte) cells[i].walls; 
        }

        return maze;
    }
}
