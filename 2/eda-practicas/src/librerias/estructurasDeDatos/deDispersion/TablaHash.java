package librerias.estructurasDeDatos.deDispersion;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * TablaHash: implementación de una Tabla Hash Enlazada
 * en la que sus cubetas, o listas de colisiones, se
 * representan mediante Listas con PI de EntradaHash<C, V>
 * 
 * @param <C>  tipo de las Claves del Map que implementa
 * @param <V>  tipo de Valores del Map que implementa
 * 
 * @author (EDA-QA), ruizsamuel
 * @version (Curso 2021-2022)
 */
public class TablaHash<C, V> implements Map<C, V> {
    
    // Una Tabla Hash TIENE:
    
    // UNA CTE JAVA que representa...
    /** El valor (float) del factor de carga estándar (por defecto) de una
     *  Tabla Hash, el mismo que se usa en la clase java.util.HashMap */
    public static final double FACTOR_DE_CARGA = 0.75;
    
    // UNA CTE JAVA que representa...
    /** El valor (boolean) que indica si una Tabla Hash realiza 
     *  Rehashing cuando su factor de carga supera FC_ESTÁNDAR
     */
    public static boolean REHASHING = true; // en Parte 1 vale false;
	
    public static void setRehashing(boolean b) { REHASHING = b; }
    
    // Un array de Listas Con PI de EntradaHash<C, V> elArray:
    // - elArray[h] representa una cubeta, o lista de    
    //   colisiones asociadas al índice Hash h
    // - elArray[h] contiene la referencia a la Lista     
    //   Con PI donde se encuentran todas las Entradas  
    //   cuya Clave tiene un índice Hash h
    protected ListaConPI<EntradaHash<C, V>>[] elArray;
    
    // Una talla que representa el número de Entradas
    // almacenadas en una Tabla Hash o, equivalentemente, 
    // en sus cubetas
    protected int talla; 
    
    // Un número de operaciones de Rehashing (int) efectuadas
    // para mejorar el tiempo promedio que tardan en localizarse
    // las claves de sus talla Entradas
    private int numRH;
    
    
    // Un metodo indiceHash que representa la función de
    // Dispersion de la Tabla
    //**SIN ESTE METODO NO SE TIENE UNA TABLA HASH, SOLO UN ARRAY**
    // Devuelve el índice Hash de la Clave c de una Entrada,
    // i.e. la posición de la cubeta en la que se ubica
    // la Entrada de Clave c
    protected int indiceHash(C c) {
        int indiceHash = c.hashCode() % elArray.length;
        if (indiceHash < 0) indiceHash += elArray.length; 
        return indiceHash;
    }
    
