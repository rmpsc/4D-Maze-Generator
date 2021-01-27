public class Cell {

    int walls;

    // DEFAULT CONSTRUCTOR
    Cell() {
        walls = 1111;
    }

    // CONSTRUCTOR
    Cell(int walls) {
        this.walls = walls;
    }

    // GETTERS
    public int getWalls() { return walls; }

    // SETTERS
    public void setWalls(int walls) { this.walls = walls; }

    @Override
    public String toString() {
        return Integer.toBinaryString(walls);
        
    }
}
