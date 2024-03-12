package exam2;

import java.util.Locale;
import others2.CasesEx1T4;
/**
 * Clase programa para facilitar la prueba del código, desarrollado por
 * el alumno, para el método sumNTerms.
 *
 * @author Parcial 2 - Ejercicio 1 
 * @version IIP - Curso 2021/22
 */
public class TestSumNTerms {
    
    private TestSumNTerms() { }        

    public static void main(String[] args) {
        System.out.println("Se realizan 10 llamadas a sumNTerms(int)");
        System.out.println("Las tres primeras con n = 1, 2 y 3.");
        System.out.println("El resto con valores aleatorios para n en [1..9999].");
        System.out.println("Se muestra la solución correcta y tu solución.\n");
        
        for (int i = 1; i <= 3; i++) {
            double solCorrecta = CasesEx1T4.correcto(i);
            double solAlumno = IIPMath.sumNTerms(i);
            System.out.printf(Locale.US, "sumNTerms(%d) = %.15f\n --> Tu solución: %.15f\n", 
                i, solCorrecta, solAlumno);
        }            
            
        for (int i = 4; i <= 10; i++) {
            int n = (int) (Math.random() * 9999 + 1);
            double solCorrecta = CasesEx1T4.correcto(n);
            double solAlumno = IIPMath.sumNTerms(n);
            System.out.printf(Locale.US, "sumNTerms(%4d) = %.10f\n --> Tu solución: %.10f\n", 
                n, solCorrecta, solAlumno);
        }         
    }
    
}
