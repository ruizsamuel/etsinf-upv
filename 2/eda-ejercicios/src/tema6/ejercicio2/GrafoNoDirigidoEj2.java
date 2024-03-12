package tema6.ejercicio2;

import librerias.estructurasDeDatos.grafos.Adyacente;
import librerias.estructurasDeDatos.grafos.GrafoNoDirigido;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class GrafoNoDirigidoEj2 extends GrafoNoDirigido {
    public GrafoNoDirigidoEj2 (int numVertices) {
        super(numVertices);
    }

    // EJERCICIO 2.2

    /**
     * Ejercicio 2.2
     * Método lanzadera
     * Devuelve un vector de elementos que son los vértices del grafo en el orden en que
     * termina su DFS
     */
    public int[] finDelDFS() {
        int[] res = new int[numVertices()];
        ordenVisita = 0;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices(); v++) {
            if (visitados[v] == 0) finDelDFS(v, res);
        }
        return res;
    }

    /**
     * Ejercicio 2.2
     * Método recursivo
     * Devuelve un vector de elementos que son los vértices del grafo en el orden en que
     * termina su DFS
     */
    protected void finDelDFS(int v, int[] res) {
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) toArrayDFS(w, res);
        }
        res[ordenVisita] = v;
        ordenVisita++;
    }

    // EJERCICIO 2.3

    /**
     * Ejercicio 2.3
     * Método lanzadera
     * Devuelve true si el grafo this es conexo y false en caso contrario
     */
    public boolean esConexo() {
        visitados = new int[numV];
        ordenVisita = 0;
        esConexo(0);
        return ordenVisita == numV;
    }

    /**
     * Ejercicio 2.3
     * Método recursivo
     */
    protected void esConexo(int v) {
        visitados[v] = 1;
        ordenVisita++;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente())
            if (visitados[l.recuperar().getDestino()] == 0) esConexo(l.recuperar().getDestino());
    }

    // EJERCICIO 2.4

    /**
     * Ejercicio 2.4
     * Método lanzadera
     * Devuelve un String con el número de componentes
     * conexas del grafo this y los vértices de cada una.
     */
    public String toStringCC() {
        ListaConPI<ListaConPI<Integer>> CCs = new LEGListaConPI<>();
        visitados = new int[numV];
        for (int i = 0; i < numV; i++) {
            if (visitados[i] == 0) {
                ListaConPI<Integer> CC = new LEGListaConPI<>();
                toStringCC(i, CC);
                CCs.insertar(CC);
            }
        }
        StringBuilder res = new StringBuilder();
        res.append("Hay ").append(CCs.talla()).append(" componentes conexas y son: ");
        for (CCs.inicio(); !CCs.esFin(); CCs.siguiente()) {
            ListaConPI<Integer> CC = CCs.recuperar();
            CC.inicio();
            res.append("[").append(CC.recuperar());
            CC.siguiente();
            while(!CC.esFin()) {
                res.append("::").append(CC.recuperar());
                CC.siguiente();
            }
            res.append("] ");
        }
        return res.toString();
    }

    /**
     * Ejercicio 2.4
     * Método recursivo
     */
    protected void toStringCC (int v, ListaConPI<Integer> CC) {
        CC.insertar(v);
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) toStringCC(w, CC);
        }
    }
}
