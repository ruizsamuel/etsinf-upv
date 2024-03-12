package librerias.estructurasDeDatos.modelos;

/**
 * Modelo Diccionario SIN claves repetidas, o Map: Búsqueda Dinámica
 * de una Entrada de Clave dada en una Colección, para recuperar el
 * Valor asociado a esta 
 * @param <C>, el tipo de sus Claves
 * @param <V>, el tipo de sus Valores
 * 
 * @author (EDA) 
 * @version (Curso 2018-2019)
 */

public interface Map<C, V> {

    /** Inserta la Entrada (c,v) en un Map y devuelve null; si ya
     *  existe una Entrada de Clave c en el Map, devuelve su valor 
     *  asociado y lo substituye por v (o actualiza la Entrada)
     */
    V insertar(C c, V v);
    
    /** Elimina la Entrada con Clave c de un Map y devuelve su
     *  valor asociado, null si no existe una Entrada con dicha clave
     */
    V eliminar(C c);
    
    /** Devuelve el valor asociado a la clave en un Map,
     *  null si no existe una Entrada con dicha clave
     */
    V recuperar(C c);
    
    /** Comprueba si un Map esta vacío */
    boolean esVacio();
    
    /** Devuelve la talla, o número de Entradas, de un Map */
    int talla();
    
    /** devuelve una ListaConPI con las talla() Claves de un Map */
    ListaConPI<C> claves();
}

