package exam1;

import others.Point;
import java.util.Locale;
import java.util.Scanner;
/**
 * Clase MainExam: clase programa que usa la clase others.Point, 
 * identica a tu clase Point con el metodo yIntercept(double, Point) correcto. 
 * 
 * @author IIP
 * @version Curso 2021-22
 */
public class MainExam {
    // No se usan objetos de esta clase
    private MainExam() { }
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Lectura de teclado de la pendiente de una recta.");
        System.out.print("   -> Introduzca la pendiente: ");
        double m = teclado.nextDouble();
        
        System.out.println("Lectura de teclado de las coordenadas de un punto.");
        System.out.print("   -> Introduzca la abscisa: ");
        double abs1 = teclado.nextDouble();
        System.out.print("   -> Introduzca la ordenada: ");
        double ord1 = teclado.nextDouble();
        
        System.out.println("Lectura de teclado de las coordenadas de otro punto.");
        System.out.print("   -> Introduzca la abscisa: ");
        double abs2 = teclado.nextDouble();
        System.out.print("   -> Introduzca la ordenada: ");
        double ord2 = teclado.nextDouble();
        // Una vez leidas las coordenadas desde el teclado y 
        // suponiendo que son correctas:
        // a) Crea un Point p1 a partir de abs1 y ord1
        Point p1 = new Point(abs1, ord1);
        
        // b) Crea un Point p2 a partir de abs2 y ord2
        Point p2 = new Point(abs2, ord2);
        
        // c) Obten el Point p3 resultado de aplicar a p1 el metodo yIntercept 
        //    pasando como argumentos la pendiente m y el Point p2
        Point p3 = p1.yIntercept(m, p2);
        
        // d) Muestra por pantalla p3 (en el formato "(abscisa,ordenada)")        
        System.out.println(p3);      
    }
}