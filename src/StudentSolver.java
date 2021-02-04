import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class StudentSolver {
    public static void main(String[] args) {
        byte[] alienMaze = solve(2);
        
        for (int i = 0; i < alienMaze.length; i++) {
            System.out.println("index[" + i + "] = " + Byte.toUnsignedInt(alienMaze[i]));
        }
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
                        cells[index] = new Cell('z', 0b11111111, new int[]{t, z, y, x}, null, 0);
                        // points to itself afters its created
                        cells[index].setParent(cells[index]);
                        // gives each cell a name
                        cells[index].setName((char) ('A' + index));
                        // assigns index
                        cells[index].setIndex(index);
                        // allows cell to be reached by location
                        cellMap.put(t + "" + z + "" + y + "" + x, index);
                        // System.out.println(cells[index].name);
                        // System.out.println(t + "" + z + "" + y + "" + x);
                        // System.out.println("index = " + index);
                        System.out.println(cells[index].getWalls());
                        index += 1;
                    }
                }
            }
        }

        // starting cell
        Cell current = cells[0];
        cells[0].parent = cells[1];

        while (wallsHit < mazeSize - 1) {
            // obtains wall inside boundaries
            // odd = pos dir    even = neg dir
            randDir = ThreadLocalRandom.current().nextInt(1, 9);
            coordinate = (int) coordMap.get(randDir);
            wallState = current.getWall(randDir);
            while ((current.location[coordinate] == 0 && randDir % 2 == 0) || (current.location[coordinate] == n - 1 && randDir % 2 == 1)) {
                randDir = ThreadLocalRandom.current().nextInt(1, 9);
                coordinate = (int) coordMap.get(randDir);
                wallState = current.getWall(randDir);
            }

            // obtains next cell
            nIndex = (int) cellMap.get(current.getNeighbor(randDir, coordinate));

            // if wall is closed, check if you can break
            // if wall is open, move to that cell
            if (wallState == 1 && cells[nIndex].parent == cells[nIndex]) {
                // break wall in cell
                current.changeWall(randDir);

                // must break neighbors wall as well
                if (randDir % 2 == 0) { cells[nIndex].changeWall(randDir - 1); }
                else { cells[nIndex].changeWall(randDir + 1); }

                // change neighbors parent to current cell
                cells[nIndex].parent = current;

                // increment walls hit
                wallsHit += 1;
                System.out.println("wallsHit = " + wallsHit);
            }

            current = cells[nIndex];

        }

        for (int i = 0; i < mazeSize; i++) {
            maze[i] = (byte) cells[i].walls;
            System.out.println(cells[i].getName() + " at " + cells[i].getLocation() + " = " + cells[i].getWalls());
        }
        // transfers walls to maze array
        System.out.println("transferring");
        for (int i = 0; i < mazeSize; i++) {
            maze[i] = (byte) cells[i].walls; 
        }

        return maze;
    }
}
