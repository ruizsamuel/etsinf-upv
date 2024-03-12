package exam2;

import graph2D.Graph2D;
import java.awt.Color;
import java.util.Scanner;
import others2.Point;
import others2.Polygon;
/**
 * Clase programa para facilitar la prueba del codigo, desarrollado por
 * el alumno, para el metodo translateAll.
 * El main crea una secuencia con 3 cuadrados en escalera superpuestos:
 * verde en el origen, rojo desplazado a la derecha y arriba y 
 * azul desplazado tambien a la derecha y arriba.
 * Se realizan 6 pruebas partiendo siempre de la secuencia original
 * y llamando al metodo translateAll(Point, double, double) para ver que:
 * 1.- La figura no se modifica.
 * 2.- El cuadrado verde es el unico que se traslada.
 * 3.- El cuadrado rojo es el unico que se traslada.
 * 4.- El cuadrado azul es el unico que se traslada.
 * 5.- Se trasladan los cuadrados verde y rojo.
 * 6.- Se trasladan los cuadrados rojo y azul.
 * 
 * La prueba 7 parte de la secuencia con 3 cuadrados y todos con centro 
 * en el origen de coordenadas: azul, rojo y verde.
 * Se llama al metodo translateAll(Point, double, double) para ver que 
 * se trasladan todos.
 * 
 * Esta clase es para uso particular del alumno. 
 * No se entrega.
 *
 * @author Parcial 2 - Ejercicio 2 
 * @version IIP - Curso 2021/22
 */
public class TestTranslateAll {
    private TestTranslateAll() { } // No se usan objetos de esta clase
    
    public static void main(String[] args)  {
        Scanner keyB = new Scanner(System.in);
        // Crea un grupo de 3 cuadrados que se superponen formando escalera                    
        Graph2D gd = new Graph2D(-20, 20, -20, 20, 400, 400, Color.WHITE, 
            "Prueba translateAll(Point, double, double)");
        PolygonGroup g = makeGroup(0);      
        drawGroup(gd, g);
        gd.drawPoint(-4, 4, Color.BLACK, 4);
        System.out.println("Veras 3 cuadrados superpuestos formando escalera y un punto exterior a todos ellos.");
        System.out.println("Secuencia: Verde -> Rojo -> Azul"); 
        System.out.println("** Prueba 1: llamada a translateAll(new Point(-4, 4), -3, -3)");
        System.out.println("para trasladar todos los poligonos que contienen al punto (-4.0,4.0).");        
        System.out.println("Pulsa INTRO para continuar..."); 
        String s = keyB.nextLine();
        g.translateAll(new Point(-4, 4), -3, -3);
        drawGroup(gd, g); 
        System.out.println("...Comprueba que la figura queda igual, sin cambios.");        
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        g = makeGroup(0);  
        drawGroup(gd, g);
        gd.drawPoint(-2, 2, Color.BLACK, 4);
        System.out.println("Volvemos a la figura original y un punto interior al cuadrado verde.");
        System.out.println("Secuencia: Verde -> Rojo -> Azul"); 
        System.out.println("** Prueba 2: llamada a translateAll(new Point(-2, 2), -3, -3)");
        System.out.println("para trasladar todos los poligonos que contienen al punto (-2.0,2.0).");      
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        g.translateAll(new Point(-2, 2), -3, -3);
        drawGroup(gd, g);   
        System.out.println("...Comprueba que el unico que se ha trasladado es el verde.");
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        g = makeGroup(0);  
        drawGroup(gd, g);
        gd.drawPoint(4, 4, Color.BLACK, 4);
        System.out.println("Volvemos a la figura original y un punto interior al cuadrado rojo.");
        System.out.println("Secuencia: Verde -> Rojo -> Azul"); 
        System.out.println("** Prueba 3: llamada a translateAll(new Point(4, 4), -2, -2)");
        System.out.println("para trasladar todos los poligonos que contienen al punto (4.0,4.0)."); 
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        g.translateAll(new Point(4, 4), -2, -2);
        drawGroup(gd, g);   
        System.out.println("...Comprueba que el unico que se ha trasladado el rojo.");
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        g = makeGroup(0);  
        drawGroup(gd, g);
        gd.drawPoint(12.5, 12.5, Color.BLACK, 4);
        System.out.println("Volvemos a la figura original y un punto interior al cuadrado azul.");
        System.out.println("Secuencia: Verde -> Rojo -> Azul");
        System.out.println("** Prueba 4: llamada a translateAll(new Point(12.5, 12.5), -3, -3)");
        System.out.println("para trasladar todos los poligonos que contienen al punto (12.5,12.5)."); 
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        g.translateAll(new Point(12.5, 12.5), -3, -3);
        drawGroup(gd, g);    
        System.out.println("...Comprueba que el unico que se ha trasladado es el azul.");
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        g = makeGroup(0);  
        drawGroup(gd, g);
        gd.drawPoint(2, 2, Color.BLACK, 4);
        System.out.println("Volvemos a la figura original y un punto interior a los cuadrados verde y rojo.");
        System.out.println("Secuencia: Verde -> Rojo -> Azul");    
        System.out.println("** Prueba 5: llamada a translateAll(new Point(2, 2), -3, -3)");
        System.out.println("para trasladar todos los poligonos que contienen al punto (2.0,2.0).");        
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        g.translateAll(new Point(2, 2), -3, -3);
        drawGroup(gd, g); 
        System.out.println("...Comprueba que se han trasladado el verde y el rojo.");        
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        g = makeGroup(0);  
        drawGroup(gd, g);
        gd.drawPoint(6, 6, Color.BLACK, 4);
        System.out.println("Volvemos a la figura original y un punto interior a los cuadrados rojo y azul.");
        System.out.println("Secuencia: Verde -> Rojo -> Azul");    
        System.out.println("** Prueba 6: llamada a translateAll(new Point(6, 6), -2, -2)");
        System.out.println("para trasladar todos los poligonos que contienen al punto (6.0,6.0).");        
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        g.translateAll(new Point(6, 6), -2, -2);
        drawGroup(gd, g); 
        System.out.println("...Comprueba que se han trasladado el rojo y el azul.");        
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        g = makeGroup(7);      
        drawGroup(gd, g);
        gd.drawPoint(0, 0, Color.BLACK, 4);
        System.out.println("Ahora veras 3 cuadrados con el mismo centro en el punto (0.0,0.0).");
        System.out.println("Secuencia: Azul -> Rojo -> Verde");        
        System.out.println("** Prueba 7: llamada a translateAll(new Point(0, 0), -3, -3)");
        System.out.println("para trasladar todos los poligonos que contienen al punto (0.0,0.0).");        
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        g.translateAll(new Point(0, 0), -3, -3);
        drawGroup(gd, g); 
        System.out.println("...Comprueba que se han trasladado todos.");        
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();        
        System.out.println("Fin del proceso!!!"); 
        System.out.println("Envia ahora tu solucion a traves de la aplicacion de examen!!!");
    }
    
