package pract5;

/**
 * Clase PolygonGroupExam. Grupo de poligonos en el plano.
 * Los poligonos estan en orden segun la secuencia en que se anyaden
 * al grupo, de manera que se considera que cada poligono esta mas 
 * arriba en el grupo que los poligonos anteriores, o dicho de otro 
 * modo, se superpone a los anteriores. 
 * Se supone que el orden del grupo da la secuencia en que se dibujan
 * los poligonos, de manera que cada uno se dibuja por encima de los
 * anteriores, superponiendose a aquellos con los que solape.
 *  
 * La manera de seleccionar un poligono es dando un punto visible 
 * del poligono, es decir, dando un punto que no pertenezca
 * a los poligonos que aparecen superpuestos en el dibujo.
 *
 * @author PRG
 * @version Curso 2021-22
 */
public class PolygonGroupExam {
    private NodePol front, back;
    private int size;

    /** Crea un grupo de 0 poligonos. */
    public PolygonGroupExam() {
        front = null;
        back = null;
        size = 0;
    }

    /** Anyade al grupo, arriba del todo, un poligono dado. 
     *  @param pol Polygon, el poligono.
     */
    public void add(Polygon pol) {  
        front = new NodePol(pol, front);
        if (back == null) { back = front; }
        size++;
    }

    /** Devuelve el numero de poligonos del grupo,  
     *  esto es, la talla del grupo.
     *  return int, la talla.
     */
    public int getSize() { return size; }
    
    /** Devuelve la referencia al nodo con el poligono  
     *  del frente del grupo.
     *  return NodePol, nodo con el poligono del frente.
     */
    public NodePol getFront() { return front; }
    
    /** Devuelve la referencia al nodo con el poligono 
     *  del fondo del grupo.
     *  return NodePol, nodo con el poligono del fondo.
     */
    public NodePol getBack() { return back; }

    /** Devuelve un array con la secuencia de poligonos del grupo, 
     *  por orden desde el de mas abajo al de mas arriba.
     *  @return Polygon[], el array.
     */
    public Polygon[] toArray() {
        Polygon[] result = new Polygon[size];
        NodePol aux = front;
        for (int i = size - 1; i >= 0; i--) {           
            result[i] = aux.data;
            aux = aux.next;
        }
        return result;
    } 
    
    /** Busca en el grupo el poligono que ocupa la posicion pos. 
     *  Devuelve un array de NodePol tal que:
     *  - la componente 1 es el nodo con el poligono que ocupa la posicion pos 
     *    (null si dicho nodo no existe)  
     *  - la componente 0 es el nodo anterior, esto es, el nodo con el poligono
     *    que ocupa la posicion pos - 1 (null si no esta definido).
     *  @param pos int, la posicion.   
     *  @return NodePol, el resultado.
     */
    private NodePol[] searchPos(int pos) {
        NodePol aux = front, prevAux = null;
        int k = 0;
        while (aux != null && k < pos) {
            prevAux = aux;
            aux = aux.next;
            k++;
        }
        NodePol[] s = new NodePol[2];
        if (pos >= 0 && pos <= size) { s[0] = prevAux; s[1] = aux; }
        else { s[0] = null; s[1] = null; }
        return s;
    }

    /** Inserta el poligono p en el grupo en la posicion pos. 
     *  Se supone que los poligonos ocupan las posiciones 0 a size - 1 numerados  
     *  desde el del frente hasta el del fondo. 
     *  Solo son validas como posiciones de insercion los valores de 0 a size.
     *  Si pos = size, inserta en el fondo.
     *  El metodo no hace nada si pos es una posicion no valida.
     *  
     *  @param p Polygon, el poligono a insertar.
     *  @param pos int, la posicion de insercion.
     */   
    public void insertPos(Polygon p, int pos) {
        if (0 <= pos && pos <= size) {
            NodePol[] search = searchPos(pos);
            
            if (search[0] == null) {
                front = new NodePol(p, front);
            } else {
                search[0].next = new NodePol(p, search[1]);
            }
            if (pos == size) {back = back.next;}
            size++;
        }
        
    }

}
