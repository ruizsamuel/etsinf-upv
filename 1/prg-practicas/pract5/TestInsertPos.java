package pract5;

import graph2D.Graph2D;
import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Clase programa para facilitar la prueba del codigo, desarrollado por 
 * el alumno, para el metodo insertPos.
 * El main crea una secuencia con 3 cuadrados de igual tamanyo superpuestos:
 * gris en el origen, verde desplazado a la derecha y arriba y rojo tambien  
 * desplazado a la derecha y arriba: Gris -> Verde -> Rojo.
 * Se realizan cuatro pruebas partiendo de la secuencia original para insertar
 * en diferentes posiciones:
 * 1.- Llamar al metodo insertPos sobre la secuencia de cuadrados con una posicion
 *     no valida (-1) y comprobar que no hace nada. Seguimos viendo la secuencia
 *     Gris -> Verde -> Rojo.
 * 2.- Llamar al metodo insertPos(p, 0) para insertar un rectangulo negro
 *     en la posicion 0, es decir en el frente. Ahora veremos la secuencia
 *     Gris -> Verde -> Rojo -> Negro.
 * 3.- Llamar al metodo insertPos(p, 2) para insertar un rectangulo azul 
 *     en la posicion 2, esto es, entre el Verde y el Rojo. Ahora veremos la secuencia
 *     Gris -> Verde -> Azul -> Rojo -> Negro.
 * 4.- Llamar al metodo insertPos(p, 5) para insertar un rectangulo amarillo
 *     en la posicion 5, es decir, en el fondo. Ahora veremos la secuencia
 *     Amarillo -> Gris -> Verde -> Azul -> Rojo -> Negro.
 *     
 * Esta clase es para uso particular del alumno. 
 * No se entrega.
 *
 * @author PRG
 * @version Curso 2021-22
 */
public class TestInsertPos {
    private TestInsertPos() { } // No se usan objetos de esta clase
    
    public static void main(String[] args)  {
        Scanner keyB = new Scanner(System.in);
        Graph2D gd = new Graph2D(-20, 20, -20, 20, 600, 400, Color.WHITE, 
            "Test insertPos");
        //Crea un grupo de 3 cuadrados que se superponen formando escalera
        PolygonGroupExam g = makeGroup(); 
        Polygon[] a = g.toArray();  
        drawGroup(gd, g);
        System.out.println("Veras 3 cuadrados superpuestos formando escalera.");
        System.out.println("Secuencia: Gris -> Verde -> Rojo");
        System.out.println("Pulsa INTRO para continuar..."); 
        String s = keyB.nextLine();
        
        //----- Prueba 1 ----
        System.out.println("** Test 1: insertar un rectangulo negro en -1 (no valida).");      
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        //Insertar el poligono en posicion no correcta, p.e. -1
        Polygon polBlack = makeBlack();
        g.insertPos(polBlack, -1);
        drawGroup(gd, g); 
        System.out.println("... Comprueba que el grupo no ha cambiado.");          
        boolean okFront = g.getFront() != null && equals(g.getFront().data, a[a.length - 1]);
        boolean okBack = g.getBack() != null && equals(g.getBack().data, a[0]);
        if (!okFront || !okBack) {
            System.out.println("    --> ERROR: Problemas con las referencias front y/o back");
        }
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        //----- Prueba 2 ----
        System.out.println("** Test 2: insertar un rectangulo negro en 0 (frente).");
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        // Insertar el poligono en la posicion 0 (el del frente)
        g.insertPos(polBlack, 0);
        drawGroup(gd, g); 
        System.out.println("... Comprueba que en el frente esta el rectangulo negro, ");
        System.out.println("    Secuencia: Gris -> Verde -> Rojo -> Negro.");
        okFront = g.getFront() != null && equals(g.getFront().data, polBlack);
        okBack = g.getBack() != null && equals(g.getBack().data, a[0]);
        if (!okFront || !okBack) {
            System.out.println("    --> ERROR: Problemas con las referencias front y/o back");
        }
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        //----- Prueba 3 ----
        System.out.println("** Test 3: insertar un rectangulo azul en 2 (entre Verde y Rojo).");
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        // Insertar el poligono en la posicion 2 (el de enmedio)
        Polygon polBlue = makeBlue();
        g.insertPos(polBlue, 2);
        drawGroup(gd, g); 
        System.out.println("... Comprueba que el rectangulo azul se ha insertado. ");
        System.out.println("    Secuencia: Gris -> Verde -> Azul -> Rojo -> Negro.");     
        okFront = g.getFront() != null && equals(g.getFront().data, polBlack);
        okBack = g.getBack() != null && equals(g.getBack().data, a[0]);
        if (!okFront || !okBack) {
            System.out.println("    --> ERROR: Problemas con las referencias front y/o back");
        }
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        //----- Prueba 4 ----
        System.out.println("** Test 4: insertar un rectangulo amarillo en 5 (al fondo).");
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        // Insertar el poligono en la posicion 5 (el del fondo)
        Polygon polY = makeYellow();
        g.insertPos(polY, 5);
        drawGroup(gd, g); 
        System.out.println("... Comprueba que el rectangulo amarillo se ha insertado, ");
        System.out.println("    Secuencia: Amarillo -> Gris -> Verde -> Azul -> Rojo -> Negro.");
        okFront = g.getFront() != null && equals(g.getFront().data, polBlack);
        okBack = g.getBack() != null && equals(g.getBack().data, polY);
        if (!okFront || !okBack) {
            System.out.println("    --> ERROR: Problemas con las referencias front y/o back");
        }
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();     
        
        System.out.println("Fin del proceso!!!");
    }
    
