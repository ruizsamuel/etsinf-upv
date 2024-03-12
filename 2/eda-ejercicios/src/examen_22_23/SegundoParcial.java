package examen_22_23;

import librerias.estructurasDeDatos.grafos.Adyacente;
import librerias.estructurasDeDatos.grafos.GrafoNoDirigido;
import librerias.estructurasDeDatos.jerarquicos.ABB;
import librerias.estructurasDeDatos.jerarquicos.NodoABB;
import librerias.estructurasDeDatos.modelos.ListaConPI;

import java.util.Arrays;

public class SegundoParcial {
    /**
     * Método main para probar las soluciones
     */
    public static void main(String[] args) {
        // EJERCICIO 1
        System.out.println("* Ejercicio 1 *");
        Integer[] ej1 = {6,13,21,19,15,65,31};
        System.out.println(esMinHeap(ej1));

        // EJERCICIO 2
        System.out.println("* Ejercicio 2 *");
        ABBEjercicio2<Integer> ej2 = new ABBEjercicio2<>();
        Arrays.asList(20, 8, 32, 4, 12, 25, 10, 14).forEach(ej2::insertar);
        System.out.println(ej2.tallaSubarbol(12));
        System.out.println(ej2.tallaSubarbol(8));
        System.out.println(ej2.tallaSubarbol(22));

        // EJERCICIO 3
        System.out.println("* Ejercicio 3 *");
        GrafoEjercicio3 grafo = new GrafoEjercicio3(5);
        grafo.insertarArista(0,1); grafo.insertarArista(1,2);
        grafo.insertarArista(1,3); grafo.insertarArista(2,3);
        grafo.insertarArista(0,4);grafo.insertarArista(3, 4);
        System.out.println(grafo.enCiclo(0));
        System.out.println(grafo.enCiclo(1));
        System.out.println(grafo.enCiclo(2));
        System.out.println(grafo.enCiclo(3));
        System.out.println(grafo.enCiclo(4));
    }

    // EJERCICIO 1

    /**
     * Implementa un método estático que, dado un array genérico v (indexado desde 0 hasta v - length-1),
     * devuelva true si sus elementos cumplen la propiedad de orden de un MinHeap y false en
     * caso contrario.
     */
    public static <E extends Comparable<E>> boolean esMinHeap(E[] v) {
        if (v.length < 2) return false;
        for (int i = v.length; i > 1; i--) if (v[i - 1].compareTo(v[(i/2) - 1]) < 0) return false;
        return true;
    }

    // EJERCICIO 2

    /**
     * En la clase ABB vista en teoría, cada nodo contiene el campo talla, que indica el número
     * de nodos del subárbol cuya raíz es ese nodo. Se ha modificado la clase ABB y no se mantiene esa información.

     * Implementa para la nueva clase un método tallaSubarbol que recibe una clave y devuelve el número
     * de nodos del subárbol cuya raíz tiene esa clave. Si la clave no se encuentra devolverá 0.
     */
    static class ABBEjercicio2<E extends Comparable<E>> extends ABB<E>{
        public int tallaSubarbol(E key) {
            return tamanyo(encontrar(raiz, key));
        }

        protected NodoABB<E> encontrar(NodoABB<E> actual, E key) {
            if (actual == null) return null;
            if (actual.dato.compareTo(key) > 0) return encontrar(actual.izq, key);
            if (actual.dato.compareTo(key) == 0) return actual;
            else return encontrar(actual.der, key);
        }

        protected int tamanyo(NodoABB<E> nodo) {
            if (nodo == null) return 0;
            return 1 + tamanyo(nodo.izq) + tamanyo(nodo.der);
        }
    }

    // EJERCICIO 3

    /**
     * Dada la implementación de la clase Grafo vista en teoría, se pide diseñar en la clase Grafo
     * un método enCiclo que compruebe si el vértice dado forma parte de un ciclo de un grafo.
     */
    static class GrafoEjercicio3 extends GrafoNoDirigido {
        public GrafoEjercicio3(int numVertices) {
            super(numVertices);
        }

        public boolean enCiclo(int v){
            visitados = new int[numVertices()];
            return enCicloDFS(v, v);
        }
        protected boolean enCicloDFS(int v, int u){
            boolean res = false; visitados[v] = 1;
            ListaConPI<Adyacente> l = adyacentesDe(v);
            for ( l.inicio(); !l.esFin() && !res; l.siguiente() ){
                int w = l.recuperar().getDestino();
                if ( visitados[w]==0 ) res = enCicloDFS(w, u);
                else if ( u==v ) res = true;
            }
            return res;
        }
    }
}