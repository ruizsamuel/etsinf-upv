package tema5.ejercicio1;

import librerias.estructurasDeDatos.jerarquicos.ABBMapOrdenado;
import librerias.estructurasDeDatos.modelos.MapOrdenado;

import java.util.Arrays;

public class Test {
    /**
     * Método main para probar los métodos del Ejercicio 1
     */
    public static void main(String[] args) {

        // EJERCICIO 1.1
        System.out.println("* Ejercicio 1.1 *");
        MapOrdenado<Integer, String> ej1 = new ABBMapOrdenado<>();
        ej1.insertar(1, "Primero");
        ej1.insertar(3, "Tercero");
        ej1.insertar(2, "Segundo");
        ej1.insertar(6, "Sexto");
        System.out.println(Metodos.entradas(ej1) + "\n");

        // EJERCICIO 1.2
        System.out.println("* Ejercicio 1.2 *");
        Integer[] ej2 = new Integer[]{4,2,-2,7,3,10};
        Metodos.mapSort(ej2);
        System.out.println(Arrays.toString(ej2) + "\n");

        // EJERCICIO 1.3
        System.out.println("* Ejercicio 1.3 *");
        int[] ej3 = new int[]{1,2,3,4,5,6,7};
        System.out.println(Metodos.hayDosQueSuman(ej3, 12));
        System.out.println(Metodos.hayDosQueSuman(ej3, 13) + "\n");
    }
}