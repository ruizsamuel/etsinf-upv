package examen_21_22;

import librerias.estructurasDeDatos.grafos.Adyacente;
import librerias.estructurasDeDatos.grafos.GrafoNoDirigido;
import librerias.estructurasDeDatos.jerarquicos.ABB;
import librerias.estructurasDeDatos.jerarquicos.NodoABB;
import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class SegundoParcial {
    /**
     * Método main para probar las soluciones
     */
    public static void main(String[] args) {
        // EJERCICIO 1
        System.out.println("* Ejercicio 1 *");
        ABBInteger ej1 = new ABBInteger();
        ej1.insertar(5); ej1.insertar(4); ej1.insertar(7);
        ej1.insertar(2); ej1.insertar(6); ej1.insertar(9);
        System.out.println(ej1.caminosQueSuman(9));
        System.out.println(ej1.caminosQueSuman(11));
        System.out.println(ej1.caminosQueSuman(10));
        System.out.println(ej1.caminosQueSuman(19));

        // EJERCICIO 3
        System.out.println("* Ejercicio 3 *");
        GrafoEjercicio3 g = new GrafoEjercicio3(9);
        g.insertarArista(0,1); g.insertarArista(0,2);
        g.insertarArista(1,2); g.insertarArista(1,4);
        g.insertarArista(1,3); g.insertarArista(2,5);
        g.insertarArista(4,5); g.insertarArista(5,7);
        g.insertarArista(5,6); g.insertarArista(3, 6);
        g.insertarArista(7,6); g.insertarArista(7,8);
        g.insertarArista(6,8);
        System.out.println(g.verticesCercanos(0,1));
        System.out.println(g.verticesCercanos(0,2));
        System.out.println(g.verticesCercanos(0,3));
    }

    // EJERCICIO 1

    /**
     * Implementa en la clase ABBInteger un método caminoQueSuma que, con el menor coste
     * posible y recursivamente, compruebe si existe una secuencia de nodos (camino) desde su raíz a uno de
     * sus descendientes cuyos datos sumen s (un número entero, argumento del método).

     * El método tiene las siguientes precondiciones: s es un número positivo, y
     * this es un árbol cuyos datos son todos números positivos y con un dato en
     * su raíz que es menor estricto que s.
     */
    static class ABBInteger extends ABB<Integer> {
        public boolean caminosQueSuman(int s) {
            return caminosQueSuman(raiz, s);
        }

        protected boolean caminosQueSuman(NodoABB<Integer> actual, int s) {
            if (actual == null) return false;
            if (actual.dato == s) return true;
            if (actual.dato > s) return false;
            int diff = s - actual.dato;
            if (diff <= actual.dato) return caminosQueSuman(actual.izq, diff) || caminosQueSuman(actual.der, diff);
            return caminosQueSuman(actual.izq, diff);
        }
    }

    // EJERCICIO 3

    /**
     * Implementa, en la clase Grafo, para un grafo no ponderado, un método de instancia
     * verticesCercanos que, dados un vértice y una distancia, devuelva un String que contenga los
     * vértices alcanzables desde el vértice dado con esa distancia máxima, indicando también
     * para cada vértice alcanzable su distancia (al ser el grafo no ponderado, la distancia entre
     * dos vértices es igual al número de aristas que los conectan, dado que todas tienen peso 1.0).
     */
    static class GrafoEjercicio3 extends GrafoNoDirigido {
        public GrafoEjercicio3(int numVertices) {
            super(numVertices);
        }

        public String verticesCercanos (int v, int d) {
            StringBuilder res = new StringBuilder().append("[");
            distanciaMin = new double[numVertices()];
            for (int i = 0; i < numVertices(); i++) {
                distanciaMin[i] = Double.MAX_VALUE;
            }
            distanciaMin[v] = 0;
            q = new ArrayCola<>();
            q.encolar(v);
            while(!q.esVacia()) {
                int u = q.desencolar();
                if (distanciaMin[u] < d) {
                    ListaConPI<Adyacente> l = adyacentesDe(u);
                    for (l.inicio(); !l.esFin(); l.siguiente()) {
                        int w = l.recuperar().getDestino();
                        if (distanciaMin[w] == Double.MAX_VALUE) {
                            distanciaMin[w] = distanciaMin[u] + 1;
                            res.append("(").append(w).append(", ").append((int)distanciaMin[w]).append(")");
                            q.encolar(w);
                        }
                    }
                }
            }
            return res.append("]").toString();
        }
    }
}
