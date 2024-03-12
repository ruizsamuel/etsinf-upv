package tema1.ejercicio3;

import librerias.estructurasDeDatos.modelos.ColaPlus;

public class ArrayDequeColaPlus<E> extends ArrayDequeCola<E> implements ColaPlus<E> {

    public ArrayDequeColaPlus() { super(); }

    @Override
    public int talla() {
        return this.size();
    }
}
