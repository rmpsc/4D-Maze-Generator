public class StudentSolver {
    public static void main(String[] args) {
        Cell kell = new Cell();

        System.out.println(kell);
        kell.changeWall(1);
        System.out.println(kell);
    }

    /*
    public static byte[] solve(int n) {
        return 0;
    }
    */

}
/*
BOUNDARY RULES
if coordinate == 0, you cant move in the negative direction
if coordinate == n, you cant move in the positive direction
*/
