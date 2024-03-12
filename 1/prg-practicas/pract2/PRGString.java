package pract2;

/**
 * Clase PRGString: clase de utilidades con métodos para trabajar con Strings.
 * 
 * @author PRG - ETSINF - DSIC - UPV
 * @version Curso 2021/2022
 */
public class PRGString {    
    
    /** No hay objetos de esta clase. */
    private PRGString() { }
    
    /**
     * Devuelve el número de 'a's en la String dada.
     * @param s String en la que se quieren contar las 'a's.
     * @return int.
     */
    public static int countA(String s) {
        // Caso base: String vacía
        if (s.length() == 0) { return 0; }
        // Caso general: String no vacía. Tratar la substring posterior.
        else if (s.charAt(0) == 'a') { return 1 + countA(s.substring(1)); }
        else { return countA(s.substring(1)); }
    }

    /**
     * Devuelve el número de 'a's en la String dada a partir
     * de una determinada posición.
     * @param s String en la que se quieren contar las 'a's.
     * @param pos posición en s donde comienza la substring.
     * @return int.
     * PRECONDICION: pos >= 0
     */
    public static int countA(String s, int pos) {
        // Caso base: String vacía
        if (pos >= s.length()) { return 0; }
        // Caso general: String no vacía. Tratar la substring posterior.
        else if (s.charAt(pos) == 'a') { return 1 + countA(s, pos + 1); }
        else { return countA(s, pos + 1); }
    }

    /**
     * Devuelve el número de 'a's en la String dada.
     * @param s String en la que se quieren contar las 'a's.
     * @return int.
     */
    public static int countA2(String s) {
        // Caso base: String vacía
        if (s.length() == 0) { return 0; }
        // Caso general: String no vacía. Tratar la substring anterior.
        else if (s.charAt(s.length() - 1) == 'a') {
            return 1 + countA2(s.substring(0, s.length() - 1));
        } else { return countA2(s.substring(0, s.length() - 1)); }
    }

    /**
     * Determina si a es o no prefijo de b.
     * @param a String.
     * @param b String.
     * @return boolean, true si a es prefijo de b
     * y false en caso contrario.
     */
    public static boolean isPrefix(String a, String b) {
        //Caso base
        if (a.length() == 0 || a.length() > b.length()) return true;
        //Caso general
        if (a.charAt(0) == b.charAt(0)) return isPrefix(a.substring(1, a.length()), b.substring(1, b.length()));
        return false;
    }

    /**
     * Determina si a es o no subcadena de b.
     * @param a String.
     * @param b String.
     * @return boolean, true si a es subcadena de b 
     * y false en caso contrario.
     */
    public static boolean isSubstring(String a, String b) {
        /* COMPLETAR Y CORREGIR EL VALOR DE RETORNO */
        return false;
    }
}