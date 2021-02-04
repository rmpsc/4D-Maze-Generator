public class Cell {

    char name;
    int walls;
    int[] location;
    Cell parent;
    int index;

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
        return location[0] + "" + location[1] + "" + location[2] + "" + location[3];
    }

    public String getNeighbor(int direction, int coordinate) {
        switch (coordinate) {
            case 0:
                if (direction % 2 == 0) { return (location[0] - 1) + "" + location[1] + "" + location[2] + "" + location[3]; }
                else { return (location[0] + 1) + "" + location[1] + "" + location[2] + "" + location[3]; }
            case 1:
                if (direction % 2 == 0) { return location[0] + "" + (location[1] - 1) + "" + location[2] + "" + location[3]; }
                else { return location[0] + "" + (location[1] + 1) + "" + location[2] + "" + location[3]; }
            case 2:
                if (direction % 2 == 0) { return location[0] + "" + location[1] + "" + (location[2] - 1) + "" + location[3]; }
                else { return location[0] + "" + location[1] + "" + (location[2] + 1) + "" + location[3]; }
            case 3:
                if (direction % 2 == 0) { return location[0] + "" + location[1] + "" + location[2] + "" + (location[3] - 1); }
                else { return location[0] + "" + location[1] + "" + location[2] + "" + (location[3] + 1); }
            default:
                return "default switch case";
        }
    }

    public Cell getParent() { return parent; }

    public char getName() { return name; }

    // SETTERS
    public void setWalls(int walls) { this.walls = walls; }

    public void setLocation(int[] location) { this.location = location; }

    public void setParent(Cell parent) { this.parent = parent; }

    public void setName(char name) { this.name = name; }

    public void setIndex(int index) { this.index = index; }

    public void changeWall(int change) {
        // holds bit value to be checked
        int bit = (walls >> (change - 1)) & 1;
        // System.out.println("bit before = " + bit);
        
        walls = walls & ~(1 << (change - 1));
        
        bit = (walls >> (change - 1)) & 1;
        // System.out.println("bit after = " + bit);
    }
    

    @Override
    public String toString() {
        return Integer.toString(walls);
        
    }
}
