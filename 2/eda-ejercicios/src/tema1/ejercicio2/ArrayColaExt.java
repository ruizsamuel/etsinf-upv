package tema1.ejercicio2;

import librerias.estructurasDeDatos.lineales.ArrayCola;

public class ArrayColaExt<E> extends ArrayCola<E> implements ColaExt<E> {
    @Override
    public void invertir() {
        if (!esVacia()) {
            E aux = desencolar();
            invertir();
            encolar(aux);
        }
    }
}
