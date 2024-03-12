package tema1.ejercicio1;

import librerias.estructurasDeDatos.lineales.NodoLEG;
import librerias.estructurasDeDatos.modelos.Pila;

public class LEGPila<E> implements Pila<E> {

    protected NodoLEG<E> tope;
    int talla;

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
        NodoLEG<E> aux;
        for (aux = tope; aux != null; aux = aux.siguiente) res.append(aux.dato).append("| ");
        res.append("]");
        return res.toString();
    }
}
