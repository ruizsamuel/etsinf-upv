package tema1.ejercicio4;
import librerias.estructurasDeDatos.modelos.ListaConPI;
public interface ListaConPIPlus<E> extends ListaConPI<E> {
    boolean contiene(E e);
    boolean eliminarPrimero(E e);
    boolean eliminarUltimo(E e);
    boolean eliminarTodos(E e);
    void concatenar(ListaConPI<E> l);
    void vaciar();
    void buscar(E e);
    void invertir();
    String toString();
}
