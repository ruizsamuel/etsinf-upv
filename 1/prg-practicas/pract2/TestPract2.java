package pract2;

/**
 * Clase TestPract2: clase programa que prueba 
 * los métodos isPrefix, isSubstring de PRGString
 * 
 * @author PRG - ETSINF - DSIC - UPV
 * @version Curso 2021/2022
 */
public class TestPract2 {
    /** No hay objetos de esta clase */
    private TestPract2() { }
    
    public static void testIsPrefix() {
        String[] s = {"", "rec", "pecur", 
                      "recurso", "remursi", 
                      "123456789", "recursion"};
                      
        System.out.printf("%8s %12s %20s %12s\n", 
            "a", "b", "isPrefix(a, b)", "b.startsWith(a)");
            
        // a y b vacías
        System.out.printf("%8s %12s %20s %12s\n", 
            s[0], s[0], "isPrefix(a, b)", "b.startsWith(a)");
                          
        // solo a vacía                          
        /* COMPLETAR */
        
        // solo b vacía                  
        /* COMPLETAR */
                          
        // a de mayor longitud que b                  
        /* COMPLETAR */
        
        // a y b de igual longitud y a es prefijo de b                  
        /* COMPLETAR */
        
        // a y b de igual longitud y a no es prefijo de b                  
        /* COMPLETAR */
        
        // a de menor longitud que b y a es prefijo de b                  
        /* COMPLETAR */
        
        // a de menor longitud que b y a no es prefijo de b:
        // por el primer carácter
        /* COMPLETAR */
        
        // a de menor longitud que b y a no es prefijo de b:
        // por el ultimo carácter
        /* COMPLETAR */
        
        // a de menor longitud que b y a no es prefijo de b:
        // por un carácter intermedio
        /* COMPLETAR */        
    }
      
    public static void testIsSubstring() {
        String[] s = {"", "rec", "pecur", 
                      "recurso", "remursi", 
                      "123456789", "recursion",
                      "sion", "curs"};
               
        System.out.printf("%8s %12s %20s %10s\n", 
                          "a", "b", "isSubstring(a,b)", "b.contains(a)"); 
        // a y b vacías
        /* COMPLETAR */
        
        // solo a vacía                          
        /* COMPLETAR */
        
        // solo b vacía                  
        /* COMPLETAR */
        
        // a de mayor longitud que b                  
        /* COMPLETAR */
        
        // a y b de igual longitud y a es subcadena de b                  
        /* COMPLETAR */
        
        // a y b de igual longitud y a no es subcadena de b                  
        /* COMPLETAR */
        
        // a de menor longitud que b y a es sucadena de b
        // porque a es prefijo de b
        /* COMPLETAR */
        
        // a de menor longitud que b y a es sucadena de b
        // porque a es sufijo de b
        /* COMPLETAR */
        
        // a de menor longitud que b y a es sucadena de b
        // porque a está en b a partir de una posición intermedia
        /* COMPLETAR */       
        
    }
     
    private static void compareIsPrefix(String a, String b) {
        System.out.printf("%12s %12s %12b %12b\n", a, b, 
                          PRGString.isPrefix(a, b),
                          b.startsWith(a));
    }
    
    private static void compareIsSubstring(String a, String b) {
        System.out.printf("%12s %12s %12b %12b\n", a, b, 
                          PRGString.isSubstring(a, b),
                          b.contains(a));
    }
   
}