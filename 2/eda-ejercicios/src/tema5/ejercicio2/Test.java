package tema5.ejercicio2;

import librerias.excepciones.ElementoNoEncontrado;

import java.util.Arrays;

public class Test {
    /**
     * Método main para pruebas del Ejercicio 2
     */
    public static void main(String[] args) {
        // EJERCICIO 2.1
        System.out.println("* Ejercicio 2.1 (b) *");
        ABBEj2<Integer> ej1 = new ABBEj2<>();
        ej1.insertar(1); ej1.insertar(3); ej1.insertar(5); ej1.insertar(2); ej1.insertar(8);
        System.out.println(ej1.enQueNivel(3));
        System.out.println(ej1.enQueNivel(8));
        System.out.println(ej1.enQueNivel(1));
        System.out.println(ej1.enQueNivel(2));
        System.out.println(ej1.enQueNivel(7) + "\n");

        // EJERCICIO 2.2
        System.out.println("* Ejercicio 2.2 (b) *");
        ABBEj2<Integer> ej2 = new ABBEj2<>();
        ej2.insertar(1); ej2.insertar(3); ej2.insertar(5); ej2.insertar(2); ej2.insertar(8);
        System.out.println(ej2.contarMayoresQue(4));

        // EJERCICIO 2.3
        System.out.println("* Ejercicio 2.3 (a) *");
        ABBEj2<Integer> ej3 = new ABBEj2<>();
        ej3.insertar(1); ej3.insertar(3); ej3.insertar(5); ej3.insertar(2); ej3.insertar(8);
        System.out.println(ej3.padreDe(2));
        System.out.println(ej3.padreDe(3));
        System.out.println(ej3.padreDe(1));
        System.out.println(ej3.padreDe(10));
        System.out.println("* Ejercicio 2.3 (b) *");
        System.out.println(ej3.hermanoDe(2));
        System.out.println(ej3.hermanoDe(1));
        System.out.println(ej3.hermanoDe(10));

        // EJERCICIO 2.4
        System.out.println("* Ejercicio 2.4 *");
        ABBEj2<Integer> ej4 = new ABBEj2<>();
        ej4.insertar(1); ej4.insertar(6); ej4.insertar(5); ej4.insertar(2); ej4.insertar(8);
        System.out.println(ej4.toListaConPI());

        // EJERCICIO 2.6
        System.out.println("* Ejercicio 2.6 *");
        ABBEj2<Integer> ej6Eq = new ABBEj2<>();
        ABBEj2<Integer> ej6NoEq = new ABBEj2<>();
        Arrays.asList(7, 2, 9, 1, 5).forEach(ej6Eq::insertar);
        Arrays.asList(7, 2, 9, 1, 5, 3).forEach(ej6NoEq::insertar);
        try {
            System.out.println(ej6Eq.alturaDeEquilibrado());
            System.out.println(ej6NoEq.alturaDeEquilibrado());
        } catch (ElementoNoEncontrado e) {
            System.err.println("Arbol no equilibrado");
        }
    }
}
