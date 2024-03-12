package pract4;
import java.util.Scanner;
/**
 *  Clase Test4.
 *  Clase de prueba de TimeInstat.
 *  @author IIP 
 *  @version Curso 2021-22
 */
public class Test4 {
    
    /** No hay objetos de esta clase */
    private Test4() { }
    
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Lectura de teclado de una hora.");
        System.out.print("   -> Introduzca las horas (entre 0 y 23): ");
        int h = kbd.nextInt();
        System.out.print("   -> Introduzca los minutos (entre 0 y 59): ");
        int m = kbd.nextInt();
        
        TimeInstant ti = new TimeInstant(h,m);
        TimeInstant actual = new TimeInstant();
        
        int diff = ti.compareTo(actual);
     
        System.out.println("Hora introducida: " + ti);
        System.out.println("Hora actual: " + actual);
        System.out.println("Diferencia en minutos entre ambas horas: " + 
                        Math.abs(diff));
        
        String comparacion;
        if (diff > 0) {
            comparacion = "No";
        } else if (diff < 0) {
            comparacion = "Sí";
        } else {
            comparacion = "Son iguales";
        }
        
        System.out.println("Es anterior la hora introducida a la hora actual? " +
                        comparacion);
    }    
 
}   
