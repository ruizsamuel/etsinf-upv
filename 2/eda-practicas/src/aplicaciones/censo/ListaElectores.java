package aplicaciones.censo;

import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;

/**
 * ListaElectores: representa una lista de habitantes, 
 *                 registrados en el censo, y por ello, electores
 * 
 * @author  Profesores EDA, ruizsamuel
 * @version Septiembre 2023
 */

public class ListaElectores {
   
    private final ListaConPI<Habitante> censo;
    private final int talla;
    
    /**
     * Métodos consultores de atributos
     */
    public ListaConPI<Habitante> getCenso() { return censo; }
    public int getTalla() { return talla; }
    
    /**
     * Devuelve el String que representa una ListaElectores 
     * 
     * @return el String con la ListaElectores en el formato texto dado. 
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (talla == 0) return res.toString();
        censo.inicio();
        for (int pos = 0; pos <= censo.talla() - 2; pos++) {
            res.append(censo.recuperar()).append(", \n");
            censo.siguiente();
        }
        res.append(censo.recuperar());
        return res.toString();
    }
   
    /**
     * Crea una ListaElectores con habitantes aleatorios
     * 
     * @param orden Un boolean que indica si el censo,  
     *              debe estar ordenada ascendentemente (true) o no (false). 
     *              
     * @param n     Un int que indica la talla, número de elementos, de la lista              
     */
    public ListaElectores(boolean orden, int n) {
        talla = n;
        censo = (orden) ? new LEGListaConPIOrdenada<>() : new LEGListaConPI<>();
        for (int i = 0; i < n; i++) censo.insertar(new Habitante());
    }

    /**
     * Crea una ListaElectores a partir de una ListaConPI de Habitantes
     *
     * @param l     ListaConPI<Habitante> con los Habitantes del censo
     */
    public ListaElectores(ListaConPI<Habitante> l) {
        talla = l.talla();
        censo = l;
    }

    /**
     * Devuelve una ListaElectores que sea el subconjunto de this ListaElectores
     * que contenga los habitantes cuyo código postal esté en el rango [CP1 ... CP2]. Si al invocarlo, cp1 = cp2,
     * entonces se obtiene la ListaElectores de un único código postal.
     *
     * @param cp1 Código postal mínimo del rango
     * @param cp2 Código postal máximo del rango
     *
     * @return Una ListaElectores (ordenada) que contiene aquellos habitantes cuyo código postal se encuentra
     * entre el rango [CP1 ... CP2]
     */
    public ListaElectores getCensoCP(int cp1, int cp2) {
        ListaConPI<Habitante> n = new LEGListaConPIOrdenada<Habitante>();
        censo.inicio();
        Habitante h;
        while(!censo.esFin()) {
            h = censo.recuperar();
            if (h.getCp() >= cp1 && h.getCp() <= cp2) n.insertar(h);
            censo.siguiente();
        }
        return new ListaElectores(n);
    }

    /**
     * Devuelve una ListaElectores que sea el subconjunto de this ListaElectores
     * que contenga los habitantes que al menos uno de sus apellidos o nombre contenga el prefijo (prefijo).
     *
     * @param prefijo Prefijo a buscar
     *
     * @return Una ListaElectores (ordenada) filtrada
     */
    public ListaElectores buscador(String prefijo) {
        ListaConPI<Habitante> n = new LEGListaConPIOrdenada<Habitante>();
        censo.inicio();
        Habitante h;
        while(!censo.esFin()) {
            h = censo.recuperar();
            if (h.getApellido1().startsWith(prefijo) || h.getApellido2().startsWith(prefijo)
                    || h.getNombre().startsWith(prefijo)) n.insertar(h);
            censo.siguiente();
        }
        return new ListaElectores(n);
    }
    
    /**
     * Devuelve el índice o posición del Habitante (h) en una ListaElectores,
     * o -1 si (h) no forma parte de la lista.
     * 
     * @param h un Habitante
     * @return  el índice de (h) en un censo, un valor int
     *          0 o positivo si (h) está en el censo
     *          o -1 en caso contrario
     */
    protected int indice(Habitante h) {
        censo.inicio();
        int i;
        for (i = 0; !censo.esFin() && !censo.recuperar().equals(h); i++) censo.siguiente();
        return i < censo.talla() ? i : (-1);
    }
    
}
