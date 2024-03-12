package tema1.ejercicio5;

import librerias.estructurasDeDatos.modelos.Pila;

public interface PilaExt<E> extends Pila<E> {
    E minimo();
}