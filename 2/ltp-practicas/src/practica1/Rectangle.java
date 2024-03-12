package practica1;

public class Rectangle extends Figure {
	
	private double base;
	private double height;

    public Rectangle(double x, double y, double b, double h) {
        super(x, y);
        base = b;
        height = b;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Rectangle)) { return false; }
        if (!super.equals(o)) { return false; }
        Rectangle f = (Rectangle) o;
        return base == f.base && height == f.height;
    }

    public String toString() {
    	if (base == height) { return "Square:\n\t" +
    			super.toString() +
                "\n\tLado: " + base ;
    	}
        return "Rectangle:\n\t" +
            super.toString() +
            "\n\tBase: " + base +
            "\n\tHeight: " + height;
    }

	public double area() {
		return base * height;
	}
	
	public double perimetre() {
		return (2 * base) + (2 * height);
	}
   
}