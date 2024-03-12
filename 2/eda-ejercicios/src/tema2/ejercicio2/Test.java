package tema2.ejercicio2;

import java.util.Arrays;

/**
 * Clase para probar los algorítmos del Ejercicio 2 de la clase Metodos
 */
public class Test {

    /**
     * Método ejecutable main
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Ejercicio 2.2
        System.out.println("* Ejercicio 2.2 *");
        Integer[] ej2 = {-5,3,4,5,1,4,5,7};
        Metodos.mergeDC(ej2,0, 3, 7);
        System.out.println(Arrays.toString(ej2));

        // Ejercicio 2.4
        System.out.println("* Ejercicio 2.3 *");
        int[] ej4a = {-2, 3, 4, -3, 5, 6, -2};
        int[] ej4b = {-2, 11, -4, 13, -5, 2};
        System.out.println(Metodos.subSumaMax(ej4a));
        System.out.println(Metodos.subSumaMax(ej4b));

        //Ejercicio 2.5
        System.out.println("* Ejercicio 2.5 *");
        int[] ej5a = {1,2,3,4,4,5};
        int[] ej5b = {1,1,2};
        int[] ej5c = {1,2};
        System.out.println(Metodos.valorRepetido(ej5a));
        System.out.println(Metodos.valorRepetido(ej5b));
        System.out.println(Metodos.valorRepetido(ej5c));

        //Ejercicio 2.6
        System.out.println("* Ejercicio 2.6 *");
        int[] ej6a = {0,1,2,4,5};
        int[] ej6b = {1,2,3,4};
        int[] ej6c = {-3,-2,-1,0,2};
        System.out.println(Metodos.primerOmitido(ej6a));
        System.out.println(Metodos.primerOmitido(ej6b));
        System.out.println(Metodos.primerOmitido(ej6c));

        //Ejercicio 2.7
        System.out.println("* Ejercicio 2.7 *");
        String[] ej7 = {"a", "aa", "bba", "bbb", "baba"};
        System.out.println(Metodos.deLongitudX(ej7, 3));
    }
}
