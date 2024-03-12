package examen_23_24;

import librerias.estructurasDeDatos.grafos.Adyacente;
import librerias.estructurasDeDatos.grafos.GrafoNoDirigido;
import librerias.estructurasDeDatos.jerarquicos.ABB;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;
import librerias.estructurasDeDatos.jerarquicos.NodoABB;
import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.modelos.ListaConPI;

import java.util.Arrays;

public class SegundoParcial {

    /**
     * Método main para probar las soluciones
     */
    public static void main(String[] args) {
        // EJERCICIO 1
        System.out.println("* Ejercicio 1 *");
        HeapEjercicio1<Integer> a = new HeapEjercicio1<>(new Integer[]{null, 6, 13, 21, 19, 15, 65, 31});
        HeapEjercicio1<Integer> b = new HeapEjercicio1<>(new Integer[]{null, 6, 21, 13, 31, 65, 15, 19});
        System.out.println(a.esReflejo(b));
        b = new HeapEjercicio1<>(new Integer[]{null, 6, 21, 13, 31, 65, 19, 15});
        System.out.println(a.esReflejo(b));
        b = new HeapEjercicio1<>(new Integer[]{null, 6, 13, 21, 31, 65, 19, 15});
        System.out.println(a.esReflejo(b));

        // EJERCICIO 2
        System.out.println("* Ejercicio 2 *");
        ABBEjercicio2<Integer> arbol1 = new ABBEjercicio2<>();
        Arrays.asList(7, 2, 9, 1, 4, 8, 10, 6).forEach(arbol1::insertar);
        ABBEjercicio2<Integer> arbol2 = new ABBEjercicio2<>();
        Arrays.asList(7, 2, 9, 1, 4, 8, 10).forEach(arbol2::insertar);
        System.out.println(arbol1.encontrarMediana());
        System.out.println(arbol2.encontrarMediana());

        // EJERCICIO 3
        System.out.println("* Ejercicio 3 *");
        GrafoEjercicio3 grafo = new GrafoEjercicio3(5);
        grafo.insertarArista(0, 1); grafo.insertarArista(1, 2);
        grafo.insertarArista(1, 3); grafo.insertarArista(0, 2);
        grafo.insertarArista(2, 3);
        System.out.println(grafo.nivelesDesdeCero());
        grafo.insertarArista(3,4);
        System.out.println(grafo.nivelesDesdeCero());
    }


    // EJERCICIO 1

    /**
     * Implementa un método de instancia eficiente en la clase MonticuloBinario que reciba como
     * argumento otro MonticuloBinario, y devuelva true si ese otro montículo es reflejo del montículo sobre el cual
     * se invoca el método (this), y devuelva false en caso contrario.
     * Asumimos que ambos montículos cumplen las condiciones de los montículos binarios mínimos y completos en
     * todos los niveles del árbol, y que no están vacíos.

     * Un montículo binario es reflejo de otro si:
     *      • Tiene la misma talla,
     *      • Los elementos en la raíz son el mismo y, para cada subárbol,
     *      • El elemento en el nodo padre es el mismo, pero los nodos hijos están intercambiados. Esto es, el elemento
     *      en el nodo izquierdo del primer montículo es el mismo que el elemento en el nodo derecho del segundo
     *      montículo, y viceversa.

     * Además, tras la ejecución del método, los dos montículos deben permanecer inalterados
     */
    static class HeapEjercicio1<E extends Comparable<E>> extends MonticuloBinario<E> {

        public HeapEjercicio1(E[] array) {
            super();
            elArray = array;
            talla = array.length - 1;
        }
        public boolean esReflejo(HeapEjercicio1<E> otro) {
            return otro.talla == talla && esReflejo(1, 1, otro);
        }

        protected boolean esReflejo(int i, int j, HeapEjercicio1<E> otro) {
            if (this.elArray[i].compareTo(otro.elArray[j]) == 0) {
                if (i < talla/2) {
                    return esReflejo(2 * i, 2 * j + 1, otro) &&
                            esReflejo(2 * i + 1, 2 * j, otro);
                }
                return true;
            }
            return false;
        }
    }

    // EJERCICIO 2

    /**
     * Implementa un método de instancia en la clase ABB que devuelva el dato del nodo
     * que representa la mediana de todos los datos en el árbol. Si el número de elementos es par, devuelve el elemento
     * más pequeño de los dos que estarían en la mitad.
     */
    static class ABBEjercicio2<E extends Comparable<E>> extends ABB<E> {
        public E encontrarMediana() {
            if (raiz == null) return null;
            int totalElementos = this.talla();
            int medianaPos = totalElementos / 2;
            if (totalElementos % 2 != 0) medianaPos++;
            return encontrarK(raiz, medianaPos);
        }
        protected E encontrarK(NodoABB<E> actual, int k) {
            if (actual == null) return null;
            int tallaI = (actual.izq == null) ? 0 : actual.izq.talla;

            if (k == tallaI + 1) return actual.dato;
            if (k < tallaI + 1) return encontrarK(actual.izq, k);
            else return encontrarK(actual.der, k - tallaI - 1);
        }
    }

    // EJERCICIO 3

    /**
     * Implementa en la clase Grafo, con el menor coste posible, un método de instancia que calcule
     * cuántos niveles hay en el grafo, comenzando desde el vértice 0. Asumimos que el Grafo (this) es conexo, no dirigido
     * y que no está vacío.
     * El número de niveles en un grafo se puede determinar encontrando la distancia más larga desde el vértice inicial
     * (en este caso, el vértice 0) hasta cualquier otro vértice en el grafo
     */
    static class GrafoEjercicio3 extends GrafoNoDirigido {

        public GrafoEjercicio3(int numVertices) {
            super(numVertices);
        }

        public int nivelesDesdeCero() {
            int niveles = 0; // Si el primer vértice se considera un nivel, inicializar a 1
            visitados = new int[numVertices()];
            q = new ArrayCola<>();
            visitados[0] = 1;
            q.encolar(0);
            while(!q.esVacia()) {
                int w = q.desencolar();
                ListaConPI<Adyacente> l = adyacentesDe(w);
                int noVisitados = 0;
                for (l.inicio(); !l.esFin(); l.siguiente()) {
                    int u = l.recuperar().getDestino();
                    if (visitados[u] == 0) {
                        noVisitados++;
                        visitados[u] = 1;
                        q.encolar(u);
                    }
                }
                if (noVisitados > 0) niveles++;
            }
            return niveles;
        }
    }
}
