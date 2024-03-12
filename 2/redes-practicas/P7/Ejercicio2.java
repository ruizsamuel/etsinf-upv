import java.net.DatagramSocket;
import java.net.SocketException;

public class Ejercicio2 {

    public static void main(String[] args) {

        try {
            DatagramSocket ds = new DatagramSocket();
            System.out.println(ds.getLocalPort());
            ds.close();
        } catch (SocketException e) {
            System.err.println(e);
        }
    }
}