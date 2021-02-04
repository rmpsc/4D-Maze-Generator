import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class StudentSolver {
    public static void main(String[] args) {
        byte[] alienMaze = solve(40);
        /*
        for (int i = 0; i < alienMaze.length; i++) {
            System.out.println("index[" + i + "] = " + Byte.toUnsignedInt(alienMaze[i]));
        }
        */
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

        
        Queue<Integer> indexQueue = new LinkedList<>();

        int index = 0;
        int nIndex = 0;
        int randDir = 0;
        int coordinate = 0;
        int wallState = 0;
        int wallsHit = 0;
        int temp = 0;
        int exactCoord = 0;

        // makes all cells with proper locations
        for (int t = 0; t < n; t++) {
            for (int z = 0; z < n; z++) {
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {         
                        cells[index] = new Cell('z', 0b11111111, new int[]{0, 0, 0, 0}, null, 0);
                        // points to itself
                        cells[index].setParent(cells[index]);
                        // gives each cell a name
                        cells[index].setName((char) ('A' + index));
                        // assigns index
                        cells[index].setIndex(index);
                        // assigns locations
                        cells[index].setT(t);
                        cells[index].setZ(z);
                        cells[index].setY(y);
                        cells[index].setX(x);
                        // allows cell to be reached by location
                        cellMap.put(t + ", " + z + ", " + y + ", " + x, index);
                        //System.out.println(cells[index].getLocation());
                    
                        // throws index into queue
                        indexQueue.add(index);
                        index += 1;
                    }
                }
            }
        }
        // System.out.println(cellMap);

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
            //System.out.println("randDir = " + randDir);
            //System.out.println("coordinate = " + coordinate);
            //System.out.println("curr loc = " + current.getLocation());
            String neigh = current.getNeighbor(randDir, coordinate);
            //System.out.println("neigh = " + neigh);
            if (cellMap.get(neigh) == null) { 
                System.out.println("neigh = " + neigh);
                System.out.println(neigh + " not found");
            }
            nIndex = (int) cellMap.get(neigh);

            // if wall is closed, check if you can break
            // if wall is open, move to that cell
            if (cells[nIndex].parent == cells[nIndex] && wallState == 1) {
                // System.out.println("breaking wall at " + current.location[coordinate]);
                // break wall in cell
                current.changeWall(randDir);

                // must break neighbors wall as well
                if (randDir % 2 == 0) { cells[nIndex].changeWall(randDir - 1); }
                else { cells[nIndex].changeWall(randDir + 1); }

                // change neighbors parent to current cell
                cells[nIndex].parent = current;

                // increment walls hit
                wallsHit += 1;
        //System.out.println("wallsHit = " + wallsHit);

                // get next cell
                current = cells[nIndex];
            }
            else {
                //temp = indexQueue.remove();
                //indexQueue.add(temp);
                // if (wallsHit % 100 == 0) {System.out.println("looping else for " + temp);}
                //System.out.println("looping else for " + temp);
                current = cells[nIndex];
                //current = cells[indexQueue.peek()];
            }
        }
        /*
        for (int i = 0; i < mazeSize; i++) {
            maze[i] = (byte) cells[i].walls;
            System.out.println(cells[i].getName() + " at " + cells[i].getLocation() + " = " + cells[i].getWalls());
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
