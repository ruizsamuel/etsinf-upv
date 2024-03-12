package practica1;
/**
 * class Circle.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class Circle extends Figure {
    private double radius;

    public Circle(double x, double y, double r) {
        super(x, y);
        radius = r;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Circle)) { return false; }
        if (!super.equals(o)) {return false;}
        Circle f = (Circle) o;
        return radius == f.radius;
    }

    public String toString() {
        return "Circle:\n\t" +
            super.toString() +
            "\n\tRadius: " + radius;
    }

	public double area() {		
		return Math.PI * radius * radius;
	}
	
	public double perimetre() {
		return Math.PI * 2 * radius;
	}
}