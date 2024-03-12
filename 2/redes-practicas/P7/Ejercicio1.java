import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ejercicio1 {

    public static void main(String[] args) {

        try {
            InetAddress ipServer = InetAddress.getByName(args[0]);
            System.out.println(ipServer);

        } catch (UnknownHostException e) {
            System.err.println(e);
        }
    }
}