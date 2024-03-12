package aplicaciones.pruebasOrdenacion;

/**
 * GeneradorDeString(n) permite generar String que tienen
 * iguales los n caracteres iniciales.
 * Ejemplo de uso para crear dos cadenas s1 y s2 que comparten los 
 * primeros 10 caracteres:
 * GeneradorDeString g = new GeneradorDeString(10);
 *  String s1 = g.generar();
 *  String s2 = g.generar();
 *
 * @author (EDA) 
 * @version (Curso 2017-2018)
 */

public class GeneradorDeString {
    
    private final int numIg;   // Número de caracteres iniciales iguales de los String
    private final String base; // SubString inicial común a los String generados
    
    /**
     * Crea un GeneradorDeString cuyos n primeros caracteres 
     * son iguales.
     */
    public GeneradorDeString(int n) {
        this.numIg = n;
        StringBuilder s = new StringBuilder();
        int desAlea = (int) (Math.random() * 10);
        for (int i = 0; i < numIg; i++) {
            s.append((char) ((i + desAlea) % 256 + (int) '0'));
        }
        this.base = s.toString();
    }

    /**
     * Devuelve un String con sus n primeros caracteres
     * idénticos, aunque elegidos al azar.
     */
    public String generar() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= (int) (Math.random() * 2 * numIg); i++) {
            s.append((char) ((int) (Math.random() * 128) + (int) '0'));
        }
        return this.base + s;
    }
}