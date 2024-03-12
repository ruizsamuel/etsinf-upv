package tema6.ejercicio2;

import java.util.Arrays;

public class Test {
    /**
     * Método main para probar los métodos
     */
    public static void main(String[] args) {
        // EJERCICIO 2.2
        System.out.println("* Ejercicio 2.2 *");
        GrafoNoDirigidoEj2 grf1 = new GrafoNoDirigidoEj2(6);
        grf1.insertarArista(0,1); grf1.insertarArista(0,2);
        grf1.insertarArista(0,1); grf1.insertarArista(1,3);
        grf1.insertarArista(1,4); grf1.insertarArista(1,5);
        grf1.insertarArista(3,4); grf1.insertarArista(5,2);
        System.out.println(Arrays.toString(grf1.finDelDFS()));

        // EJERCICIO 2.3
        System.out.println("* Ejercicio 2.3 *");
        System.out.println(grf1.esConexo());
        System.out.println(new GrafoNoDirigidoEj2(2).esConexo());

        // EJERCICIO 2.4
        System.out.println("* Ejercicio 2.4 *");
        System.out.println(grf1.toStringCC());
        System.out.println(new GrafoNoDirigidoEj2(4).toStringCC());
    }
}
