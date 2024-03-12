package tema2.ejercicio2;

public class Metodos {
    // EJERCICIO 2.2

    /**
     * Ejercicio 2.2
     * Método recursivo (Divide y Vencerás)
     * Ordena un array dividido en dos partes ya ordenadas
     *
     * @param <T> tipo de los elementos a ordenar, deben ser comparables
     * @param v array de T a ordenar
     * @param i inicio del primer sub-array de v, ya ordenado
     * @param m fin del primer sub-array, tal que m + 1 es la posición inicial del segundo
     * @param j fin del segundo sub-array
     */
    public static <T extends Comparable<T>> void mergeDC(T[] v, int i, int m, int j) {
        T[] aux = (T[]) new Comparable[j - i + 1];
        int a = i, b = m + 1, c = 0;
        while(a <= m && b <= j) aux[c++] = (v[a].compareTo(v[b]) < 0 ) ? v[a++] : v[b++];

        while (a < m) aux[c++] = v[a++];
        while (b <= j) aux[c++] = v[b++];

        System.arraycopy(aux, 0, v, i, aux.length);
    }

    // EJERCICIO 2.4

    /**
     * Ejercicio 2.4
     * Método lanzadera
     * Encuentra la sub secuencia máxima de un array, es decir, la sub secuencia de
     * números (consecutivos) cuya suma es máxima.
     *
     * @param v array
     * @return el valor de la suma de la sub secuencia máxima
     */
    public static int subSumaMax(int[] v) {
        return subSumaMax(v, 0, v.length - 1);
    }

    /**
     * Ejercicio 2.4
     * Método recursivo (Divide y Vencerás)
     * Encuentra la sub secuencia máxima de un array, es decir, la sub secuencia de
     * números (consecutivos) cuya suma es máxima.
     *
     * @param v array
     * @param p posición inicial del array dividido
     * @param f posición final del array dividido
     * @return el valor de la suma de la sub secuencia máxima
     */
    public static int subSumaMax(int[] v, int p, int f) {
        if (p == f) return Math.max(v[p], 0);

        int m = (p + f) / 2;
        int sumaMaxIzq = subSumaMax(v, 0, m);
        int sumaMaxDer = subSumaMax(v, m + 1, f);

        int sumaBordeIzq = 0; int sumaMaxBordeIzq = 0;
        for (int i = m; i >= p; i--) {
            sumaBordeIzq += v[i];
            sumaMaxBordeIzq = Math.max(sumaBordeIzq, sumaMaxBordeIzq);
        }

        int sumaBordeDer = 0; int sumaMaxBordeDer = 0;
        for (int i = m + 1; i <= f; i++) {
            sumaBordeDer += v[i];
            sumaMaxBordeDer = Math.max(sumaBordeDer, sumaMaxBordeDer);
        }

        return Math.max (Math.max(sumaMaxIzq, sumaMaxDer), sumaMaxBordeDer + sumaMaxBordeIzq);
    }

    // EJERCICIO 2.5

    /**
     * Ejercicio 2.5 (EX REC 2021)
     * Método lanzadera
     * Encuentra si existe, el único valor entero que se repite en un array de n enteros con rango [1 ... n]
     * Si no se encuentra ningún elemento repetido, se indicará con el valor especial -1
     *
     * @param v array en el que buscar
     * @return el valor repetido
     */
    public static int valorRepetido(int[] v) {
        return valorRepetido(v, 0, v.length - 1);
    }

    /**
     * Ejercicio 2.5
     * Método recursivo (Divide y Vencerás)
     * Encuentra si existe, el único valor entero que se repite en un array de n enteros con rango [1 ... n]
     * Si no se encuentra ningún elemento repetido, se indicará con el valor especial -1
     *
     * @param v array en el que buscar
     * @param p posición inicial del array dividido
     * @param f posición final del array dividido
     * @return el valor repetido
     */
    public static int valorRepetido(int[] v, int p, int f) {
        if (f - p == 1) return (v[p] == v[f]) ? v[p]: -1;
        if (p == f) return -1;
        int m = (p + f) / 2;
        if(v[m] - 1 < m) return valorRepetido(v,p,m);
        else return valorRepetido(v,m + 1, f);
    }

    // EJERCICIO 2.6

    /**
     * Ejercicio 2.6 (EX 2021)
     * Método lanzadera
     * Dado un array de enteros no vacío, ordenado de forma ascendente y sin elementos repetidos,
     * devuelve el primer valor omitido que haría consecutivos sus elementos. Si no hay ningún
     * valor omitido, se devuelve el siguiente valor de la secuencia.
     *
     * @param v array
     * @return el primer valor omitido
     */
    public static int primerOmitido(int[] v) {
        return primerOmitido(v, 0, v.length - 1);
    }

    /**
     * Ejercicio 2.6 (EX 2021)
     * Método recursivo (Divide y Vencerás)
     * Dado un array de enteros no vacío, ordenado de forma ascendente y sin elementos repetidos,
     * devuelve el primer valor omitido que haría consecutivos sus elementos. Si no hay ningún
     * valor omitido, se devuelve el siguiente valor de la secuencia.
     *
     * @param v array
     * @param p posición inicial del array dividido
     * @param f posición final del array dividido
     * @return el primer valor omitido
     */
    public static int primerOmitido(int[] v, int p, int f) {
        if (p == f) return v[p] + 1;
        int m = (p + f) / 2;
        return (v[m + 1] > m + 1 + v[0]) ? primerOmitido(v, p, m) : primerOmitido(v, m + 1, f);
    }

    // EJERCICIO 2.7

    /**
     * Ejercicio 2.7 (EX 2021)
     * Método lanzadera
     * Devuelve el número de Strings de una longitud dada, n, en un array de Strings, v, ordenados de forma
     * ascendente por longitud de String
     *
     * @param v array
     * @param n tamaño String a buscar
     * @return número de strings de longitud n
     */
    public static int deLongitudX(String[] v, int n) {
        return deLongitudX(v, n, 0, v.length - 1);
    }

    /**
     * Ejercicio 2.7 (EX 2021)
     * Método recursivo (Divide y Vencerás)
     * Devuelve el número de Strings de una longitud dada, n, en un array de Strings, v, ordenados de forma
     * ascendente por longitud de String
     *
     * @param v array
     * @param p posición inicial del array dividido
     * @param f posición final del array dividido
     * @param n tamaño String a buscar
     * @return número de strings de longitud n
     */
    public static int deLongitudX(String[] v, int n, int p, int f) {
        if (p == f) return 0;
        int m = (p + f) / 2;
        int l = v[m].length();
        if (l == n) {
            return 1 + deLongitudX(v, n, p, m) + deLongitudX(v, n, m + 1, f);
        } else if (l < n) {
            return deLongitudX(v, n, m + 1, f);
        } else {
            return deLongitudX(v, n, p, m);
        }
    }
}
