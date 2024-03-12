package practica2;

/**
 * class WrapperClassesUse.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class WrapperClassesUse {        
    public static void main(String[] args) {            
        // Assignment of wrapper variables to elementary types 
        int i = new Integer(123456);
        double j = new Double(123.456);
        char k = new Character('k');
            
        // Writing elementary variables
        System.out.println("int i = " + i);
        System.out.println("double j = " + j);
        System.out.println("char k = " + k);
               
        // Assignment of elementary values to wrapper variables
        Integer eI = 123456; 
        Double eJ = 123.456;
        Character eK = 'k';
            
        // Writing wrapper variables
        System.out.println("Integer I = " + eI);
        System.out.println("Double J = " + eJ);
        System.out.println("Character K = " + eK);
    }    
}