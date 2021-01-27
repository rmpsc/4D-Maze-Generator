public class StudentSolver {
    public static void main(String[] args) {
        Cell kell = new Cell(1111);
        System.out.println(kell);

    }

    
    public static byte[] solve(int n) {
        
        byte[] cells = new byte[n];
        Cell kell = new Cell(1111);
        cells[0] = kell.getxNeg();
        return cells;
        
    }
    
  
}
/*
BOUNDARY RULES
if coordinate == 0, you cant move in the negative direction
if coordinate == n, you cant move in the positive direction
*/
