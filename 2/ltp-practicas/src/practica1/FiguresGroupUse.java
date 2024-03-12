package practica1;
/**
 * class FiguresGroupUse.
 * 
 * @author LTP 
 * @version 2020-21
 */
public class FiguresGroupUse {
    public static void main(String[] args) {
        FiguresGroup g = new FiguresGroup();
        g.add(new Circle(10, 5, 3.5));
        g.add(new Triangle(10, 5, 6.5, 32));
        
        //Iguales a g
        FiguresGroup f = new FiguresGroup();
        f.add(new Circle(10, 5, 3.5));
        f.add(new Triangle(10, 5, 6.5, 32));
        FiguresGroup r = new FiguresGroup();
        r.add(new Triangle(10, 5, 6.5, 32));
        r.add(new Circle(10, 5, 3.5));
        
        //Diferentes de g
        FiguresGroup h = new FiguresGroup();
        h.add(new Triangle(10, 5, 6.5, 32));
        h.add(new Circle(10, 5, 3.5));
        FiguresGroup j = new FiguresGroup();
        j.add(new Triangle(11, 5, 6.5, 32));
        j.add(new Circle(10, 5, 3.5));
        j.add(new Circle(10, 5, 3.5));
        
        System.out.println(g.equals(f));
        System.out.println(g.equals(r));
        System.out.println(g.equals(h));
        System.out.println(g.equals(j));
        
        System.out.println(g);
    }
}