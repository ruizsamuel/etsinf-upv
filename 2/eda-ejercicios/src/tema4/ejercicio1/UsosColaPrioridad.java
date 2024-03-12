package tema4.ejercicio1;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

import java.util.Arrays;

/**
 * Class UsosColaPrioridad.
 * Ejercicio 1
 * 
 * @author FTG, ruizsamuel
 */

public class UsosColaPrioridad {
    
    /** Ejercicio 1.1
     *  Diseñar un método estático e iterativo cPSort
     *  que, con la ayuda de una Cola de Prioridad, 
     *  ordene un array de elementos Comparable.
     */
    public static <E extends Comparable<E>> void cPSort(E[] v) {
        ColaPrioridad<E> res = new MonticuloBinario<>();
        for (E i : v) {
            res.insertar(i);
        }
        for (int i = 0; i < v.length; i++) v[i] = res.eliminarMin();
    }
    
    /** Ejercicio 1.2
     *  Diseñar un metodo estático, genérico e iterativo cPFusionar
     *  que devuelva una ListaConPI con los datos de 2 Colas de Prioridad dadas, 
     *  cP1 y cP2, ordenados ascendentemente. 
     *  El método no puede usar ninguna EDA auxiliar para calcular su resultado
     *  y, además, cP1 y cP2 deben quedar vacías al concluir su ejecución.
     */
    public static <E extends Comparable<E>> ListaConPI<E> cPFusionar(ColaPrioridad<E> cP1, ColaPrioridad<E> cP2) {
        ListaConPI<E> res = new LEGListaConPI<>();
        while (!cP1.esVacia() && !cP2.esVacia()) {
            if (cP1.recuperarMin().compareTo(cP2.recuperarMin()) < 0)
                    res.insertar(cP1.eliminarMin());
            else    res.insertar(cP2.eliminarMin());
        }
        while (!cP1.esVacia()) res.insertar(cP1.eliminarMin());
        while (!cP2.esVacia()) res.insertar(cP2.eliminarMin());
        return res;
    }
    
    /** Ejercicio 1.3
     *  Diseñar un metodo estático e iterativo cPEsLineal
     *  que determine si un conjunto de valores reales se ajusta (aprox.) 
     *  a una función lineal creciente usando el siguiente algoritmo:
     *  comprobar si la diferencia entre todo par de valores consecutivos, 
     *  en orden ascendente, esta acotada por un epsilon dado. 
     */
    public static boolean cPEsLineal(ColaPrioridad<Double> cP, double epsilon) {
        Double aux = cP.eliminarMin();
        while (!cP.esVacia()) if (Math.abs(aux - (aux = cP.eliminarMin())) > epsilon) return false;
        return true;
    }
    
    /** Ejercicio 1.4
     *  Diseñar un método estático, genérico e iterativo cPTopK
     *  que, dado un array de datos (v) y un entero (k),
     *  devuelva una Cola de Prioridad con los k mejores (Top K) datos de v. 
     *  El método debe tener un coste O(X log k), siendo X la longitud de v.
     */
    public static <E extends Comparable<E>> ColaPrioridad<E> cPTopK(E[] v, int k) {
        ColaPrioridad<E> res = new MonticuloBinario<>(k + 1);
        for (int i = 0; i < v.length; i++) {
            res.insertar(v[i]);
            if (i >= k) res.eliminarMin();
        }
        return res;
    }

    /**
     * Método main para pruebas
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        // EJERCICIO 1.1
        Integer[] ej1 = {2,4,1,8,5,3,-2};
        cPSort(ej1);
        System.out.println("* Ejercicio 1.1 *");
        System.out.println(Arrays.toString(ej1));

        // EJERCICIO 1.2
        ColaPrioridad<Integer> ej2a = new MonticuloBinario<>();
        for (Integer i : new Integer[]{3,7,1}) ej2a.insertar(i);

        ColaPrioridad<Integer> ej2b = new MonticuloBinario<>();
        for (Integer i : new Integer[]{2,6,4}) ej2b.insertar(i);

        System.out.println("* Ejercicio 1.2 *");
        System.out.println(cPFusionar(ej2a, ej2b));

        // EJERCICIO 1.3
        ColaPrioridad<Double> ej3 = new MonticuloBinario<>();
        for (Double i : new Double[]{0.30, 0.31,0.37}) ej3.insertar(i);

        System.out.println("* Ejercicio 1.3 *");
        System.out.println(cPEsLineal(ej3, 0.05));

        // EJERCICIO 1.4
        Integer[] ej4 = {10,1,4,2,9,3,7,8,5,14};

        System.out.println("* Ejercicio 1.4 *");
        System.out.println(cPTopK(ej4, 5));
    }
}