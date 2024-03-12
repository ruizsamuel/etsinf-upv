package tema2.ejercicio1;

public class Metodos {
    // EJERCICIO 1.1

    /**
     * Ejercicio 1.1
     * Método lanzadera
     *
     * @param v Array de enteros int[] que se ajustan al perfil de una curva continua
     * monotónicamente creciente, tal que v[0]<0 y v[v.length-1]>0.
     *
     * @return una única posición k de v, 0≤k<v.length-1, tal que entre v[k] y v[k+1] la función es
     * 0, es decir, tal que v[k]≤0 y v[k+1]>0.
     */
    public static int metodo1(int[] v) {
        return metodo1(v, 0, v.length - 1);
    }

    /**
     * Ejercicio 1.1
     * Método recursivo (Divide y Vencerás)
     *
     * @param v Array de enteros int[] que se ajustan al perfil de una curva continua
     * monotónicamente creciente, tal que v[0]<0 y v[v.length-1]>0.
     * @param p posición inicial del array dividido
     * @param f posición final del array dividido
     *
     * @return una única posición k de v, 0≤k<v.length-1, tal que entre v[k] y v[k+1] la función es
     * 0, es decir, tal que v[k]≤0 y v[k+1]>0.
     */
    public static int metodo1(int[] v, int p, int f) {
        if (p == f) return p;
        int m = (p + f) / 2;
        return (v[m + 1] > 0) ? metodo1(v, p, m) : metodo1(v, m + 1, f);
    }

    // EJERCICIO 1.2

    /**
     * Ejercicio 1.2
     * Método lanzadera
     *
     * @param v array de enteros positivos que se ajustan al perfil de una curva
     * cóncava, es decir, existe una única posición k de v, 0≤k<v.length, tal que:
     * 0≤j<k | v[j]>v[j+1] & k<j<v.length | v[j-1]<v[j].
     *
     * @return k
     */
    public static int metodo2(int[] v) {
        return metodo2(v, 0, v.length - 1);
    }

    /**
     * Ejercicio 1.2
     * Método recursivo (Divide y Vencerás)
     *
     * @param v array de enteros positivos que se ajustan al perfil de una curva
     * cóncava, es decir, existe una única posición k de v, 0<k<v.length, tal que:
     * 0≤j<k | v[j]>v[j+1] & k<j<v.length | v[j-1]<v[j].
     * @param p posición inicial del array dividido
     * @param f posición final del array dividido
     *
     * @return k
     */
    public static int metodo2(int[] v, int p, int f) {
        if (p == f) return p;
        int m = (p + f) / 2;
        if      (v[m - 1] > v[m] && v[m] > v[m + 1]) return metodo2(v, m + 1, f);
        else if (v[m - 1] < v[m] && v[m] < v[m + 1]) return metodo2(v, p, m);
        return m;
    }

    // EJERCICIO 1.3

    /**
     * Ejercicio 1.3
     * Método lanzadera
     *
     * @param v un array de enteros, ordenado de forma ascendente y sin elementos repetidos
     *
     * @return la posición j del componente de v tal que v[j] = j siempre que exista, si no existe devuelve -1
     */
    public static int metodo3(int[] v) {
        return metodo3(v, 0, v.length - 1);
    }

    /**
     * Ejercicio 1.3
     * Método recursivo (Divide y Vencerás)
     *
     * @param v un array de enteros, ordenado de forma ascendente y sin elementos repetidos
     * @param p posición inicial del array dividido
     * @param f posición final del array dividido
     *
     * @return la posición j del componente de v tal que v[j] = j siempre que exista, si no existe devuelve -1
     */
    public static int metodo3(int[] v, int p, int f) {
        if (p == f) return (v[p] == p) ? p : -1;
        int m = (p + f) / 2;
        if (m == v[m]) return m;
        return (v[m] < m) ? metodo3(v, m + 1, f) : metodo3(v, 0, m);
    }

    // EJERCICIO 1.4

    /**
     * Ejercicio 1.4
     * Método lanzadera
     *
     * @param v un array de String, ordenado de forma ascendente, sin elementos repetidos y no vacío
     * @param x String estríctamente menor que y
     * @param y String estríctamente mayor que x
     *
     * @return true si X e Y ocupan posiciones consecutivas en v y false en caso contrario
     */
    public static boolean metodo4(String[] v, String x, String y) {
        return metodo4(v, x, y, 0, v.length - 1);
    }

    /**
     * Ejercicio 1.4
     * Método recursivo (Divide y Vencerás)
     *
     * @param v un array de String, ordenado de forma ascendente y sin elementos repetidos y no vacío
     * @param x String estríctamente menor que y
     * @param y String estríctamente mayor que x
     * @param p posición inicial del array dividido
     * @param f posición final del array dividido
     *
     * @return true si X e Y ocupan posiciones consecutivas en v y false en caso contrario
     */
    public static boolean metodo4(String[] v, String x, String y, int p, int f) {
        if (v[f].compareTo(x) < 0) return false;
        int m = (p + f) / 2;
        if (v[m].equals(x)) return (v[(m+1) % v.length].equals(y));
        return (v[m].compareTo(x) < 0) ? metodo4(v, x, y, m + 1, f) : metodo4(v, x, y, p, m);
    }
}