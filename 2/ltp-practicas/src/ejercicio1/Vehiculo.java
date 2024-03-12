package ejercicio1;

public abstract class Vehiculo implements Posicion {
    
    private double x, y;
    
    public Vehiculo (double coordX, double coordY) {
        this.x = coordX;
        this.y = coordY;
    }
    
    public double obtenerCoordenadaX(){
        return x;
    }
    
    public double obtenerCoordenadaY(){
        return y;
    }
    
}
