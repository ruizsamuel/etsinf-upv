package aplicaciones.biblioteca;

import librerias.estructurasDeDatos.deDispersion.TablaHash;
import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * BuscadorBD: clase que representa un Buscador eficiente, 
 * i.e. con un Índice, de una Biblioteca Digital (BD).
 * Por ello, su funcionalidad básica es...
 * 1.- INDEXAR: crear el Índice de la biblioteca, representado mediante
 *     un Map implementado mediante una Tabla Hash Enlazada.
 * 2.- BUSCAR: recuperar del Índice de la biblioteca la información que
 *     exista sobre una palabra dada (Posting List) en ella.
 * 
 * @author  Profesores EDA 
 * @version Septiembre 2023
 */
 
public class BuscadorBD {
    
    // Un BuscadorBD TIENE...
    
    // UN String listaLibros, el fichero de texto que contiene los nombres de
    // los (ficheros .txt de los) libros de una biblioteca digital. Su valor 
    // por defecto es el que figura a continuacion, por lo que debe cambiarse 
    // si procede 
    protected static String listaLibros =  "lista10.txt"; // "lista.txt"
    
    // UN String dirListaLibros, el directorio donde se ubica listaLibros. 
    // Su valor por defecto es el que figura a continuacion, por lo que debe 
    // cambiarse si procede 
    protected static String dirListaLibros = "aplicaciones" + File.separator
                                             + "biblioteca" + File.separator;
    
    // UN String dirLibros, el directorio donde se ubican los (ficheros .txt
    // de los) libros de listaLibros. Su valor por defecto es el que figura a  
    // continuacion, por lo que debe cambiarse si procede 
    protected static String dirLibros = "aplicaciones" + File.separator
                                        + "biblioteca" + File.separator 
                                        + "TXT" + File.separator;
    
    // UN String separadores, la expresion regular que define los  
    // separadores de palabras que aparecen en los libros de listaLibros.    
    // Su valor por defecto es el que figura a continuacion, por lo que  
    // debe cambiarse si procede
    protected static String separadores = 
       "[[ ]*|[,]*|[\\.]*|[\t]*|[:]*|[;]*|[(]*|[)]*|[/]*|[!]*|[?]*|[¿]*|[“]*|[”]*|[+]*]+";

    // UN int maxTerminos, el numero de terminos que, como maximo, contienen  
    // los libros de listaLibros. Su valor por defecto es el que figura a 
    // continuacion, por lo que debe cambiarse si procede
    protected static int maxTerminos =  22310; // 105985 para "lista.txt"
    
    // UN Map index, que representa el Indice de una biblioteca digital.  
    // La clase de sus Claves es Termino y la de sus Valores ListaConPI<Posting>
    protected Map<Termino, ListaConPI<Posting>> index;
    
    // UN boolean verb, que activa/desactiva el modo "verbose" del buscador. 
    // Su valor por defecto es el que figura a continuacion, por lo que debe
    // cambiarse (a false) si procede
    protected static boolean verb = true; // false;
    
    public static void setVerb(boolean b) { verb = b; }
    
    /** Crea el Buscador de la BD que forman los libros de listaLibros.
     *  Basicamente, supone crear el Indice de la BD con, como maximo, maxTerminos. 
     *  Si no encuentra (el fichero .txt de) un libroLanza FileNotFoundException.
     */ 
    public BuscadorBD() throws FileNotFoundException { 
        boolean res = true; 
        Scanner fich = new Scanner(new File(dirListaLibros + listaLibros));
        if (verb) { 
            System.out.println("Creando el Indice de la biblioteca... " + listaLibros);
        }
        
        // Inicializar el Map index que tiene el Buscador 
    // usando como tipo dinamico la clase TablaHash.  
        // NOTA: el constructor de TablaHash requiere como argumento el  
        //       numero de Entradas que, como maximo, contendra la tabla
        index = new TablaHash<Termino, ListaConPI<Posting>>(maxTerminos);
        while (fich.hasNext()) {
            String nombreLibro = fich.next();
            String fichLibro = dirLibros + nombreLibro;
            res = indexarLibro(fichLibro);
        }
        if (verb) { 
            System.out.println("Terminos del Indice (talla del Map) = " + index.talla());
        }
        if (!res) throw new FileNotFoundException();
    }

