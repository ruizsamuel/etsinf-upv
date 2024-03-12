package ejercicio4;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteDayTime {

    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket s = new Socket("zoltar.redes.upv.es", 13);
        Scanner entrada = new Scanner(s.getInputStream());

        System.out.println(entrada.nextLine());

        entrada.close();
        s.close();

        return;
    }
}
