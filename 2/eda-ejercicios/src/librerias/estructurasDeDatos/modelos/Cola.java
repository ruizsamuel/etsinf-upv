package librerias.estructurasDeDatos.modelos;

public interface Cola<E> {

    /** Inserta el Elemento e en una Cola, o lo sitúa en su final
     */
    void encolar(E e);
    
    /** SII !esVacia(): 
     * obtiene y elimina de una Cola el Elemento que ocupa su principio 
     */
    E desencolar();

    /** SII !esVacia(): 
     * obtiene el Elemento que ocupa el principio de una Cola,
     * el primero en orden de inserción
     */
    E primero();
    
    /** comprueba si una Cola esta vacia 
     */
    boolean esVacia();
}