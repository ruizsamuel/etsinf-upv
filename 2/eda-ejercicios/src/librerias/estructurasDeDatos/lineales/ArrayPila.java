package librerias.estructurasDeDatos.lineales;

import  librerias.estructurasDeDatos.modelos.*;

public class ArrayPila<E> implements Pila<E> {
    protected E[] elArray;
    protected int tope;
    protected static final int CAPACIDAD_POR_DEFECTO = 50;

    public ArrayPila() {
        elArray = (E[]) new Object[CAPACIDAD_POR_DEFECTO];
        tope = -1;
    }

    public void apilar(E e) {
        tope++;
        if (tope > elArray.length) duplicarArray();
        elArray[tope] = e;
    }

    protected void duplicarArray() {
        E[] nuevoArray = (E[]) new Object[elArray.length * 2];
        System.arraycopy(elArray, 0, nuevoArray, 0, tope);
        elArray = nuevoArray;
    }

    public E desapilar() {
        if (!esVacia()) tope--;
        else return null;
        return elArray[tope + 1];
    }

    public E tope() {
        if (!esVacia()) return elArray[tope];
        return null;
    }

    public boolean esVacia() {
        return (tope < 0);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = tope; i >= 0; i--) res.append(elArray[i].toString()).append(", ");
        res.append("]");
        return res.toString();
    }
}