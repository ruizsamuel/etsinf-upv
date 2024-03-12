package exam1;

/**
 * Clase MyString: Ejercicio Practica 2 - Parcial 1 Lab
 * 
 * @author PRG 
 * @version Curso 2021-22
 */
public class MyString {    
    /** No hay objetos de esta clase */
    private MyString() { }
    
    /**
     * Devuelve true si el char c es una vocal y,
     * en caso contrario, devuelve false.
     */
    private static boolean isVowel(char c) {
        final String VOWELS = "aeiouAEIOU";
        return VOWELS.indexOf(c) != -1;
    }
    
    /**
     * Devuelve el String que resulta de sustituir cada 
     * una de las vocales del String s por el caracter c.
     */
    public static String replaceVowels(String s, char c) {
        if (s.length() < 1) {
            return s;
        }
        return replaceVowels(s, c, 0, s.length() - 1);
    }
    
    public static String replaceVowels(String s, char c, int ini, int fin) {
        if (fin < ini) {
            return s;
        } else {
            if (isVowel(s.charAt(ini))) {
                s = s.replace(s.charAt(ini), c);
            }
            return replaceVowels(s, c, ini + 1, fin);
        }
    }
    
    /** Muestra el resultado del metodo para varios String ejemplo.
     *  Puedes probar con otros Strings.  
     */
    public static void main(String[] args) {
        System.out.println("Se realizan 8 llamadas a replaceVowels(String, char), ");
        System.out.println("para sustituir las vocales del String por el carácter 'Z'.");
        System.out.println("La primera llamada con la cadena vacía.");
        System.out.println("Se muestra el String de prueba, la solución correcta y tu solución.\n");
        
        String[] s = {"", "bcd", "abcd", "bcde", 
                "aaa 4 uuu", "ctrlandX", "aeiou", "Shine On Until Tomorrow"}; 
        String[] sol = {"", "bcd", "Zbcd", "bcdZ", "ZZZ 4 ZZZ", "ctrlZndX", "ZZZZZ", "ShZnZ Zn ZntZl TZmZrrZw"};
        
        System.out.printf("%-30s %-30s %-15s\n", "      s", "replaceVowels(s, 'Z')", "Tu solución");
        for (int i = 0; i < s.length; i++) {
            System.out.printf("%-30s %-30s ", s[i], sol[i]);
            System.out.printf("%-15s\n", replaceVowels(s[i], 'Z'));
        }
    }
}