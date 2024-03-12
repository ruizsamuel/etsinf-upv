package ejercicio10;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteHTTP {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("www.upv.es", 80);
        
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        Scanner sc = new Scanner(s.getInputStream());

        pw.println("GET / HTTP/1.0\r\n\r\n");

        while(sc.hasNext()) {
            System.out.println(sc.nextLine());
        }

        pw.close();
        sc.close();
        s.close();
    }
}
