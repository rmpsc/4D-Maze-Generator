public class Cell {

    int walls;
    int[] location;

    // DEFAULT CONSTRUCTOR
    Cell() {
        walls = 0b00001111;
    }

    // CONSTRUCTOR
    Cell(int walls, int[] location) {
        this.walls = walls;
        this.location = location;
    }

    // GETTERS
    public int getWalls() { return walls; }

    public int[] getLocation() { return location; }

    // SETTERS
    public void setWalls(int walls) { this.walls = walls; }

    public void setLocation(int[] location) { this.location = location; }

    
    public void changeWall(int change) {
        // holds bit value to be checked
        int bit = (walls >> (change - 1)) & 1;
        System.out.println("bit before = " + bit);
        if (bit == 0) {
            walls = walls | (1 << (change - 1));
        }
        else {
            walls = walls & ~(1 << (change - 1));
        }
        bit = (walls >> (change - 1)) & 1;
        System.out.println("bit after = " + bit);
    }
    

    @Override
    public String toString() {
        return Integer.toString(walls);
        
    }
}
