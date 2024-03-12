package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ListaConPI;

public class LEGListaConPIOrdenada<E extends Comparable> extends LEGListaConPI<E> implements ListaConPI<E> {
    @Override
    public void insertar(E e) {
        if (talla() > 0) {
            inicio();
            while (!esFin() && recuperar().compareTo(e) < 0) siguiente();
        }
        super.insertar(e);
    }
}
