package aplicaciones.biblioteca;

/**
 * Posting: clase que representa la informacion asociada a   
 * la aparicion de un Termino de un Indice en un libro, i.e. el titulo   
 * del libro y (el numero de) la linea donde aparece el Termino.
 * 
 * @author  Profesores EDA
 * @version Septiembre 2023
 */
public class Posting {
        
    protected String tituloLibro;
    protected int lineaLibro;
        
    /** Crea un Posting del libro de titulo t y de su numero de linea n. */
    public Posting(String t, int n) {
        tituloLibro = new String(t.substring(0, t.indexOf(".txt")));
        lineaLibro = n;
    }
    
    /** Devuelve un String con un Posting en formato texto. */
    public String toString() {
        return tituloLibro + ", linea " + lineaLibro + "\n";
    }
}
