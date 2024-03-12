import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main (String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            while (true) {
                Socket s = ss.accept();
                System.out.println("Se ha conectado un cliente al servidor");
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