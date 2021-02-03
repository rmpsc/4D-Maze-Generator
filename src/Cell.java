public class Cell {

    char name;
    int walls;
    int[] location;
    Cell parent;
    Cell[] neighbors;

    // DEFAULT CONSTRUCTOR
    Cell() {
        walls = 0b00001111;
    }

    // CONSTRUCTOR
    Cell(char name, int walls, int[] location, Cell parent) {
        this.name = name;
        this.walls = walls;
        this.location = location;
        this.parent = parent;
    }

    // GETTERS
    public int getWalls() { return walls; }

    public String getLocation() {
        return "[" + location[0] + ", " + location[1] + ", " + location[2] + ", " + location[3] + "]";
    }

    public Cell getParent() { return parent; }

    public char getName() { return name; }

    // SETTERS
    public void setWalls(int walls) { this.walls = walls; }

    public void setLocation(int[] location) { this.location = location; }

    public void setParent(Cell parent) { this.parent = parent; }

    public void setName(char name) { this.name = name; }

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
