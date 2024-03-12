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
                Conn t = new Conn(s);
                t.start();
            }
        } catch (IOException e){
            System.out.println (e);
        }
    }
}

class Conn extends Thread {
    Socket s;
    
    public Conn(Socket s) {
        this.s = s;
    }

    public void run() {
        try {

            Scanner entrada = new Scanner(s.getInputStream());
            PrintWriter salida = new PrintWriter(s.getOutputStream(), true);
            String linea;

            while (!(linea = entrada.nextLine()).equalsIgnoreCase("FIN")) {
                salida.println(linea);
            }

            salida.println("FIN");
            s.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}