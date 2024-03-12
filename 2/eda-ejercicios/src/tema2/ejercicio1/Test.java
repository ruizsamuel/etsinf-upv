package tema2.ejercicio1;

/**
 * Clase para probar los algorítmos del Ejercicio 1 de la clase Metodos
 */
public class Test {

    /**
     * Método ejecutable main
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Ejercicio 1.1
        System.out.println("* Ejercicio 1.1 *");
        int[] ej1 = {-5,-3,-2,0,1,4,5,7};
        System.out.println(Metodos.metodo1(ej1));

        // Ejercicio 1.2
        System.out.println("* Ejercicio 1.2 *");
        int[] ej2 = {4,3,2,3,4,5,6,7};
        System.out.println(Metodos.metodo2(ej2));

        // Ejercicio 1.3
        System.out.println("* Ejercicio 1.3 *");
        int[] ej3 = {-1,0,1,2,3,4,5,6};
        System.out.println(Metodos.metodo3(ej3));

        // Ejercicio 1.4
        System.out.println("* Ejercicio 1.4 *");
        String[] ej4 = {"aa", "ab", "bc", "dd"};
        System.out.println(Metodos.metodo4(ej4, "ab", "bc"));
    }
}
