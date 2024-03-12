package tema4.ejercicio2;

import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;
/**
 * Class MonticuloBinarioEj2
 * Ejercicio 2
 *
 * @author ruizsamuel
 */

public class MonticuloBinarioEj2<E extends Comparable<E>> extends MonticuloBinario<E> implements ColaPrioridad<E> {

    /** Ejercicio 2.1
     *  En tiempo constante, comprueba si hay elementos menores
     *  que e en el montículo.
     */
    public boolean hayMenoresQue(E e) {
        return elArray[1].compareTo(e) < 0;
    }

    /** Ejercicio 2.2
     *  En tiempo mínimo, comprueba si hay elementos mayores
     *  que e en el montículo.
     */
    public boolean hayMayoresQue(E e) {
        for (int i = (talla / 2 + 1); i <= talla; i++) if (elArray[i].compareTo(e) > 0) return true;
        return false;
    }

    // EJERCICIO 2.3

    /** Ejercicio 2.3
     *  Método lanzadera
     *  Comprueba si el elemento (e) está en el montículo.
     */
    public boolean estaEn(E e) {
        return estaEn(e, 1);
    }

    /** Ejercicio 2.3
     *  Método recursivo
     *  Comprueba si el elemento (e) está en el montículo.
     */
    protected boolean estaEn(E e, int pos) {
        if (pos <= talla) {
            if (e.compareTo(elArray[pos]) == 0) return true;
            if (e.compareTo(elArray[pos]) > 0)
                return estaEn(e, pos * 2) || estaEn(e, pos * 2 + 1);
        }
        return false;
    }

    /** Ejercicio 2.4
     *  Elimina las hojas del montículo que están dentro del rango de
     *  datos genéricos no vacíos [x, y].
     */
    public void borrarHojasEnRango(E x, E y) {
        for (int i = talla / 2 + 1; i <= talla; i++)
            if (elArray[i].compareTo(x) >= 0 &&
                elArray[i].compareTo(y) <= 0) elArray[i] = elArray[talla--];

        if (elArray[talla].compareTo(x) >= 0 && elArray[talla].compareTo(y) <= 0) talla--;

        arreglar();
    }

    /** Ejercicio 2.5
     *  Devuelve y elimina el elemento k-ésimo (por niveles del montón)
     */
    public E eliminar(int k) {
        E res = elArray[k];
        elArray[k] = elArray[talla--];
        arreglar();
        return res;
    }

    // EJERCICIO 2.6

    /** Ejercicio 2.6
     *  Método lanzadera
     *  Devuelve el número de elementos iguales al mínimo (incluido)
     */
    public int igualesAlMinimo() {
        return igualesAlMinimo(1);
    }

    /** Ejercicio 2.6
     *  Método recursivo
     *  Devuelve el número de elementos iguales al mínimo (incluido)
     */
    protected int igualesAlMinimo(int pos) {
        if (pos > talla || elArray[pos].compareTo(elArray[1]) > 0) return 0;
        return 1 + igualesAlMinimo(pos * 2) + igualesAlMinimo(pos * 2 + 1);
    }

    /** Ejercicio 2.7
     *  Método estático que comprueba si un array es un montículo con el menor coste posible
     */
    public static <E extends Comparable<E>> boolean esHeap(E[] v) {
        for (int i = v.length - 1; i > 0; i--) {
            if (v[i].compareTo(v[i /2]) < 0) return false;
        }
        return true;
    }

    // EJERCICIO 2.8

    /** Ejercicio 2.8
     *  Método lanzadera
     *  Devuelve el número de elementos menores que e
     */
    public int menoresQue(E e) {
        return menoresQue(e, 1);
    }

    /** Ejercicio 2.8
     *  Método recursivo
     *  Devuelve el número de elementos menores que e
     */
    protected int menoresQue(E e, int pos) {
        return (talla >= pos && elArray[pos].compareTo(e) < 0)
                ? 1 + menoresQue(e, pos * 2) + menoresQue(e, pos * 2 + 1)
                : 0;
    }

    /** Ejercicio 2.9
     *  Elimina y devuelve la primera hoja
     */
    public E eliminar1aHoja() {
        int i = talla / 2 + 1;
        return eliminar(i);
    }
}