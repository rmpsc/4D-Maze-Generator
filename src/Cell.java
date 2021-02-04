public class Cell {

    char name;
    int walls;
    int[] location;
    Cell parent;
    int index;
    int t;
    int z;
    int y;
    int x;

    // DEFAULT CONSTRUCTOR
    Cell() {
        walls = 0b00001111;
    }

    // CONSTRUCTOR
    Cell(char name, int walls, int[] location, Cell parent, int index) {
        this.name = name;
        this.walls = walls;
        this.location = location;
        this.parent = parent;
        this.index = index;
    }

    // GETTERS
    public int getWalls() { return walls; }

    public int getWall(int dir) {
        return (walls >> (dir - 1)) & 1;
    }

    public String getLocation() {
        return t + ", " + z + ", " + y + ", " + x;
    }

    public String getNeighbor(int direction, int coordinate) {
        switch (coordinate) {
            case 0:
                if (direction % 2 == 0) { return (t - 1) + ", " + z + ", " + y + ", " + x; }
                else { return (t + 1) + ", " + z + ", " + y + ", " + x; }
            case 1:
                if (direction % 2 == 0) { return t + ", " + (z - 1) + ", " + y + ", " + x; }
                else { return t + ", " + (z + 1) + ", " + y + ", " + x; }
            case 2:
                if (direction % 2 == 0) { return t + ", " + z + ", " + (y - 1) + ", " + x; }
                else { return t + ", " + z + ", " + (y + 1) + ", " + x; }
            case 3:
                if (direction % 2 == 0) { return t + ", " + z + ", " + y + ", " + (x - 1); }
                else { return t + ", " + z + ", " + y + ", " + (x + 1); }
            default:
                return "default switch case";
        }
    }

    public int getExactCoord(int coord) {
        if (coord == 0) { return t; }
        if (coord == 1) { return z; }
        if (coord == 2) { return y; }
        if (coord == 3) { return x; }
        return 0;
    }

    public Cell getParent() { return parent; }

    public char getName() { return name; }

    public int getT() { return t; }
    public int getZ() { return z; }
    public int getY() { return y; }
    public int getX() { return x; }
    public void setT(int t) { this.t = t; }
    public void setZ(int z) { this.z = z; }
    public void setY(int y) { this.y = y; }
    public void setX(int x) { this.x = x; }
    // SETTERS
    public void setWalls(int walls) { this.walls = walls; }

    public void setLocation(int[] location) { this.location = location; }

    public void setParent(Cell parent) { this.parent = parent; }

    public void setName(char name) { this.name = name; }

    public void setIndex(int index) { this.index = index; }

    public void changeWall(int change) {
        walls = walls & ~(1 << (change - 1));
    }
    
    @Override
    public String toString() {
        return Integer.toString(walls);
        
    }
}
