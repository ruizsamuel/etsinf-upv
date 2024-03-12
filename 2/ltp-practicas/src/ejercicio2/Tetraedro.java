package ejercicio2;

public class Tetraedro extends Poliedro {
    
    public Tetraedro (double x, double y, double a) {
        super(x,y,a);
    }
    
    public double area() {
        return Math.pow(getArista(),2) * Math.sqrt(3);
    }
    
    public double volumen() {
        return (Math.pow(getArista(),3) * Math.sqrt(2))/12;
    }
}