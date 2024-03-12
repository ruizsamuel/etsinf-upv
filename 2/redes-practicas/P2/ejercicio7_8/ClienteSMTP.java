package ejercicio7_8;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSMTP {

    public static void main(String[] args) throws UnknownHostException, IOException{

        System.setProperty("line.separator", "\r\n");
        
        Socket s = new Socket("smtp.upv.es", 25);

        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        Scanner sc = new Scanner(s.getInputStream());

        System.out.println(sc.nextLine());

        pw.println("HELO 158.42.46.65");

        System.out.println(sc.nextLine());

        pw.close();
        sc.close();
        s.close();

        return;
    }
}
