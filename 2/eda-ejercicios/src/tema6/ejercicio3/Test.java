package tema6.ejercicio3;

import java.util.Arrays;

public class Test {
    /**
     * Método main para pruebas
     */
    public static void main(String[] args) {
        // EJERCICIO 3.1
        GrafoNoDirigidoEj3 grafo = new GrafoNoDirigidoEj3(4);
        grafo.insertarArista(0,1); grafo.insertarArista(1,2); grafo.insertarArista(1,3);
        grafo.insertarArista(2,3); grafo.insertarArista(2,0);
        System.out.println("* Ejercicio 3.1 *");
        System.out.println(Arrays.toString(grafo.arbolRecubrimiento()));
        System.out.println(Arrays.toString(new GrafoNoDirigidoEj3(3).arbolRecubrimiento()));
    }
}
