package tema1.ejercicio5;

import tema1.ejercicio1.LEGPila;

public class LEGPilaExt<E extends Comparable<E>> extends LEGPila<E> implements PilaExt<E>{

    @Override
    public E minimo() {
        if (esVacia()) return tope();
        E menor = desapilar();
        E aux = minimo();
        apilar(menor);
        if (menor == null || aux.compareTo(menor) < 0)
            return aux;
        return menor;
    }
}