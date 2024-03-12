package exam1;

/**  
 *  Clase Point: clase "tipo de datos" que define un punto en el plano 
 *  cartesiano, determinado por sus coordenadas x e y. 
 *  
 *  @author IIP - Parcial 1 Laboratorio
 *  @version Curso 2021/22
 */
public class Point {
    // Un Point TIENE...    
    
    // (a) Como atributos de instancia privados, las siguientes variables:
    private double x;
    private double y;
   
    // (b) Como metodos publicos, los siguientes:
    /** Crea un Point con abscisa px y ordenada py. 
     *  @param px double, la abscisa.
     *  @param py double, la ordenada.
     */
    public Point(double px, double py) { this.x = px; this.y = py; }
    
    /** Crea un Point con abscisa 0.0 y ordenada 0.0. */
    public Point() { this(0, 0); }
  
    /** Devuelve la abscisa del Point this. 
     *  @return double, la abscisa.      
     */
    public double getX() { return this.x; }
    
    /** Devuelve la ordenada del Point this.
     *  @return double, la ordenada.
     */
    public double getY() { return this.y; }

    /** Actualiza la abscisa del Point this a px.
     *  @param px double, la nueva abscisa.
     */
    public void setX(double px) { this.x = px; }
    
    /** Actualiza la ordenada del Point this a py. 
     *  @param py double, la nueva ordenada.
     */
    public void setY(double py) { this.y = py; }   
    
    /** Actualiza la abscisa del Point this a px y la ordenada a py.
     *  @param px double, la nueva abscisa.
     *  @param py double, la nueva ordenada.
     */
    public void move(double px, double py) { 
        this.x = px; this.y = py; 
    }  
    
    /** Devuelve la distancia entre el Point this y otro Point dado.
     *  @param p Point, el otro Point.
     *  @return double, la distancia.
     */
    public double distance(Point p) { 
        double abs = p.x - this.x; double ord = p.y - this.y; 
        return Math.sqrt(abs * abs + ord * ord); 
    }
    
    /** Devuelve la distancia entre el Point this y el origen
     *  de coordenadas.     
     *  @return double, la distancia.
     */
    public double distance() {        
        return this.distance(new Point()); 
    }
    
    /**
     * Comprueba si el Point this es igual a otro Point dado.
     * @param o Object, objeto a comparar con el Point this.
     * @return boolean, true si o es un Point y las coordenadas
     * x e y del Point this coinciden con las del Point dado y 
     * false en caso contrario.
     */
    public boolean equals(Object o) {
        return o instanceof Point 
            && this.x == ((Point) o).x 
            && this.y == ((Point) o).y;        
    }

    /** Devuelve un String que representa el Point this en el  
     *  formato tipico matematico, i.e., (abscisa,ordenada) 
     *  @return String, el resultado.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";      
    }   
   
    /** Dado un double m y un Point p, que junto con el Point this  
     *  forma parte de una recta de pendiente m, devuelve this, p o 
     *  el punto (0.0,0.0) si el termino independiente de la recta 
     *  es negativo, positivo o cero, respectivamente.
     *  Precondición: las abscisas de this y p son distintas y,
     *  por tanto, la pendiente m no es infinita (esto es, 
     *  la recta no es paralela al eje Y).
     *  @param m double, la pendiente.
     *  @param p Point, el otro Point.
     *  @return Point, el resultado.
     */ 
    public Point yIntercept(double m, Point p) {
        Point res = null;
        Point origin = new Point (0,0);
        double n = p.getY() - (m*p.getX());
        
        if (n < 0) {
            res = this;
        } else if (n > 0) {
            res = p;
        } else {
            res = origin;
        }
        return res;
    }
}
