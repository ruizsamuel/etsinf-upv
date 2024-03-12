package ejercicio2;

public class Hexaedro extends Poliedro {
    
    public Hexaedro (double x, double y, double a) {
        super(x,y,a);
    }
    
    public double area() {
        return Math.pow(getArista(), 2) * 6;
    }
    
    public double volumen() {
        return Math.pow(getArista(), 3);
    }
}
