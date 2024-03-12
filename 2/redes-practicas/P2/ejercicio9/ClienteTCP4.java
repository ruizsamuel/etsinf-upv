package ejercicio9;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP4 {

    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Socket s = new Socket("www.upv.es", 80);

        System.out.println("Puerto de retorno del Socket: " + s.getPort());
        System.out.println("IP remota del Socket: " + s.getInetAddress());
        System.out.println("Puerto local del Socket: " + s.getLocalPort());
        System.out.println("IP local del Socket: " + s.getLocalAddress());

        s.close();
    }
}