    public BuscadorBD(String fichero) throws FileNotFoundException {
        boolean res = true;
        Scanner fich = new Scanner(new File("src/" + dirListaLibros + fichero));
        if (verb) {
            System.out.println("Creando el Indice de la biblioteca... " + "src/" + listaLibros);
        }

        // Inicializar el Map index que tiene el Buscador
        // usando como tipo dinamico la clase TablaHash.
        // NOTA: el constructor de TablaHash requiere como argumento el
        //       numero de Entradas que, como maximo, contendra la tabla
        index = new TablaHash<Termino, ListaConPI<Posting>>(maxTerminos);
        while (fich.hasNext()) {
            String nombreLibro = fich.next();
            String fichLibro = "src/" + dirLibros + nombreLibro;
            res = indexarLibro(fichLibro);
        }
        if (verb) {
            System.out.println("Terminos del Indice (talla del Map) = " + index.talla());
        }
        if (!res) throw new FileNotFoundException();
    }
    
    /** Devuelve true tras actualizar el Buscador de una BD 
     *  con los terminos que contiene fichLibro (fichero .txt). 
     *  Basicamente, supone actualizar el Indice de la biblioteca 
     *  con los terminos de fichlibro.
     *  El metodo devuelve false si fichlibro no existe.
     */ 
    public boolean indexarLibro(String fichLibro) {
        boolean res = true;     
        try {            
            Scanner libro = new Scanner(new File(fichLibro));            
            int posSep = fichLibro.lastIndexOf(File.separator);
            String titulo = fichLibro.substring(posSep + 1);
            if (verb) {
                System.out.println("Indexando libro... " 
                                  + titulo.substring(0, titulo.indexOf(".txt")));
            }
            int numLin = 0;
            while (libro.hasNext()) {
                String linea = libro.nextLine();
                numLin++;
                String[] palabras = linea.split(separadores);
                Posting nuevo = new Posting(titulo, numLin);
                for (int i = 0; i < palabras.length; i++) {
                    String palabra = palabras[i];
                    if (Termino.esValido(palabra)) {
                        // Actualizar index con el "Posting" nuevo asociado a clave.
                        Termino clave = new Termino(palabra.toLowerCase());
                        ListaConPI<Posting> valor = index.recuperar(clave);
                        if (valor == null) valor = new LEGListaConPI<Posting>(); 
                        valor.insertar(nuevo);
                        index.insertar(clave, valor);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: no se ha encontrado el libro " + fichLibro);
            res = false;        
        }
        return res;
    }
       
    /**
     * Devuelve en formato texto (String) el resultado de la busqueda del termino
     * asociado a una palabra, p, en el Indice de una BD (frecuencia de aparicion del 
     * termino y, en su caso, el listado que contiene los titulos y lineas de los
     * libros de la bibiblioteca en los que aparece, i.e. su Posting List).
     */
    public String buscar(String p) { 
        String res = "";
        Termino clave = new Termino(p.toLowerCase());
        ListaConPI<Posting> valor = index.recuperar(clave);
        if (valor == null) {
            res = "La palabra \"" + p + "\" no aparece en ningun libro de esta biblioteca";
        }
        else {
            res = "Encontradas " + valor.talla() + " apariciones de la palabra \"" + p 
                   + "\" en...\n" + valor.toString(); 
        }
        return res;
    }
    
    /** Comprueba si una palabra, p, es un termino del Indice de una BD, i.e. 
     *  si p esta formada por letras del alfabeto español en minusculas.
     */
    /*
    protected static boolean esTermino(String p) {
        if (p.length() == 0) return false; 
        for (int i = 0; i < p.length(); i++) {
            if (!Character.isLetter(p.charAt(i))) return false; 
        }
        return true;
    }*/
    
    /** Generalización de hapax */
    /** Devuelve una ListaConPI con aquellos términos del Índice de una BD que
     *  aparecen n veces en sus libros, o null si no existe ninguno.
     */
    public ListaConPI<Termino> terminosNveces(int n) {        
        ListaConPI<Termino> res = new LEGListaConPI<>();
        ListaConPI<Termino> c = index.claves();         
        ListaConPI<Posting> valor;
        for (c.inicio(); !c.esFin(); c.siguiente()) {
            Termino clave = c.recuperar();
            valor = index.recuperar(clave);
            if (valor.talla() == n) res.insertar(clave);
        }
        if (res.talla() == 0) return null; 
        else return res; 
    }
    
    /** Devuelve una ListaConPI con aquellos terminos del Indice de una BD que 
     *  solo aparecen solo una vez en sus libros, i.e. los llamados "hapax 
     *  legomena" de la BD, o null si no existe ninguno.
     *  PISTA: Implementar mediante invocacion al metodo terminosNveces
     */
    public ListaConPI<Termino> hapax() {
        return terminosNveces(1);
    }
    
       
    public Map<String,Integer> mapTitulos(ListaConPI<Termino> l) {
        Map<String,Integer> m = new TablaHash<>(20);
        
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            Termino c = l.recuperar();
            ListaConPI<Posting> v = index.recuperar(c);
            for (v.inicio(); !v.esFin(); v.siguiente()) {
                String s = v.recuperar().tituloLibro;
                Integer i = m.recuperar(s);
                if (i == null) m.insertar(s, 1);
                else m.insertar(s, i+1);
            }
        }
        
        return m;
    }
    
    public Map<Integer,ListaConPI<Termino>> mapContador() {
        Map<Integer,ListaConPI<Termino>> m = new TablaHash<>(10000);  
        
        ListaConPI<Termino> c = index.claves();
        for (c.inicio(); !c.esFin(); c.siguiente()) {
            Termino clave = c.recuperar();
            int n = index.recuperar(clave).talla();
            ListaConPI<Termino> l = m.recuperar(n);
            if (l == null) l = new LEGListaConPI<Termino>();
            l.insertar(clave);
            m.insertar(n,l);
        }  
        
        return m;        
    }
    /*
    public static void main14() throws FileNotFoundException {
        BuscadorBD bib = new BuscadorBD();
        ListaConPI<Termino> l2 = new LEGListaConPI<Termino>();
        l2.insertar(new Termino("instituciones"));
        l2.insertar(new Termino("libertad"));
        l2.insertar(new Termino("futuro"));
        l2.insertar(new Termino("editores"));
        Map<String,Integer> m2 = bib.mapTitulos(l2);
        System.out.println(m2);
    }
    
    public static void main24() throws FileNotFoundException {
        BuscadorBD bib = new BuscadorBD();
        Map<Integer,ListaConPI<Termino>> m = bib.mapContador();
        System.out.println(m.recuperar(63));
    }
    
    public static void main4() throws FileNotFoundException {
        BuscadorBD bib = new BuscadorBD();
        Map<Integer,ListaConPI<Termino>> m = bib.mapContador();
        //System.out.println(m);
        System.out.println("******************");
        System.out.println(m.recuperar(63));
        System.out.println("******************");
        System.out.println(m.recuperar(88));
        System.out.println("******************");
        System.out.println(m.recuperar(125));
        System.out.println("******************");
        System.out.println(m.recuperar(23372));
        System.out.println("******************");
        ListaConPI<Termino> l = bib.metodo3();
        System.out.println(l);
        System.out.println("******************");
        ListaConPI<Termino> l2 = new LEGListaConPI<Termino>();
        l2.insertar(new Termino("instituciones"));
        l2.insertar(new Termino("libertad"));
        l2.insertar(new Termino("futuro"));
        l2.insertar(new Termino("editores"));
        //l2.insertar(new Termino("autor"));
        Map<String,Integer> m2 = bib.mapTitulos(l2);
        System.out.println(m2);
    }
    */
    public ListaConPI<Termino> metodo3() {
        Map<Integer,ListaConPI<Termino>> m = new TablaHash<>(1000);  
        
        ListaConPI<Termino> c = index.claves();
        for (c.inicio(); !c.esFin(); c.siguiente()) {
            Termino clave = c.recuperar();
            int n = index.recuperar(clave).talla();
            ListaConPI<Termino> l = m.recuperar(n);
            if (l == null) l = new LEGListaConPI<Termino>();
            l.insertar(clave);
            m.insertar(n,l);
        }  
        
        ListaConPI<Integer> l = m.claves();
        Termino t = null;
        int max = 0;
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            Integer clave = l.recuperar();
            if (clave > max) {
                max = clave;
            }
        }    
        System.out.println("max = "+max);
        return m.recuperar(max);        
    }
    
    
    public Termino masFrecuente0(ListaConPI<Termino> c) {
        Termino t = null;
        int max = 0;
        for (c.inicio(); !c.esFin(); c.siguiente()) {
            Termino clave = c.recuperar();
            ListaConPI<Posting> valor = index.recuperar(clave);
            //ListaConPI<BuscadorDeLaBibl.Posting> valor = index.recuperar(clave);
            //if (valor != null) System.out.println(clave + " / " + valor.talla());
            if (valor.talla() > max) {
            //if (valor != null && valor.talla() > max) {    
                max = valor.talla();
                t = clave;
            }
        }
        return t;
    }
    
    public Map<Termino, ListaConPI<Posting>> buildSubindex(ListaConPI<Termino> c) {
        Map<Termino, ListaConPI<Posting>> subindex = 
            new TablaHash<Termino, ListaConPI<Posting>>(c.talla());
        for (c.inicio(); !c.esFin(); c.siguiente()) {
            Termino clave = c.recuperar();
            ListaConPI<Posting> valor = index.recuperar(clave);
            subindex.insertar(clave, valor);
        }    
        return subindex;
    }
    /*
    public static void main(String[] args) throws FileNotFoundException {
        
        BuscadorBD bib = new BuscadorBD();
        String[] a = {"ficheros", "tabla", "programa", "estrellas", "ladrillos"};
        ListaConPI<Termino> lt = new LEGListaConPI<Termino>();
        for (String s : a) lt.insertar(new Termino(s));
        System.out.print(bib.masFrecuente(lt));
        System.out.print(bib.masFrecuente(bib.index.claves()));  
    */    
       /*     
       BuscadorDeLaBibl bib = new BuscadorDeLaBibl();
    String titulo = "10-anos-explorando-el-Universo";
    int numLinea = 19;
    ListaConPI<Termino> l = bib.contenidoLinea(titulo, numLinea);
    System.out.println("Contenidos de la línea "+numLinea+" del libro "+titulo);
    if (l.talla() == 0) System.out.println("\tlinea vacía...");
    else{
        System.out.println("[");
        for (l.inicio(); !l.esFin(); l.siguiente()){
            System.out.print("\t"+l.recuperar().toString());
        }
        System.out.println("]");
    }
    */
   
   /*
   BuscadorDeLaBibl bib = new BuscadorDeLaBibl();    
    String[] a = {"ficheros", "tabla", "programa", "estrellas", "ladrillos"};
    ListaConPI<Termino> lt = new LEGListaConPI<Termino>();
    for (String s : a) lt.insertar(new Termino(s));
    Map<Termino,Integer> m = bib.tablaFrecuencias(lt);  
    */
   // }
    
    /**
 * Dado el título de un libro y un número de línea, devolver la una
 * ListaConPI que contenga todos los terminos que aparecen la línea
 * indicada del libro indicado
 * 
 * Se puede asumir la String titulo siempre corresponde con un libro existente
 * i el int numLinea con una linea valida de dicho libro
 */
public ListaConPI<Termino> contenidoLinea(String titulo, int numLinea) {
    ListaConPI<Termino> res = new LEGListaConPI<Termino>();
    ListaConPI<Termino> c = index.claves();         
    for (c.inicio(); !c.esFin(); c.siguiente()) {
        Termino clave = c.recuperar();
        ListaConPI<Posting> postings = index.recuperar(clave);
        for (postings.inicio(); !postings.esFin(); postings.siguiente()) {
            Posting aux = postings.recuperar();
            if (aux.lineaLibro == numLinea && aux.tituloLibro.equals(titulo)) {
                res.insertar(clave); 
            }             
        }
    }
    return res;
}

// metodo main a incluir en la clase BuscadorDeLaBibl para el test del metodo contenidoLinea
/*
public static void main1() throws FileNotFoundException {
    BuscadorBD bib = new BuscadorBD();
    String titulo = "10-anos-explorando-el-Universo";
    int numLinea = 19;
    ListaConPI<Termino> l = bib.contenidoLinea(titulo, numLinea);
    System.out.println("Contenidos de la línea "+numLinea+" del libro "+titulo);
    if (l.talla() == 0) System.out.println("\tlinea vacía...");
    else{
        System.out.println("[");
        for (l.inicio(); !l.esFin(); l.siguiente()){
            System.out.print("\t"+l.recuperar().toString());
        }
        System.out.println("]");
    }
}
*/
/**
 * Dada una ListaConPI de Termino, devuelve un Map<Termino,Integer> con la frecuencia
 * de aparición de cada término de la lista. Si uno de los terminos de la lista no 
 * aparece en el índice, su frecuencia sera, evidentemente, 0
 * El metodo debe además, antes de devolver el Map, imprimir las frecuencias en el formato
 * siguiente:
 * (palabra1, frecuencia1)
 */
public Map<Termino,Integer> tablaFrecuencias(ListaConPI<Termino> lt) {
    Map<Termino,Integer> res = new TablaHash<Termino,Integer>(lt.talla());
    for(lt.inicio(); !lt.esFin(); lt.siguiente()){
        int frec;
        Termino term = lt.recuperar();
        ListaConPI<Posting> postings = index.recuperar(term);
        if (postings != null) { frec = postings.talla(); }
        else { frec = 0; }
        res.insertar(term, frec);
        System.out.println("("+term.palabra+", "+frec+")");
    }
    return res;
}

    // metodo main a incluir en la clase BuscadorDeLaBibl para el test del metodo tablaFrecuencias
    /*
public static void main2() throws FileNotFoundException {
    BuscadorBD bib = new BuscadorBD();    
    String[] a = {"ficheros", "tabla", "programa", "estrellas", "ladrillos", "cabeza", "vino", "tocadiscos"};
    ListaConPI<Termino> lt = new LEGListaConPI<Termino>();
    for (String s : a) lt.insertar(new Termino(s));
    Map<Termino,Integer> m = bib.tablaFrecuencias(lt);    
}
public static void main3() throws FileNotFoundException  {
    BuscadorBD bib = new BuscadorBD();
    String s = bib.contenidoLibro("10-anos-explorando-el-Universo");
    System.out.println(s);
}
*/

public String contenidoLibro(String titulo) {
    ListaConPI<Termino> res = new LEGListaConPI<Termino>();
    ListaConPI<Termino> c = index.claves();         
    for (c.inicio(); !c.esFin(); c.siguiente()) {
        Termino clave = c.recuperar();
        ListaConPI<Posting> p = index.recuperar(clave);
        for (p.inicio(); !p.esFin(); p.siguiente()) {
            Posting aux = p.recuperar();
            if (aux.tituloLibro.equals(titulo)) {
                res.insertar(clave); break;
            }             
        }
    }
    System.out.println(res.talla());
    return res.toString();
}

    public Termino masFrecuente1(ListaConPI<Termino> c) {
        Termino t = null;
        int max = 0;
        for (c.inicio(); !c.esFin(); c.siguiente()) {
            Termino clave = c.recuperar();
            ListaConPI<Posting> valor = index.recuperar(clave);
            //ListaConPI<BuscadorDeLaBibl.Posting> valor = index.recuperar(clave);
            //if (valor != null) System.out.println(clave + " / " + valor.talla());
            if (valor.talla() > max) {
            //if (valor != null && valor.talla() > max) {    
                max = valor.talla();
                t = clave;
            }
        }
        return t;
    }
    
public Termino masFrecuente2(ListaConPI<Termino> lt){
        int maxFreq = -1;
        Termino res = null;
        ListaConPI<Posting> postings;        
        for(lt.inicio(); !lt.esFin(); lt.siguiente()){
            Termino term = lt.recuperar();
            postings = this.index.recuperar(term);
            if (postings != null){
                int termFreq = postings.talla();
                if (termFreq > maxFreq) {
                   maxFreq = termFreq;
                   res = term;
                }
            } else {
                System.out.println("Atención: El Termino " + term.palabra + " no está en el índice. ");
            }
        }
        System.out.println("El Temino más frecuente es "+ res.palabra +", que aparece "+ maxFreq+" veces.");
        return res;
    }
    
 
    

    
    
    /** pregunta examen 30-3-2023 */
    public Termino masFrecuente(ListaConPI<Termino> lt) {
        int maxFreq = -1;
        Termino res = null;
        ListaConPI<Posting> postings;        
        for (lt.inicio(); !lt.esFin(); lt.siguiente()) {
            Termino term = lt.recuperar();
            postings = this.index.recuperar(term);
            if (postings != null) {
                int termFreq = postings.talla();
                if (termFreq > maxFreq) {
                    maxFreq = termFreq;
                    res = term;
                }
            } else {
                System.out.println("Atención: El Termino " + term.palabra + " no está en el índice. ");
            }
        }
        System.out.println("El Termino más frecuente es " + res.palabra + ", que aparece " + maxFreq + " veces.");
        return res;
    }
    /*
    public static void testMasFrecuente() throws FileNotFoundException{
        BuscadorBD bib = new BuscadorBD();
        String[] a = {"ficheros", "tabla", "tocadiscos", "programa", "estrellas", "ladrillos"};
        ListaConPI<Termino> lt = new LEGListaConPI<Termino>();
        for (String s : a) lt.insertar(new Termino(s));
        bib.masFrecuente2(lt);    
   }
    */
    /** pregunta examen 30-3-2023 */
    public ListaConPI<Termino> comienzanPor(String prefijo, String libro) {
        if (!Termino.esValido(prefijo)) return null;
        ListaConPI<Termino> res = new LEGListaConPI<Termino>();
        ListaConPI<Termino> c = index.claves();
        ListaConPI<Posting> p;
        //No es necesario comprobar if (c != null)
        for (c.inicio(); !c.esFin(); c.siguiente()) {
            Termino clave = c.recuperar();
            if (clave.palabra.startsWith(prefijo)) {
                p = index.recuperar(clave);
                //No es necesario comprobar if (p != null)
                for (p.inicio(); !p.esFin(); p.siguiente()) {
                    Posting posting = p.recuperar();
                    if (posting.tituloLibro.equals(libro)) {
                        res.insertar(clave);
                        break;
                    }
                }
            }
        }
        return res;
    }
    
    /** pregunta examen 30-3-2023 */
    public ListaConPI<Termino> enTitulo() {
        ListaConPI<Termino> res = new LEGListaConPI<Termino>();
        ListaConPI<Termino> c = index.claves();
        ListaConPI<Posting> valor;
        for (c.inicio(); !c.esFin(); c.siguiente()) {
            Termino clave = c.recuperar();
            valor = index.recuperar(clave);            
            for (valor.inicio(); !valor.esFin(); valor.siguiente()) {
                Posting p = valor.recuperar();
                if (valor.recuperar().lineaLibro == 1) {
                    res.insertar(clave);
                    break;
                }
            }
        }
        return res;
    }
    /*
    public static void testEnTitulo() throws FileNotFoundException {
        BuscadorBD bb = new BuscadorBD();
        ListaConPI<Termino> l = bb.enTitulo();
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            System.out.print(l.recuperar());
        }
    }*/
    public static void main(String[] args) throws FileNotFoundException {
        BuscadorBD buscador10 = new BuscadorBD("lista10.txt");
        System.out.println(buscador10.buscar("Algoritmo"));
    }
}    