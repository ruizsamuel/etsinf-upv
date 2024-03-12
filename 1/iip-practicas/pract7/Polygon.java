package pract7;

import java.awt.Color;
import pract5.Point;
/**
 * Clase Polygon. Poligonos solidos (de un determinado color) en el plano,
 * dados por la secuencia de sus vertices.
 * 
 * @author IIP - Practica 7
 * @version Curso 2021/22
 */
public class Polygon {    
    private Point[] v;
    private Color color;

    /**
     * Construye un poligono a partir de un array x con las
     * abcsisas x0, x1, x2, ..., xn-1 de sus vertices, y un array y
     * con las ordenadas y0, y1, y2, ... yn-1 de sus vertices, n > 0.
     * Los vertices definen un poligono cuyos lados se extienden de 
     * un vertice al siguiente, y cerrandose en (x0,y0).
     * Por defecto, el poligono es de color azul (Color.BLUE).
     * @param x double[], las abscisas.
     * @param y double[], las ordenadas.
     */
    public Polygon(double[] x, double[] y) {
        Point[] v = new Point[x.length];
        
        for (int i = 0; i < x.length; i++) {
            v[i] = new Point(x[i], y[i]);
        }
        
        this.v = v;
        this.color = Color.BLUE;
    }

    /** Devuelve el color del poligono.
     *  @return Color, el color. 
     */
    public Color getColor() { return this.color;}
    
    /** Actualiza el color del poligono a un color dado.
     *  @param nC Color, el nuevo color.
     */
    public void setColor(Color nC) { this.color = nC; }
    
    /** Devuelve las abcisas de los vertices del poligono. 
     *  @return double[], las abscisas de los vertices.
     */
    public double[] verticesX() {
        double[] vx = new double[v.length];
        
        for (int i = 0; i < this.v.length; i++) {
            vx[i] = this.v[i].getX();
        }
        return vx;
    }
    
    /** Devuelve las ordenadas de los vertices del poligono.
     *  @return double[], las ordenadas de los vertices. 
     */
    public double[] verticesY() {
        double[] vy = new double[v.length];
        
        for (int i = 0; i < this.v.length; i++) {
            vy[i] = this.v[i].getY();
        }
        return vy;
    }
    
    /** Traslada los vertices del poligono: 
     *  incX en el eje X e incY en el eje Y.
     *  @param incX double, el incremento o decremento en X.
     *  @param incY double, el incremento o decremento en Y.
     */
    public void translate(double incX, double incY) {
        for (int i = 0; i < this.v.length; i++) {
            this.v[i].setX(this.v[i].getX() + incX);
            this.v[i].setY(this.v[i].getY() + incY);
        }  
    }
            
    /** Devuelve el perimetro del poligono.
     *  @return double, el perimetro.
     */
    public double perimeter() {
        double p = 0;
        p += this.v[0].distance(this.v[this.v.length - 1]);
        for (int i = 0; i < this.v.length - 1; i++) {
            p += this.v[i].distance(this.v[i + 1]);
        }
        return p;
    }   
          
    /** Comprueba si el Point p es interior al poligono.
     *  PRECONDICION: p no esta sobre los lados del poligono.
     *  Si el punto es interior al poligono devuelve true; 
     *  en caso contrario, devuelve false.
     *  @param p Point, el punto.
     *  @return boolean, true si el punto es interior o false
     *  si es exterior.
     */
    public boolean inside(Point p) {
        int nCuts = 0, n = v.length;
        int cross;
        for (int i = 0; i < n - 1; i++) {
            cross = p.cross(v[i], v[i + 1]);
            if (cross == Point.CROSS || cross == Point.LOW_CROSS) { nCuts++; }
        }
        cross = p.cross(v[n - 1], v[0]);
        if (cross == Point.CROSS || cross == Point.LOW_CROSS) { nCuts++; }
        if (nCuts % 2 == 0) {
            return false;
        }
        else {
            return true;
        }
    }
}