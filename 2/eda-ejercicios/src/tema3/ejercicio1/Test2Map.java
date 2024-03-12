package tema3.ejercicio1;

import librerias.estructurasDeDatos.deDispersion.TablaHash;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Map;

import java.util.Scanner;

/**
 * Ejercicio 1.1 Diseña un nuevo programa Test2Map que lea un texto desde el teclado y
 * muestre en pantalla el número de palabras diferentes que contiene.
 */
public class Test2Map {

    public static void main(String[] args) {

        // Lectura de la frase (String) a partir de la que se construye el Map
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escriba palabras separadas por blancos:");
        String texto = teclado.nextLine();

        // Creación del Map vacío
        Map<String, String> m = getStringStringMap(texto);

        ListaConPI<String> deClaves = m.claves();
        System.out.println("Palabras distintas que aparecen en el texto, "
                + "i.e. Claves del Map:\n" + deClaves.talla());
    }

    private static Map<String, String> getStringStringMap(String texto) {
        Map<String, String> m = new TablaHash<>(texto.length());

        // Construcción del Map, via inserción/actualización de sus Entradas,
        String[] palabrasDelTexto = texto.split(" +");
        for (String s : palabrasDelTexto) m.insertar(s.toLowerCase(), "");
        return m;
    }
}
