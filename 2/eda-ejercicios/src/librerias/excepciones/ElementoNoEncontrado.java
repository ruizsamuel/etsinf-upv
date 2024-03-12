package librerias.excepciones;

public class ElementoNoEncontrado extends Exception {
    public ElementoNoEncontrado(String mensaje) {
        super(mensaje);
    }    
    public ElementoNoEncontrado() { 
        super();
    }
}