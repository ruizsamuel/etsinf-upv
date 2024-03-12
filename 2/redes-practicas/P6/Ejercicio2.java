import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio2 {

    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 7777;

    public static void main (String[] args) {
        try {

            Socket s = new Socket(SERVER_IP, SERVER_PORT);

            In in = new In(s);
            in.start();

            Scanner teclado = new Scanner(System.in);
            PrintWriter salida = new PrintWriter(s.getOutputStream(), true);

            String line;
            while (!(line = teclado.nextLine()).equals("quit")) {
                salida.println(line);
            }

            salida.println("quit");

            teclado.close();
            salida.close();
            s.close();

        } catch (IOException e){
            System.out.println (e);
        }
    }
}

class In extends Thread {
    Socket s;
    
    public In(Socket s) {
        this.s = s;
    }

    public void run() {
        try {

            Scanner entrada = new Scanner(s.getInputStream());
            while(true) {
                String linea = entrada.nextLine();
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}