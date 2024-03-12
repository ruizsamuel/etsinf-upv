package pract2;

/** 
 * Clase Circle: define un círculo de un determinado radio, color y
 * posición de su centro, con la funcionalidad que aparece a continuación.
 * @author IIP 
 * @version Práctica 2 - Curso 2021/22
 */ 
public class Circle {
    private double radius; 
    private String color;
    private int centerX, centerY;   
    
    /** Crea un Circle de radio 50, negro y centro en (100,100). */
    public Circle() {
        radius = 50;
        color = "black"; 
        centerX = 100;  
        centerY = 100; 
    }
    
    /** Crea un Circle de radio r, color c y centro en (cx,cy). 
     *  @param r double que representa el radio. 
     *  @param col String que representa el color.
     *  @param cx int que representa la abscisa del centro.
     *  @param cy int que representa la ordenada del centro.
     */
    public Circle(double r, String col, int cx, int cy) {
        radius = r;  
        color = col; 
        centerX = cx; 
        centerY = cy;         
    }

    /** Devuelve el radio del Circle. 
     *  @return double, el radio. 
     */
    public double getRadius() { return radius; }    

    /** Devuelve el color del Circle.
     *  @return String, el color.  
     */
    public String getColor() { return color; }  

    /** Devuelve la abscisa del centro del Circle. 
     *  @return int, la abscisa.   
     */
    public int getCenterX() { return centerX; }  

    /** Devuelve la ordenada del centro del Circle. 
     *  @return int, la ordenada.   
     */
    public int getCenterY() { return centerY; }
 
    /** Actualiza el radio del Circle a nuevoRadio. 
     *  @param nuevoRadio double que representa el nuevo radio.
     */
    public void setRadius(double nuevoRadio) { radius = nuevoRadio; }   

    /** Actualiza el color del Circle a nuevoColor. 
     *  @param nuevoColor String que representa el nuevo color.
     */
    public void setColor(String nuevoColor) { color = nuevoColor; }   
 
    /** Actualiza el centro del Circle a la posición (cx,cy). 
     *  @param cx int que representa la nueva abscisa del centro.
     *  @param cx int que representa la nueva ordenada del centro.
     */
    public void setCenter(int cx, int cy) { centerX = cx; centerY = cy; }
    
    /** Devuelve el área del Circle.
     *  @return double, el área.  
     */
    public double area() { return Math.PI * radius * radius; }
    
    /** Devuelve el perímetro del Circle.
     *  @return double, el perímetro.  
     */
    public double perimeter() { return 2 * Math.PI * radius; }
    
    /** Devuelve un String con los datos del Circle. 
     *  @return String, los datos. 
     */
    public String toString() { 
        String res = "Círculo de radio " + radius;
        res += ", color " + color;
        res += " y centro (" + centerX + "," + centerY + ")";
        return res; 
    }

} // de Circle
