package tema1.ejercicio1;

import librerias.estructurasDeDatos.lineales.NodoLEG;
import librerias.estructurasDeDatos.modelos.Cola;

public class LEGCola<E> implements Cola<E> {

    NodoLEG<E> pri, tope;
    int talla;

    public LEGCola() {
        pri = tope = null;
        talla = 0;
    }

    @Override
    public void encolar(E e) {
        if (esVacia()) pri = tope = new NodoLEG<>(e, null);
        else {
            NodoLEG<E> n = new NodoLEG<>(e, null);
            tope.siguiente = n;
            tope = n;
        }
        talla++;
    }

    @Override
    public E desencolar() {
        E res = pri.dato;
        pri = pri.siguiente;
        if (pri == null) tope = null;
        talla--;
        return res;
    }

    @Override
    public E primero() { return pri.dato; }

    @Override
    public boolean esVacia() { return talla < 1; }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (NodoLEG<E> n = pri; (n != null) ; n = n.siguiente) res.append(n.dato.toString()).append("| ");
        res.append("]");
        return res.toString();
    }
}
