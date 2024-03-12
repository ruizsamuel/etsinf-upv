package librerias.estructurasDeDatos.modelos;

import librerias.excepciones.*;

public interface ListaConPIPlus<E> extends ListaConPI<E> {
    
    /** comprueba si el Elemento e esta en una Lista Con PI **/
    boolean contiene(E e);    
    
    /** elimina la primera aparicion del Elemento e en una Lista Con PI 
     *  y devuelve true, o devuelve false si e no esta en la Lista
     */
    boolean eliminar(E e);
    
    /** si el Elemento e esta en una Lista Con PI elimina su ultima 
     *  aparicion y la devuelve como resultado; sino lo advierte 
     *  lanzando la Excepcion ElementoNoEncontrado 
     */
    E eliminarUltimo(E e) throws ElementoNoEncontrado;
    
    /** si el Elemento e esta en una Lista Con PI elimina todas sus  
     *  apariciones en ella; sino lo advierte lanzando la Excepcion 
     *  ElementoNoEncontrado 
     */
    void eliminarTodos(E e) throws ElementoNoEncontrado;
    
    /** elimina todos los Elementos de una Lista Con PI **/
    void vaciar();
    
    /** concatena una Lista Con PI con otra **/
    void concatenar(ListaConPI<E> otra);

    /** invierte in-situ una Lista a partir de su PI **/
    void invertirDesdePI();
}