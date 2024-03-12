package pract6;

/**
 * Clase IIPMath: clase de utilidades que implementa algunas operaciones 
 * matematicas: IIPMath.sqrt(double) y IIPMath.log(double).
 *
 * @author IIP
 * @version Curso 2021-2022
 */
public class IIPMath {
    /** Valor aproximado de log(2). */
    private static final double LOG2 = 0.6931471805599453;
    
    /** No se pueden crear objetos de esta clase. */
    private IIPMath() { }    
    
    /**
     * Devuelve la raíz cuadrada de x >= 0, con error epsilon > 0. 
     * @param x. El valor, que debe ser mayor o igual a 0
     * @param epsilon. Error máximo aceptado para el cálculo
     * @return double. La raiz cuadrada aproximada de x con error máximo épsilon
     */
    public static double sqrt(double x, double epsilon) {
        double t = (1+x)/2;
        double error = epsilon + 1;
        while (error >= epsilon) {
            error = t;
            t = (t + (x/t))/2;
            error = error - t;
        }
        return t;
    }            

    /**
     * Devuelve la raíz cuadrada de x >= 0, con error 1e-15. 
     * @param x. El valor, que debe ser mayor o igual a 0
     * @return double. La raiz cuadrada aproximada de x con error máximo 1e-15
     */
    public static double sqrt(double x) {    
        double t = (1+x)/2;
        double error = 1e-15 + 1;
        while (error >= 1e-15) {
            error = t;
            t = (t + (x/t))/2;
            error = error - t;
        }
        return t;
    }               
                   
    /**
     * Devuelve log(z), 1/2 <= z < 1, con error epsilon > 0.
     * @param z. El valor, que debe estar comprendido entre 1/2 y 1
     * @param epsilon. Error máximo aceptado para el cálculo
     * @return double. El logaritmo naatural aproximado de z con error máximo épsilon
     */
    public static double logBase(double z, double epsilon) {
        double y = (1 - z) / (1 + z);
        
        double u = y;
        
        double uPrev;
        double suma = u;
        double error;
        int i = 1;
        do {
            uPrev = u;
            u = y * y * (2 * i - 1) / (2 * i + 1) * u;
            suma += u;
            error = uPrev - u;
            i++;
        } while(error >= epsilon); 
        
        return -2 * suma;
    }
            
    /**
     * Devuelve log(x), x > 0, con error epsilon > 0.
     * @param x. El valor, que debe ser mayor que 0
     * @param epsilon. Error máximo aceptado para el cálculo
     * @return double. El logaritmo naatural aproximado de x con error máximo épsilon
     */
    public static double log(double x, double epsilon) {      
        double res = logBase(x, epsilon);
        if(x > 1) {
            double z = x;
            int m = 1;
            do {
                z /= 2;
                m++;
            } while(z > 1);
            res = m * LOG2 * logBase(z, epsilon);
        } else if(x < 0.5) {
            res = -log(1 / x);
        } else if(x == 0){
            res = 0;
        }
        return res;
    }

    /**
     * Devuelve log(x), x > 0, con error 1e-15.
     * @param x. El valor, que debe ser mayor que 0
     * @return double. El logaritmo naatural aproximado de x con error máximo 1e-15
     */
    public static double log(double x) {    
        return log(x, 1e-15);
    }
}
