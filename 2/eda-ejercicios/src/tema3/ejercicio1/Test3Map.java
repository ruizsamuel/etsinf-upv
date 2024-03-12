package tema3.ejercicio1;

import librerias.estructurasDeDatos.deDispersion.TablaHash;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Map;

import java.util.Scanner;

/**
 * Ejercicio 1.2 Diseña un nuevo programa Test3Map que lea un texto desde el teclado y
 * muestre en pantalla una lista en la que cada línea contenga una palabra repetida del
 * texto (que haya aparecido más de una vez en él) y el número de veces que se repite (su
 * frecuencia de aparición).
 */
public class Test3Map {

    public static void main(String[] args) {

        // Lectura de la frase (String) a partir de la que se construye el Map
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escriba palabras separadas por blancos:");
        String texto = teclado.nextLine();

        // Creación del Map vacío
        Map<String, Integer> m = getStringIntMap(texto);

        ListaConPI<String> deClaves = m.claves();
        for (deClaves.inicio(); !deClaves.esFin(); deClaves.siguiente()) {
            String k = deClaves.recuperar();
            System.out.println(k + " " + m.recuperar(k));
        }
    }

    private static Map<String, Integer> getStringIntMap(String texto) {
        Map<String, Integer> m = new TablaHash<>(texto.length());

        // Construcción del Map, via inserción/actualización de sus Entradas,
        String[] palabrasDelTexto = texto.split(" +");
        for (String s : palabrasDelTexto) {
            Integer contador = m.recuperar(s);
            m.insertar(s.toLowerCase(),
                    (contador == null ) ? 1 : contador + 1);
        }
        return m;
    }
}
