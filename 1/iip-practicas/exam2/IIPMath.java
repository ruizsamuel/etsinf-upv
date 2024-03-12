package exam2;

/**
 * Clase IIPMath: clase de utilidades que implementa
 * algunas operaciones matematicas.
 * 
 * @author Parcial 2 - Ejercicio 1 
 * @version IIP - Curso 2021/22
 */
public class IIPMath {
    /** No se pueden crear objetos de este tipo. */
    private IIPMath() { }
    
    /** Devuelve la suma de los n primeros terminos  
     *  de la serie siguiente:
     *     a_1 = Raiz cuadrada de 2 
     *     a_i = Raiz cuadrada de (2 * a_(i-1)) + i, i > 1
     *  PRECONDICION: 0 < n
     */
    public static double sumNTerms(int n) { 
        double a = Math.sqrt(2);
        double res = 0;
        
        for (int i = 1; i <= n; i++) {
            res += a;
            a = Math.sqrt(2 * a) + i +1;
        }
        
        return res;
    }
}