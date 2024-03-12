package ejercicio5_6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteEco {

    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket s = new Socket("zoltar.redes.upv.es", 7);

        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        //PrintWriter pw = new PrintWriter(s.getOutputStream(), false);
        Scanner sc = new Scanner(s.getInputStream());

        pw.println("¡Hola Mundo!");
        //pw.flush();

        System.out.println(sc.nextLine());

        pw.close();
        sc.close();
        s.close();

        return;
    }
}
