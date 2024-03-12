package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

public class LEGPila<E> implements Pila<E> {

    protected NodoLEG<E> tope;
    protected int talla;

    public LEGPila() {
        tope = null;
        talla = 0;
    }

    @Override
    public void apilar(E o) {
        tope = new NodoLEG<>(o, tope);
        talla++;
    }

    @Override
    public E desapilar() {
        E dato = tope.dato;
        tope = tope.siguiente;
        talla--;
        return dato;
    }

    @Override
    public E tope() {
        if (talla > 0) return tope.dato;
        return null;
    }

    @Override
    public boolean esVacia() { return tope == null; }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (NodoLEG<E> aux = tope; aux.dato != null; aux = aux.siguiente) res.append(aux.dato).append("| ");
        res.append("]");
        return res.toString();
    }
}