package practica3;

public class Rectangle extends Figure implements ComparableToRange<Figure>, Printable {
	
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
	
	public int compareToRange(Figure o) {
		if (!(o instanceof Rectangle)) {throw new ClassCastException();}
		if (Math.abs(area() - o.area()) > (area() + o.area()) * 0.1) {
			return compareTo(o);
		} else {
			return 0;
		}
	}

	public void print(char c) {
		int b = (int) base;
		int h = (int) height;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < b; j++) {
					System.out.print(c);
			}
			System.out.println();
		}
	}
}