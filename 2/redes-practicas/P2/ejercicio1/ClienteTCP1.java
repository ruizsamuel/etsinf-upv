package ejercicio1;
import java.net.*;
import java.io.*;

public class ClienteTCP1 {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("www.upv.es", 80);
        System.out.println("¡Conectado!");
        s.close();
    }
}