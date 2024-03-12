package practica3;
/** Ana 21-22
 * Application entry point
 * 
 * @author CSD Juansa Sendra, ruizsamuel
 * @version 2021
 */
public class Ants
{
     static int readArg(String[] args, int i, int def, int min, int max) {
        if (i>=args.length) return def;
        int r = Integer.parseInt(args[i]); return r < min ? min: Math.min(r, max);
    }
    /* ARGUMENTS
     * type of Terrain (0..3, default 0)
         *   Terrain0: native monitor
         *   Terrain1: general monitor, 1 general condition
         *   Terrain2: general monitor, one condition per cell
         *   Terrain3: general monitor, one condition per cell, no deadlock
         * Size of terrain (side 6..10, default 8)
         * Number of ants  (10..26, default 15)
         * Number of moves per ant (2..5, default 3)
     */  
    public static void main(String[] args) {
        int T = readArg(args,1, 8, 6,10); //size of Terrain
        int A = readArg(args,2,15,10,26); // number of ants
        int M = readArg(args,3, 3, 2, 5);  //movs per ant
        Terrain t = switch (readArg(args, 0, 0, 0, 3)) {
            case 0 -> new Terrain0(T, A, M, "0.- Basic monitor:");
            case 1 -> new Terrain1(T, A, M, "1.- General monitor, 1 cond ."); // must be Terrain1 TO BE IMPLEMENTED
            case 2 -> new Terrain2(T, A, M, "2.- GMonitor, 1 cond/cell:"); // must be Terrain2 TO BE IMPLEMENTED
            case 3 -> new Terrain3(T, A, M, "3.- GMonitor, 1 cond/cell, no deadlock:");
            default -> null;        // must be Terrain3 TO BE IMPLEMENTED
        };
        for (int i=0; i<A; i++) new Ant(i,t,M).start();
    }
}
