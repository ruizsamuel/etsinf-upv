package aplicaciones.biblioteca;

/**
 * Término: clase de la Clave del Map que representa un término del
 * Índice Analítico de una Biblioteca Digital.
 * Para que sobrescriba eficientemente los metodos equals y hashCode
 * de Object, un Término TIENE UN valorHash que almacena el resultado
 * de la PRIMERA invocación al metodo hashCode de la clase sobre él.
 * De esta forma, el valor hash asociado a un Término...
 ** (a) solo se calcula una vez, independientemente del número de veces
 **     que el metodo hashCode se aplique sobre él;
 ** (b) se puede usar en el metodo equals de la clase para comprobar la
 **     igualdad de dos términos SOLO cuando sus valores Hash sean iguales.
 * Además, para poder evaluar distintos metodos hashCode, un Término TIENE
 * UNA baseHashCode que almacena la base en la que se calcula su valorHash
 * 
 * @author  Profesores EDA 
 * @version Septiembre 2023
 */
public class Termino {
    
    public static final int BASE_TRIVIAL = 1;  
    public static final int BASE_JAVA_LANG_STRING = 31;
    public static final int BASE_MCKENZIE = 4;
    
    protected String palabra;    
    protected int valorHash;
    protected int baseHashCode;
    
    /** Crea el Término asociado a una palabra y
     *  le asocia la base a emplear en el metodo hashCode
     * @param p palabra asociada
     * @param base base a emplear
     *  */
    public Termino(String p, int base) { 
        palabra = p;  
        baseHashCode = base;
        valorHash = 0;
    }
    
    /** Crea el Término asociado a una la palabra según el estándar de Java */
    public Termino(String p) { this(p, BASE_JAVA_LANG_STRING); }
    
    /** Devuelve el valor Hash de un (this) Termino de forma EFICIENTE, 
     *  i.e.
     *  al aplicar este metodo por PRIMERA vez sobre un Término de longitud n = palabra.length(),
     *  calcula su valor Hash, o valor de la siguiente función polinomial de base baseHashCode,
     *  usando la regla de Horner y, por tanto, SIN usar metodos de la clase Math:
     *  valorHash = palabra.charAt(0) * baseHashCode^(n-1)
     *              + palabra.charAt(1) * baseHashCode^(n-2)
     *              + ...
     *              + palabra.charAt[n-1]
     *  Si NO es la primera vez que se aplica el metodo, devuelve valorHash 
     */
    public int hashCode() { 
        int res = valorHash;
        if (res != 0) return res;

        for (int i = 0; i < palabra.length(); i++) res = palabra.charAt(i) + baseHashCode * res;

        valorHash = res;
        return res;
    }
   
    /** Comprueba si un (this) Termino es igual a otro de forma  
     *  eficiente, i.e. SOLO ejecuta el metodo equals de String 
     *  cuando los valores Hash de this y otro Término son iguales
     */
    public boolean equals(Object otro) {
        return  (otro instanceof Termino o) &&
                (o.hashCode() == this.hashCode()) &&
                (o.palabra.equals(this.palabra));
    }
    
    /** Devuelve un String que representa un Termino en cierto formato texto */
    public String toString() { return palabra + " (" + valorHash + ")\n"; }
	
	/** Comprueba si una palabra, p, puede ser un término del Índice de una BD,
     *  i.e. si está formada por letras del alfabeto español en minúsculas.
     */
    protected static boolean esValido(String p) {
        if (p.isEmpty()) return false;
        for (int i = 0; i < p.length(); i++) {
            if (!Character.isLetter(p.charAt(i))) return false; 
        }
        return true;
    }
}