package tema5.ejercicio2;

import librerias.estructurasDeDatos.jerarquicos.ABB;
import librerias.estructurasDeDatos.jerarquicos.NodoABB;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.excepciones.ElementoNoEncontrado;

public class ABBEj2<E extends Comparable<E>> extends ABB<E> {

    // EJERCICIO 2.1

    /**
     * Ejercicio 2.1 (b)
     * Método lanzadera
     * Devuelve el nivel en el que la primera aparición de pre-orden
     * de un elemento dado está en un ABB equilibrado, o -1 si no lo está.
     */
    public int enQueNivel(E e) {
        return (raiz == null) ? -1 : enQueNivel(e, raiz, 0);
    }

    /**
     * Ejercicio 2.1 (b)
     * Método recursivo
     */
    protected int enQueNivel(E e, NodoABB<E> actual, int nivel) {
        if (actual == null) return -1;
        int comp = e.compareTo(actual.dato);
        if (comp < 0) return enQueNivel(e, actual.izq, nivel + 1);
        if (comp > 0) return enQueNivel(e, actual.der, nivel + 1);
        return nivel;
    }

    // EJERCICIO 2.2

    /**
     * Ejercicio 2.2 (b)
     * Método lanzadera
     * Devuelve el número de elementos de un nodo de un ABB equilibrado (elementos no duplicados)
     * que son mayores que un dato dado
     */
    public int contarMayoresQue(E e) {
        return contarMayoresQue(e, raiz);
    }

    /**
     * Ejercicio 2.2 (b)
     * Método recursivo
     */
    protected int contarMayoresQue(E e, NodoABB<E> actual) {
        if (actual == null) return 0;
        int c = e.compareTo(actual.dato);
        return (c < 0)
                ? actual.der.talla + 1 + contarMayoresQue(e, actual.izq)
                : (c == 0) ? actual.der.talla
                : contarMayoresQue(e, actual.der);
    }

    // EJERCICIO 2.3

    /**
     * Ejercicio 2.3 (a)
     * Devuelve el dato en el nodo padre del nodo que contiene
     * e, o devuelve null si no está en el ABB o no tiene padre.
     */
    public E padreDe(E e) {
        NodoABB<E> actual, padre;
        actual = raiz;
        padre = null;
        while (actual.dato.compareTo(e) != 0) {
            padre = actual;
            actual = (actual.dato.compareTo(e) > 0) ? actual.izq : actual.der;
            if (actual == null) return null;
        }
        return (padre != null) ? padre.dato : null;
    }

    /**
     * Ejercicio 2.3 (b)
     * Devuelve el dato en el nodo hermano del nodo que
     * contiene e, o devuelve null si no está en el ABB o no tiene hermano.
     */
    public E hermanoDe(E e) {
        NodoABB<E> actual, hermano;
        actual = raiz;
        hermano = null;
        while (actual.dato.compareTo(e) != 0) {
            if (actual.dato.compareTo(e) > 0) {
                hermano = actual.der;
                actual = actual.izq;
            } else {
                hermano = actual.izq;
                actual = actual.der;
            }
            if (actual == null) return null;
        }
        return (hermano != null) ? hermano.dato : null;
    }

    // EJERCICIO 2.4

    /**
     * Ejercicio 2.4
     * Método lanzadera
     * Devuelve una ListaConPI con los datos del ABB en orden (ascendente).
     */
    public ListaConPI<E> toListaConPI() {
        ListaConPI<E> l = new LEGListaConPI<>();
        if (raiz != null) toListaConPI(l, raiz);
        return l;
    }

    /**
     * Ejercicio 2.4
     * Método recursivo
     */
    protected void toListaConPI(ListaConPI<E> l, NodoABB<E> actual) {
        if (actual.izq != null) toListaConPI(l, actual.izq);
        l.insertar(actual.dato);
        if (actual.der != null) toListaConPI(l, actual.der);
    }

    // EJERCICIO 2.6

    /**
     * Ejercicio 2.6
     * Método lanzadera
     * Devuelve la altura de this si es balanceado, o produzca la excepción ElementoNoEncontrado
     * tan pronto como encuentre un nodo que no cumpla la condición de ser balanceado.
     */
    public int alturaDeEquilibrado() throws ElementoNoEncontrado {
        return alturaDeEquilibrado(raiz);
        // En caso de considerar que un árbol con un solo elemento tenga altura 0, restar 1 al resultado
    }

    protected int alturaDeEquilibrado(NodoABB<E> arbol) throws ElementoNoEncontrado {
        int alturaI, alturaD;

        if (arbol == null) return 0;
        alturaI = alturaDeEquilibrado(arbol.izq);
        alturaD = alturaDeEquilibrado(arbol.der);

        if (Math.abs(alturaD - alturaI) > 1) throw new ElementoNoEncontrado();

        return Math.max(alturaD, alturaI) + 1;
    }
}