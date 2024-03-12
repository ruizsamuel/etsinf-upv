import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main (String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            while (true) {
                Socket s = ss.accept();
                System.out.println("Socket creado para nuevo cliente");
                Scanner entrada = new Scanner(s.getInputStream());
                PrintWriter salida = new PrintWriter(s.getOutputStream(), true);

                salida.println("HTTP/1.0 200 OK \r");
                salida.println("Content-Type: text/plain \r");
                salida.println("\r");
                
                String linea;
                while (!(linea = entrada.nextLine()).equals("")) {
                    salida.println(linea);
                }

                s.close();
            }
        } catch (IOException e){
            System.out.println (e);
        }

    }
}