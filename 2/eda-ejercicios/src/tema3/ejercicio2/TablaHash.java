package tema3.ejercicio2;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * Ejercicio 2.1
 * Implementación de una TablaHash Enlazada
 * con Listas con PI y SIN REHASHING
 *
 * @param <C>, el tipo de la clave
 * @param <V>, el tipo del valor asociado a una clave
 *
 * @author Samuel Ruiz (ruizsamuel)
 */
public class TablaHash<C, V> implements Map<C, V> {

    /** El valor (float) del factor de carga de una Tabla Hash
     *  (valor por defecto en la clase java.util.HashMap)
     */
    public static final double FACTOR_DE_CARGA = 0.75;

    /** Array de Listas Con PI de Tipo Genérico EntradaHashLPI<C, V>:
     *  - elArray[h] representa una cubeta,
     *    o lista de colisiones asociadas al índice Hash h
     *  - elArray[h] contiene la referencia a la Lista Con PI
     *    donde se encuentran todas las
     *    Entradas cuya Clave tiene un índice Hash h
     */
    protected ListaConPI<EntradaHash<C,V>>[] elArray;

    /** TIENE UNA talla que representa el número de Entradas
     *  almacenadas en una Tabla Hash o, si se prefiere, en sus cubetas
     */
    protected int talla;

    /** Devuelve el índice Hash de la Clave c de una Entrada, i.e.
     *  la cubeta en la que se debe encontrar la Entrada de clave c
     *  *** SIN ESTE METODO NO SE TIENE UNA TABLA HASH, SOLO UN ARRAY ***
     */
    protected int indiceHash(C c) {
        int indiceHash = c.hashCode() % elArray.length;
        if (indiceHash < 0) indiceHash += elArray.length;
        return indiceHash;
    }

    /** Crea una Tabla Hash vacia, con una capacidad (inicial) maxima
     *  de tallaMaximaEstimada entradas y factor de carga 0.75
     */
    public TablaHash(int tallaMaximaEstimada) {
        int capacidad = siguientePrimo((int) (tallaMaximaEstimada / FACTOR_DE_CARGA));
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++)
            elArray[i] = new LEGListaConPI<>();
        talla = 0;
    }

    // Devuelve un número primo MAYOR o IGUAL a n, i.e. el primo que sigue a n
    protected static int siguientePrimo(int n) {
        if (n % 2 == 0) n++;
        while (!esPrimo(n)) n += 2;
        return n;
    }

    // Comprueba si n es un numero primo
    protected static boolean esPrimo(int n) {
        for (int i = 3 ; i * i <= n; i += 2)
            if (n % i == 0) return false; // n NO es primo
        return true; // n SI es primo
    }

    /** Devuelve el valor de la entrada dada una clave o null si no existe
     *
     * @param c, clave del elemento a recuperar
     *
     * @return valor del elemento con clave c
     */
    public V recuperar(C c) {
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[indiceHash(c)];
        V valor = null;
        for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente()) {
            if (cubeta.recuperar().clave.equals(c)) {
                valor = cubeta.recuperar().valor;
                break;
            }
        }
        return valor;
    }

    /** Elimina una entrada dada su clave de una Tabla Hash
     *
     * @param c, clave del elemento a eliminar
     *
     * @return valor del elemento eliminado o null si no existe
     */
    public V eliminar(C c) {
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[indiceHash(c)];
        V valor = null;
        for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente()) {
            if (cubeta.recuperar().clave.equals(c)) {
                valor = cubeta.recuperar().valor;
                break;
            }
        }
        if (!cubeta.esFin()) { cubeta.eliminar(); this.talla--; }
        return valor;
    }

    /** Inserta la entrada (c, v) a una Tabla Hash
     *
     * @param c, clave de la nueva entrada
     * @param v, valor de la nueva entrada
     *
     * @return el antiguo valor asociado a c, o null si no hay ninguna entrada asociada a esa clave
     */
    public V insertar(C c, V v) {
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[indiceHash(c)];
        V antiguoValor = null;
        for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente()) {
            V aux = (V) cubeta.recuperar().clave;
            if (aux.equals(c)) {
                antiguoValor = aux;
                cubeta.recuperar().valor = v;
                break;
            }
        }
        if (cubeta.esFin()) cubeta.insertar(new EntradaHash<>(c, v));
        return antiguoValor;
    }

    /** Comprueba si una Tabla Hash esta vacia, i.e. si tiene 0 entradas.
     */
    public boolean esVacio() {
        return talla == 0;
    }

    /** Devuelve la talla, o número de entradas, de una Tabla Hash.
     */
    public int talla() {
        return talla;
    }

    /** Devuelve una ListaConPI con las claves de una Tabla Hash
     */
    public ListaConPI<C> claves() {
        ListaConPI<C> l = new LEGListaConPI<>();
        for (ListaConPI<EntradaHash<C, V>> e : elArray)
            for (e.inicio(); !e.esFin(); e.siguiente()) l.insertar(e.recuperar().clave);
        return l;
    }

    /** Devuelve el factor de carga actual de una Tabla Hash, i.e. la longitud
     *  media de sus cubetas
     */
    public final double factorDeCarga() {
        return (double) talla / (double) elArray.length;
    }

    /**
     * SOLO PARA EJEMPLOS DE TEORÍA
     * Devuelve un String con las Entradas de una Tabla Hash
     */
    public final String toString() {
        StringBuilder res = new StringBuilder("Tabla Hash (FC: " + factorDeCarga() + "): [");
        ListaConPI<C> k = claves();
        for (k.inicio(); !k.esFin(); k.siguiente()) {
            res .append("- ")
                .append(k.recuperar())
                .append(": ")
                .append(recuperar(k.recuperar()).toString())
                .append("\n");
        }
        return res.append("]").toString();
    }

    /**
     * Ejercicio 2.1
     * Inserta la entrada (c, v) a una Tabla Hash
     * Dada una Entrada con la clave c, devuelva el número de colisiones provocadas por su posición
     * @param c, clave entrada a comprobar
     *
     * @return el número de colisiones asociadas a esa entrada
     */
    public int numeroColisiones(C c) {
        return elArray[indiceHash(c)].talla();
    }

    /**
     * Ejercicio 2.3
     * Dado un valor determinado, devuelve una ListaConPI con todas las claves que tienen asociado ese valor.
     *
     * @param v, valor a comprobar
     *
     * @return ListaConPI de claves asociadas a ese valor
     */
    public ListaConPI<C> clavesConValor(V v) {
        ListaConPI<C> res = new LEGListaConPI<>();
        for (ListaConPI<EntradaHash<C,V>> cubeta : elArray) {
            for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente()) {
                EntradaHash<C, V> entrada = cubeta.recuperar();
                if (entrada.valor.equals(v)) res.insertar(entrada.clave);
            }
        }
        return res;
    }

    /**
     * Representa un elemento de la Lista con PI que implementa
     * una cubeta de una TablaHash, esto es una Entrada o par
     * (clave, valor)
     *
     * @param <C>, el tipo de la clave
     * @param <V>, el tipo del valor asociado a una clave
     *
     * @author (EDA)
     * @version (Curso 2018/19)
     */
    protected static class EntradaHash<C, V> {

        protected C clave;
        protected V valor;

        EntradaHash(C c, V v) {
            clave = c;
            valor = v;
        }

        public String toString() {
            return "(" + clave + ", " + valor + ")";
        }
    }
}
