package tema5.ejercicio1;

import librerias.estructurasDeDatos.jerarquicos.ABBMapOrdenado;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.jerarquicos.EntradaMap;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.MapOrdenado;

public class Metodos {

    /**
     * Ejercicio 1.1
     * Diseñar un método estático, genérico, iterativo, entradas, que devuelva un ListaConPI
     * con las entradas de un MapOrdenado no vacío, en orden ascendente.
     */
    public static <C extends Comparable<C>, V> ListaConPI<EntradaMap<C, V>> entradas(MapOrdenado<C, V> m) {
        ListaConPI<EntradaMap<C,V>> res = new LEGListaConPI<>();
        for (EntradaMap<C, V> e = m.recuperarEntradaMin(); e != null; e = m.sucesorEntrada(e.getClave()))
            res.insertar(e);
        return res;
    }

    /**
     * Ejercicio 1.2
     * Diseña un método estático, genérico e iterativo, mapSort, que, con la ayuda de un
     * MapOrdenado, ordene los elementos (Comparable) de un array no vacío, sin
     * duplicados.
     */
    public static <E extends Comparable<E>> void mapSort (E[] v) {
        MapOrdenado<E,E> aux = new ABBMapOrdenado<>();
        for (E i : v) aux.insertar(i, null);
        int i = 0;
        for (EntradaMap<E, E> e = aux.recuperarEntradaMin(); e != null; e = aux.sucesorEntrada(e.getClave()))
            v[i++] = e.getClave();
    }

    /**
     * Ejercicio 1.3
     * Diseña un método estático e iterativo, hayDosQueSuman, que, dada un array no vacío
     * de enteros y un entero k, determine si existen en este dos números cuya suma es k. Utiliza
     * un MapOrdenado como EDA auxiliar.
     */
    public static boolean hayDosQueSuman(int[] v, int k) {
        MapOrdenado<Integer, Integer> aux = new ABBMapOrdenado<>();
        for (Integer i : v) aux.insertar(i, null);
        Integer min = aux.recuperarMin();
        Integer max = aux.recuperarMax();
        while (!min.equals(max)) {
            int sum = min + max;
            if (sum == k) return true;
            if (sum < k) min = aux.sucesor(min);
            else max = aux.predecesor(max);
        }
        return false;
    }
}
