package tema1.ejercicio4;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Pila;
import tema1.ejercicio1.ArrayPila;
import tema1.ejercicio1.LEGPila;

public class Metodos{
    public static <E extends Comparable<E>> Pila<E> metodo1(ListaConPI<E> a, ListaConPI<E> b) {
        Pila<E> res = new ArrayPila<>();
        a.inicio();
        b.inicio();
        while (!a.esFin() && !b.esFin()) {
            int comp = a.recuperar().compareTo(b.recuperar());
            if (comp == 0) {
                res.apilar(a.recuperar());
                a.eliminar();
                b.eliminar();
            } else if (comp < 0){
                a.siguiente();
            }
            else b.siguiente();
        }
        return res;
    }

    public static <E extends Comparable<E>> boolean metodo2 (ListaConPI<E> a, ListaConPI<E> b) {
        if (a.talla() != b.talla()) return false;
        Pila<E> aux = new LEGPila<>();
        for (b.inicio(); !b.esFin(); b.siguiente()) aux.apilar(b.recuperar());
        for (a.inicio(); !a.esFin(); a.siguiente())
            if (a.recuperar().compareTo(aux.desapilar()) != 0) return false;
        return true;
    }
}
