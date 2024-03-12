package tema3.ejercicio1;

import librerias.estructurasDeDatos.deDispersion.TablaHash;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Map;

public class AnalizadorDeTexto {

    protected Map<String, Integer> m;
    
    /** Construye un Analizador del Texto t, considerando que
     *  el separador de sus palabras es el espacio en blanco
     */
    public AnalizadorDeTexto(String t) {
        String[] palabras = t.split(" +");
        m = new TablaHash<>(palabras.length);
        for (String palabra : palabras) {
            String pal = palabra.toLowerCase();
            Integer frec = m.recuperar(pal);
            if (frec != null) {
                frec++;
                m.insertar(pal, frec);
            } else {
                m.insertar(pal, 1);
            }
        }
    }
    
    /** Devuelve el número de palabras con frecuencia de aparición mayor
     *  que n que aparecen en el texto tratado por un Analizador.   
     *  Así, por ejemplo, si n = 0, devuelve el número de palabras distintas
     *  que aparecen en el texto; si n=1 devuelve el número de palabras
     *  repetidas que tiene el texto, etc.
     *
     * @param n Número n
     *
     */
    public int frecuenciaMayorQue(int n) {
        ListaConPI<String> claves = m.claves();
        int res = 0;
        for (claves.inicio(); !claves.esFin(); claves.siguiente()) if (m.recuperar(claves.recuperar()) > n) res++;
        return res;
    }

    public static void main(String[] args) {
        // Prueba método frecuenciaMayorQue
        System.out.println(new AnalizadorDeTexto("p1 p1 p2 p3 p3 p1").frecuenciaMayorQue(2));
    }
}