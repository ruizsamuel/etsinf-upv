package tema1.ejercicio5;

import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import tema1.ejercicio1.LEGCola;

/** Implementa la interfaz ListaConPI mediante una LEG ...
 *  (a) Con Nodo ficticio cabecera.
 *  (b) Una referencia al primer Nodo.
 *  (c) Una referencia al último Nodo.
 *  (d) Para representar el Punto de Interés, una referencia al Nodo
 *       anterior al que ocupa el punto de interés.
 *  (e) Un int talla que representa la talla de la LEG.
 *
 * @version Febrero 2019
 * @param <E> tipo de datos de la estructura
 */

public class LEGListaConPIEjercicio5<E> extends LEGListaConPI<E> implements ListaConPI<E> {
    public static <E extends Comparable<E>> Cola<E> encolarRepetidos(ListaConPI<E> a, ListaConPI<E> b) {
        Cola<E> cola = new LEGCola<>();
        a.inicio();
        b.inicio();
        while (!a.esFin() && !b.esFin()) {
            int comp = a.recuperar().compareTo(b.recuperar());
            if (comp == 0) {
                cola.encolar(a.recuperar());
                a.eliminar();
                b.eliminar();
            } else if (comp > 0) b.siguiente();
            else a.siguiente();
        }
        return cola;
    }
}
