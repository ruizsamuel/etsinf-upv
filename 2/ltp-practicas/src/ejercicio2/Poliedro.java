package ejercicio2;

public abstract class Poliedro extends Figura {
    private double arista;
    
    public Poliedro (double x, double y, double a) {
        super(x,y);
        this.arista = a;
    }
    
    public abstract double volumen();
    public abstract double area();
    
    public double getArista() {
        return arista;
    }
}