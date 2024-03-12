package pract3;

import java.util.Scanner;

/**
 *  Clase Test3.
 *  Una primera clase con lectura de datos desde teclado, 
 *  y uso de operaciones con int, long, Math y String.
 *  Contiene tres errores de compilacion.
 *  @author IIP 
 *  @version Curso 2021-22
 */
 
public class Test3 {

    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Lectura de teclado de una hora.");
        System.out.println("   -> Introduzca las horas (entre 0 y 23): ");
        int h = kbd.nextInt();
        String hString = "0" + h;
        System.out.print("   -> Introduzca los minutos (entre 0 y 59): ");
        int m = kbd.nextInt();
        String mString = "0" + m;
        System.out.println("Hora introducida: " + hString.substring(hString.length() - 2) + ":" + mString.substring(mString.length() - 2));
        
        long tMinTotal = System.currentTimeMillis() / (60 * 1000);
        int tMinCurrent = (int) tMinTotal % (24 * 60);
        System.out.println("La hora acutal es: " + (((int) tMinCurrent / 60) + 2) + ":" + tMinCurrent % 60);
        
        int inMinTotal = h * 60 + m;
        int diff = Math.abs(inMinTotal - tMinCurrent);
        System.out.println("La diferencia en minutos entre la hora actual y la introcudica es: " + diff + " minutos " + 
            "(" + (int) diff / 60 + " horas y " + diff % 60 + " minutos)");
            
        if (hString.substring(hString.length() - 2).charAt(0) == mString.substring(mString.length() - 2).charAt(1) 
            && hString.substring(hString.length() - 2).charAt(1) == mString.substring(mString.length() - 2).charAt(0)){
            System.out.println("La hora introducida es capicua.");
        } else {
            System.out.println("La hora introducida no es capicua");
        }
    }    
}
