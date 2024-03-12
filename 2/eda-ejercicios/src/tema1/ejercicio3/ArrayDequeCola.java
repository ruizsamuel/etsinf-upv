package tema1.ejercicio3;

import librerias.estructurasDeDatos.modelos.Cola;

import java.util.ArrayDeque;

public class ArrayDequeCola<E> extends ArrayDeque<E> implements Cola<E> {

    public ArrayDequeCola() { super(); }

    @Override
    public void encolar(E e) {
        this.add(e);
    }

    @Override
    public E desencolar() {
        return this.poll();
    }

    @Override
    public E primero() {
        return this.peekFirst();
    }

    @Override
    public boolean esVacia() {
        return this.size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (E e : this) res.append(e.toString()).append(" ");
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }
}
