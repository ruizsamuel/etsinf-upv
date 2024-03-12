package tema1.ejercicio2;

import librerias.estructurasDeDatos.lineales.LEGPila;
// import librerias.estructurasDeDatos.lineales.NodoLEG;

public class LEGPilaExt<E> extends LEGPila<E> implements PilaExt<E> {
    @Override
    public E base() {
        E res, aux = desapilar();
        if (esVacia()) res = aux;
        else res = base();
        apilar(aux);
        return res;

        /*
         NodoLEG<E> aux = tope;
         if (!esVacia) while (aux.siguiente != null) aux = aux.siguiente;
         return aux.dato;
        */
    }
}
