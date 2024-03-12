package tema3.ejercicio1;

import librerias.estructurasDeDatos.deDispersion.TablaHash;
import librerias.estructurasDeDatos.modelos.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Traducción Bilingüe, Palabra a Palabra, de un texto.
 * Dos metodos:
 *  1.- cargarDiccionario: 
 *      del fichero de texto "dicSpaEng.txt" ubicado en el proyecto eda
 *  2.- traducir: 
 *      traduce la frase textoE palabra a palabra consultando el diccionario d. 
 *      Cuando el diccionario no contenga
 *      la traducción para una palabra de textoE,
 *      el String resultado de traducir debe contener el literal "<error>"  
 *      en lugar de su traducción
 */

public class Test5Map {
    
    public static Map<String, String> cargarDiccionario() {
        String nombreDic = "res/dicSpaEng.txt";
        Map<String, String> m = new TablaHash<>(100);
        try { 
            Scanner ft = new Scanner(new File(nombreDic), StandardCharsets.ISO_8859_1);
            while (ft.hasNextLine()) {
                String linea = ft.nextLine();
                String[] a = linea.split("\t");
                m.insertar(a[0], a[1]);
            }
            ft.close();
            return m;
        } 
        catch (FileNotFoundException e) {
            System.out.println("** Error: No se encuentra el fichero " 
                + nombreDic);
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String traducir(String textoE, Map<String, String> d) {
        String[] palabras = textoE.split(" +");
        StringBuilder res = new StringBuilder();
        for (String s : palabras) {
            String eng = d.recuperar(s.toLowerCase());
            res.append((eng == null) ? "<error>" : eng).append(" ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        // Prueba del método traducir
        System.out.println(traducir("Hola perro gato", cargarDiccionario()));
    }
}