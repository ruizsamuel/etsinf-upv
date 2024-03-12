import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main (String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("ServerSocket abierto en " + ss.getInetAddress() + ":" + ss.getLocalPort());
            while (true) {
                Socket s = ss.accept();
                System.out.println("Socket creado en " + s.getLocalAddress() + ":" + s.getLocalPort() + " para nuevo cliente " + s.getInetAddress() + ":" + s.getPort());
                Scanner entrada = new Scanner(s.getInputStream());
                PrintWriter salida = new PrintWriter(s.getOutputStream(), true);

                String eco = entrada.nextLine();
                salida.println(eco);
                s.close();
            }
        } catch (IOException e){
            System.out.println (e);
        }

    }
}