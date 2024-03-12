package librerias.estructurasDeDatos.modelos;

public interface Pila<E> {

    /** Inserta el Elemento e en una Pila, o lo sitúa en su tope
     */
    void apilar(E e);
    
    /** SII !esVacia(): 
     * obtiene y elimina de una Pila el Elemento que ocupa su tope 
     */
    E desapilar();

    /** SII !esVacia(): 
     * obtiene el Elemento que ocupa el tope de una Pila 
     */
    E tope();
    
    /** Comprueba si una Pila esta vacia
     */
    boolean esVacia();
}