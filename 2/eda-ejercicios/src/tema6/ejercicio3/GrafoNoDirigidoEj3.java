package tema6.ejercicio3;

import librerias.estructurasDeDatos.grafos.Adyacente;
import librerias.estructurasDeDatos.grafos.GrafoNoDirigido;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class GrafoNoDirigidoEj3 extends GrafoNoDirigido {
    public GrafoNoDirigidoEj3(int numVertices) {
        super(numVertices);
    }

    // EJERCICIO 3.1

    /**
     * Ejercicio 3.1
     * Puntero auxiliar
     */
    protected int indiceArista;

    /**
     * Ejercicio 3.1
     * Método lanzadera
     * Devuelve un árbol de recubrimiento del grafo this o null si no existe ninguno.
     */
    public String[] arbolRecubrimiento() {
        String[] aristas = new String[numV - 1];
        visitados = new int[numV];
        ordenVisita = 0;
        indiceArista = 0;
        arbolRecubrimiento(0, aristas);
        return (ordenVisita == numV) ? aristas : null;
    }

    protected void arbolRecubrimiento(int v, String[] aristas) {
        visitados[v] = 1;
        ordenVisita++;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) {
                aristas[indiceArista++] = "(" + v + ", " + w + ")";
                arbolRecubrimiento(w, aristas);
            }
        }
    }
}
