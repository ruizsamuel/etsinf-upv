package tema4.ejercicio2;


/** Clase Programa para pruebas
 */
public class Test {
    public static void main(String[] args) {
        // EJERCICIO 2.1
        MonticuloBinarioEj2<Integer> ej1_2 = new MonticuloBinarioEj2<>();
        for (Integer i : new Integer[]{8,4,5,6,7}) ej1_2.insertar(i);

        System.out.println("* Ejercicio 2.1 *");
        System.out.println(ej1_2.hayMenoresQue(4));

        // EJERCICIO 2.2
        System.out.println("* Ejercicio 2.2 *");
        System.out.println(ej1_2.hayMayoresQue(7));

        // EJERCICIO 2.3
        MonticuloBinarioEj2<Integer> ej3 = new MonticuloBinarioEj2<>();
        for (Integer i : new Integer[]{1,7,2,9,8,6,3}) ej3.insertar(i);

        System.out.println("* Ejercicio 2.3 *");
        System.out.println(ej3.estaEn(5));

        // EJERCICIO 2.4
        MonticuloBinarioEj2<Integer> ej4 = new MonticuloBinarioEj2<>();
        for (Integer i : new Integer[]{1,7,2,9,8,6,3}) ej4.insertar(i);

        System.out.println("* Ejercicio 2.4 *");
        ej4.borrarHojasEnRango(4,8);
        System.out.println(ej4);

        // EJERCICIO 2.5
        MonticuloBinarioEj2<Integer> ej5 = new MonticuloBinarioEj2<>();
        for (Integer i : new Integer[]{1,7,2,9,8,6,3}) ej5.insertar(i);

        System.out.println("* Ejercicio 2.5 *");
        System.out.println(ej5);
        ej5.eliminar(4);
        System.out.println(ej5);

        // EJERCICIO 2.6
        MonticuloBinarioEj2<Integer> ej6 = new MonticuloBinarioEj2<>();
        for (Integer i : new Integer[]{1,7,1,9,1,6,3}) ej6.insertar(i);

        System.out.println("* Ejercicio 2.6 *");
        System.out.println(ej6.igualesAlMinimo());

        // EJERCICIO 2.7
        Integer[] ej7 = {null, 1, 7, 2, 9, 8, 6, 3};
        System.out.println("* Ejercicio 2.7 *");
        System.out.println(MonticuloBinarioEj2.esHeap(ej7));

        // EJERCICIO 2.8
        MonticuloBinarioEj2<Integer> ej8 = new MonticuloBinarioEj2<>();
        for (Integer i : new Integer[]{1,7,1,9,1,6}) ej8.insertar(i);

        System.out.println("* Ejercicio 2.8 *");
        System.out.println(ej8.menoresQue(7));

        // EJERCICIO 2.9
        MonticuloBinarioEj2<Integer> ej9 = new MonticuloBinarioEj2<>();
        for (Integer i : new Integer[]{1,7,1,9,1,6}) ej9.insertar(i);

        System.out.println("* Ejercicio 2.9 *");
        System.out.println(ej9);
        System.out.println("Eliminado: " + ej9.eliminar1aHoja());
        System.out.println(ej9);
    }
}
