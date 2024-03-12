package tema6.ejercicio1;

import librerias.estructurasDeDatos.grafos.Adyacente;
import librerias.estructurasDeDatos.grafos.GrafoDirigido;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class GrafoDirigidoEj1 extends GrafoDirigido {
    public GrafoDirigidoEj1(int numVertices) {
        super(numVertices);
    }

    // EJERCICIO 1.3

    /**
     * Ejercicio 1.3 (a)
     * Devuelve el grado del grafo this
     */
    public int grado() {
        int[] grados = new int[numVertices()];
        for (int i = 0; i < elArray.length; i++) {
            ListaConPI<Adyacente> l = elArray[i];
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                grados[i]++;
                grados[l.recuperar().getDestino()]++;
            }
        }
        int max = 0;
        for (int g : grados) max = Math.max(g, max);
        return max;
    }

    /**
     * Ejercicio 1.3 (b)
     * Devuelve el peso de la arista con mayor peso del grafo this
     * Se asume que existe al menos una arista y que los pesos no son negativos
     */
    public double aristaMayorPeso() {
        double max = 0.0;
        for (ListaConPI<Adyacente> l : elArray)
            for(l.inicio(); !l.esFin(); l.siguiente()) max = Math.max(max, l.recuperar().getPeso());
        return max;
    }

    /**
     * Ejercicio 1.3 (c)
     * Devuelve true si el grafo this es regular (todos los vértices tienen el mismo grado) o false en caso contrario
     */
    public boolean esRegular() {
        int[] grados = new int[numVertices()];
        for (int i = 0; i < elArray.length; i++) {
            ListaConPI<Adyacente> l = elArray[i];
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                grados[i]++;
                grados[l.recuperar().getDestino()]++;
            }
        }
        int grado = grados[0];
        for (int g : grados) if (g != grado) return false;
        return true;
    }

    // EJERCICIO 1.4

    /**
     * Ejercicio 1.4 (a)
     * Devuelve el primer vértice (el que tiene el índice
     * más bajo) del grafo this con grado de entrada |V|-1, o -1 si no existe.
     */
    public int getVerticeReceptivo() {
        int[] gradosEntrada = new int[numVertices()];
        for (ListaConPI<Adyacente> l : elArray)
            for (l.inicio(); !l.esFin(); l.siguiente())
                gradosEntrada[l.recuperar().getDestino()]++;

        for (int i = 0; i < gradosEntrada.length; i++) if (gradosEntrada[i] == numV - 1) return i;
        return -1;
    }

    /**
     * Ejercicio 1.4 (b)
     * Comprueba si un vértice dado del grafo this es un sumidero.
     */
    public boolean esSumidero(int v) {
        return gradoEntrada(v) > 0 && gradoSalida(v) == 0;
    }

    /**
     * Ejercicio 1.4 (c)
     * Devuelve el primer sumidero universal del grafo this, o -1 si no existe
     */
    public int getSumideroU() {
        int[] gradosEntrada = new int[numV];
        for (int i = 0; i < numV - 1; i++) {
            ListaConPI<Adyacente> l = elArray[i];
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                gradosEntrada[l.recuperar().getDestino()]++;
            }
        }
        for (int i = 0; i < numV; i++) if (gradosEntrada[i] == numV - 1 && esSumidero(i)) return i;
        return -1;
    }

    /**
     * Ejercicio 1.4 (d)
     * Devuelve la primera fuente universal del grafo this, o -1 si no existe
     */
    public int getFuenteU() {
        int[] gradosEntrada = new int[numV];
        int[] gradosSalida = new int[numV];
        for (int i = 0; i < numV - 1; i++) {
            ListaConPI<Adyacente> l = elArray[i];
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                gradosSalida[i]++;
                gradosEntrada[l.recuperar().getDestino()]++;
            }
        }
        for (int i = 0; i < numV; i++) if (gradosEntrada[i] == 0 && gradosSalida[i] == numV - 1) return i;
        return -1;
    }

    /**
     * Ejercicio 1.4 (e)
     * Devuelve true si el grafo this es completo y false en caso contrario.
     */
    public boolean esCompleto() {
        for (int i  = 0; i < numV; i++)
            for (int j = 0; j < numV; j++) if (j != i && !existeArista(i, j)) return false;

        return true;
    }
}