    /** Crea una Tabla Hash vacia, con tallaMaximaEstimada  
     *  Entradas y factor de carga 0.75 */
    public TablaHash(int tallaMaximaEstimada) {
        int n = (int) (tallaMaximaEstimada / FACTOR_DE_CARGA);
        int capacidad = siguientePrimo(n);
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++) 
            elArray[i] = new LEGListaConPI<>();
        talla = 0;
        numRH = 0;
    }
    // Devuelve un numero primo MAYOR o IGUAL a n, 
    // i.e. el primo que sigue a n
    public static int siguientePrimo(int n) {
        if (n % 2 == 0) n++;
        for (; !esPrimo(n); n += 2); 
        return n;
    } 
    // Comprueba si n es un numero primo
    protected static boolean esPrimo(int n) {
        for (int i = 3; i * i <= n; i += 2) 
            if (n % i == 0) return false; // n NO es primo
        return true; // n SI es primo
    }    
    
    /** Comprueba si una Tabla Hash esta vacia,  
     *  i.e. si tiene 0 Entradas */
    public boolean esVacio() { return talla == 0; }
    
    /** Devuelve la talla, o número de Entradas,
      * de una Tabla Hash */
    public int talla() { return talla; } 
    
    /** Devuelve el numero de operaciones de Rehashing que, 
      *  en su caso, se hayan efectuado para crear una 
      *  Tabla Hash */
    public int numeroDeRH() { return numRH; } 

    
    private ListaConPI<EntradaHash<C, V>> localizar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> cubeta = elArray[pos];
        for (cubeta.inicio(); 
             !cubeta.esFin() && !cubeta.recuperar().clave.equals(c); 
             cubeta.siguiente());
        return cubeta;
    }
        
    /** Devuelve el valor de la Entrada con Clave c de una 
     *  Tabla Hash, o null si tal entrada no está en la Tabla */
    public V recuperar(C c) {
        V valor = null;
        // Búsqueda en cubeta de la Entrada de clave c cuyo valor se quiere recuperar
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Resolución de la Búsqueda: SII está la Entrada se recupera su valor
        if (!cubeta.esFin()) { valor = cubeta.recuperar().valor; }
        return valor;
    }
    
    /** Elimina la Entrada con Clave c de una Tabla Hash y 
     *  devuelve su valor asociado, o null si tal entrada 
     *  no está en la Tabla */
    public V eliminar(C c) {
        V valor = null;
        // Búsqueda en cubeta de la Entrada de clave a eliminar
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Resolución de la Búsqueda:
        // SII está la Entrada se elimina, tras recuperar su valor
        if (!cubeta.esFin()) {
            valor = cubeta.recuperar().valor;
            cubeta.eliminar();
            talla--;
        }
        return valor;
    }
        
    /** Inserta la Entrada (c, v) en una Tabla Hash y
     *  devuelve el antiguo valor asociado a c, o null 
     *  si tal entrada no está en la Tabla */
    // Invoca al metodo rehashing() SII
    // - El valor de la constante REHASHING es true
    // AND
    // - TRAS insertar una nueva Entrada en su  
    //   correspondiente cubeta e incrementar la 
    //   talla de la Tabla, factorCarga() > FACTOR_DE_CARGA
    public V insertar(C c, V v) {
        V antiguoValor = null;
        // Búsqueda en cubeta de la Entrada de clave c
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Resolución de la búsqueda:
        // si la Entrada (c, v) ya existe se actualiza su valor, y si no se inserta
        if (cubeta.esFin()) { 
            // si no está, inserción efectiva de la Entrada (c, v)
            cubeta.insertar(new EntradaHash<>(c, v));
            talla++;
                       
            if (factorCarga() > FACTOR_DE_CARGA && REHASHING) { 
                numRH++;
                rehashing(); 
            }            
        }
        else { 
            // Si ya está, actualizar (el valor de la) Entrada y retornar el antiguo
            antiguoValor = cubeta.recuperar().valor;
            cubeta.recuperar().valor = v; 
        }
        return antiguoValor;
    }
    
    // Metodo que implementa el Rehashing. Por motivos obvios, NO se 
    // debe volver a inicializar el atributo numRH
    // Para tener en cuenta el coste de las operaciones de
    // rehashing en el tiempo que, en promedio, tarda en localizarse
    // una de las talla Entradas de una Tabla, el atributo numColisiones
    // TAMPOCO se debe volver a inicializar en este metodo
    protected final void rehashing() {
        ListaConPI<EntradaHash<C, V>>[] contenido = elArray;
        elArray = new LEGListaConPI[siguientePrimo(elArray.length * 2)];
        talla = 0;
        for (int i = 0; i < elArray.length; i++)
            elArray[i] = new LEGListaConPI<>();
        for (ListaConPI<EntradaHash<C,V>> cubeta : contenido)
            for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente())
                insertar(cubeta.recuperar().clave, cubeta.recuperar().valor);
    }
    
    /** Devuelve una ListaConPI con las talla() claves  
     *  de una Tabla Hash */
    public ListaConPI<C> claves() {
        ListaConPI<C> l = new LEGListaConPI<>();
        for (ListaConPI<EntradaHash<C, V>> entradaHashListaConPI : elArray)
            for (entradaHashListaConPI.inicio(); !entradaHashListaConPI.esFin(); entradaHashListaConPI.siguiente())
                l.insertar(entradaHashListaConPI.recuperar().clave);
        return l;
    }
    
    /** Devuelve el factor de carga (real) de una Tabla Hash,
     *  lo que equivale a la longitud media de sus cubetas en  
     *  una implemetación Enlazada de la Tabla */
    public final double factorCarga() { 
        return (double) talla / elArray.length; 
    }
    
    /** Devuelve un String con las Entradas de una Tabla Hash
     *  en un formato texto dado (ver toString de EntradaHash)
     */
    // RECUERDA: usa la clase StringBuilder por eficiencia
    public final String toString() {
        StringBuilder res = new StringBuilder();
        for (ListaConPI<EntradaHash<C, V>> cubeta : elArray) 
            for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente()) 
                res.append(cubeta.recuperar()).append("\n");
        return res.toString(); 
    }
    
    // Métodos para el análisis de la eficiencia de una
    // Tabla Hash Enlazada
    
    /** Devuelve la desviación típica de las longitudes de las
     *  cubetas de una Tabla Hash Enlazada */
    public final double desviacionTipica() {
        /* COMPLETAR */
        double media = factorCarga();
        double sumatorio = 0;
        for (ListaConPI<EntradaHash<C,V>> l : elArray) sumatorio += Math.pow((l.talla() - media), 2);

        return Math.sqrt(sumatorio / elArray.length);
    }
    
    /** Devuelve el coste promedio de localizar
     *  una clave de una Tabla Hash Enlazada, 
     *  calculado a partir del número de
     *  colisiones que se producen al localizar 
     *  sus talla claves
     */
    public final double costeMLocalizar() {
        int colisiones = 0;
        for (ListaConPI<EntradaHash<C,V>> l : elArray) colisiones += (l.talla() * (l.talla() - 1) / 2);
        return (double) colisiones / talla;
    }

    /** Devuelve un String con el histograma de ocupación
     *  de una Tabla Hash Enlazada en formato texto. Asi, 
     *  en cada una de sus líneas deben aparecer dos valores
     *  enteros separados por un tabulador: una longitud de 
     *  cubeta (valor int en el intervalo [0, 9]) y un número
     *  de cubetas. 
     *  MUY IMPORTANTE: el número de cubetas que aparece en
     *  la línea i, ES:
     ** (a) Si i en [0, 8], el número de cubetas de la Tabla
     **     que tienen una longitud i
     ** (b) Si i = 9 (última línea), el número de cubetas de
     **     la Tabla que tienen una longitud 9 o MAYOR
     */      
    public String histograma() {
        StringBuilder res = new StringBuilder();
        int[] histo = new int[10];
        for (ListaConPI<EntradaHash<C, V>> entradaHashListaConPI : elArray) {
            int longCubeta = entradaHashListaConPI.talla();
            if (longCubeta < 9) {
                histo[longCubeta]++;
            } else {
                histo[9]++;
            }
        }
        for (int i = 0; i < histo.length; i++) {
            res.append(i).append("\t").append(histo[i]).append("\n");
        }        
        return res.toString();
    }
}