    /** 
     * Muestra en la salida grafica un grupo de poligonos dado.
     * @param gd Graph2D, el grafo.
     * @param p PolygonGroup, el grupo de poligonos.
     */
    private static void drawGroup(Graph2D gd, PolygonGroup g) {
        gd.clear(); 
        Polygon[] aPol = g.toArray();
        for (int i = 0; i < aPol.length; i++) {
            gd.fillPolygon(aPol[i].verticesX(), aPol[i].verticesY(),
                aPol[i].getColor(), 2);   
        }   
    }    
        
    /** 
     * Crea un grupo con 3 cuadrados en escalera, segun la prueba a realizar.
     * @param prueba int, el numero de prueba.
     * @return p PolygonGroup, el grupo de poligonos.
     */
    private static PolygonGroup makeGroup(int prueba) {
        Polygon[] group = new Polygon[3];
        // cuadrado verde de tamaño 6x6 y centrado en (0.0, 0.0)
        double[] x0 = {-3.0, -3.0, 3.0, 3.0};
        double[] y0 = {-3.0, 3.0, 3.0, -3.0};
        group[0] = new Polygon(x0, y0);
        group[0].setColor(Color.GREEN);       
        // cuadrado rojo de tamaño 8x8 y centrado en (4.0, 4.0)
        double[] x1 = {0.0, 0.0, 8.0, 8.0};
        double[] y1 = {0.0, 8.0, 8.0, 0.0};
        group[1] = new Polygon(x1, y1);        
        group[1].setColor(Color.RED);        
        // cuadrado azul de tamaño 10x10 y centrado en (10.0, 10.0)
        double[] x2 = {5.0, 5.0, 15.0, 15.0};
        double[] y2 = {5.0, 15.0, 15.0, 5.0};
        group[2] = new Polygon(x2, y2);
        group[2].setColor(Color.BLUE);                        
        
        if (prueba == 7) {
            group[0] = new Polygon(x2, y2); 
            group[0].translate(-10, -10);
            group[1].translate(-4, -4);
            group[2] = new Polygon(x0, y0); 
            group[2].setColor(Color.GREEN);
        }
        
        PolygonGroup g = new PolygonGroup();
        for (int i = 0; i < group.length; i++) {
            g.add(group[i]);
        }        
        return g;
    }
}
