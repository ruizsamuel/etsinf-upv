package ejercicio1;

public class Comparador <E> {
    
    private E obj1, obj2;

    public Comparador(E obj1, E obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }
    
    public boolean compararPosiciones () {
        if (!(obj1 instanceof Posicion) || !(obj2 instanceof Posicion)) {return false;}
        
        Posicion o1 = (Posicion) obj1;
        Posicion o2 = (Posicion) obj2;
        
        if (o1.obtenerCoordenadaX() == o2.obtenerCoordenadaX() && o1.obtenerCoordenadaY() == o2.obtenerCoordenadaY()) {return true;}
        return false;
    }
    
}