    /** 
     * Muestra en la salida grafica un grupo de poligonos dado.
     * @param gd Graph2D, el grafo.
     * @param p PolygonGroup, el grupo de poligonos.
     */
    private static void drawGroup(Graph2D gd, PolygonGroupExam g) {
        gd.clear(); 
        Polygon[] aPol = g.toArray();
        for (int i = 0; i < aPol.length; i++) {
            gd.fillPolygon(aPol[i].verticesX(), aPol[i].verticesY(),
                aPol[i].getColor(), 2);   
        }   
    }
    
    /** 
     * Crea un grupo con 3 cuadrados en escalera.
     * @return p PolygonGroup, el grupo de poligonos.
     */
    private static PolygonGroupExam makeGroup() {
        PolygonGroupExam g = new PolygonGroupExam();
        // Crea un rectangulo gris centrado en (-6.0, -6.0) de tamanyo 4   
        double[] x0 = {-8.0, -8.0, 0.0, 0.0};
        double[] y0 = {-8.0, 0.0, 0.0, -8.0};
        Polygon pol = new Polygon(x0, y0);
        pol.setColor(Color.GRAY);
        g.add(pol);
        // Anyade un cuadrado verde centrado en el origen del mismo tamanyo
        double[] x1 = {-4.0, -4.0, 4.0, 4.0};
        double[] y1 = {-4.0, 4.0, 4.0, -4.0};
        pol = new Polygon(x1, y1);
        pol.setColor(Color.GREEN);
        g.add(pol);
        // Anyade un cuadrado rojo del mismo tamanyo y centrado en (6.0, 6.0)
        double[] x2 = {0.0, 0.0, 8.0, 8.0};
        double[] y2 = {0.0, 8.0, 8.0, 0.0};
        pol = new Polygon(x2, y2);
        pol.setColor(Color.RED);
        g.add(pol);
        
        return g;
    }
    
    // Crea un rectangulo negro de base 4 y altura 8 con
    // la esquina inferior izquierda en (4.0, 4.0)
    private static Polygon makeBlack() {
        double[] x = {4.0, 4.0, 8.0, 8.0};
        double[] y = {4.0, 12.0, 12.0, 4.0};
        Polygon pol = new Polygon(x, y);
        pol.setColor(Color.BLACK);
        return pol;
    }
    
    // Crea un rectangulo azul de base 4 y altura 8 con
    // la esquina inderior izquierda en (-2.0, 2.0)
    private static Polygon makeBlue() { 
        double[] x = {-2.0, -2.0, 2.0, 2.0};
        double[] y = {2.0, 10.0, 10.0, 2.0};
        Polygon pol = new Polygon(x, y);
        pol.setColor(Color.BLUE);
        return pol;
    }
    
    // Crea un rectangulo amarillo de base 4 y altura 8 con
    // la esquina inferior izquierda en (-10.0, -4.0)
    private static Polygon makeYellow() {
        double[] x = {-10.0, -10.0, -6.0, -6.0};
        double[] y = {-4.0, 6.0, 6.0, -4.0};
        Polygon pol = new Polygon(x, y);
        pol.setColor(Color.YELLOW);
        return pol;
    }
    
    /** Comprueba si dos poligonos son iguales.
     *  @param p Polygon.
     *  @param q Polygoon.
     *  @return boolean, true si son iguales; en caso contrario, false. 
     */
    private static boolean equals(Polygon p, Polygon q) {
        return p != null && q != null 
            && p.getColor().equals(q.getColor())
            && Arrays.equals(p.verticesX(), q.verticesX())
            && Arrays.equals(p.verticesY(), q.verticesY());
    }
}
