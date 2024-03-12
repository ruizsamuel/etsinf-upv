package ejercicio2;
import java.net.*;

public class ClienteTCP2 {
    
    public static void main(String[] args) {

        Socket s;
        try {
            s = new Socket("www.upv.es", 80);
            System.out.println("¡Conectado!");
            s.close();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            return;
        }
    }
}