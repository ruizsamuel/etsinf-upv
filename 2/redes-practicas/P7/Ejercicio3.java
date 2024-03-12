import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ejercicio3 {

    public static void main(String[] args) {

        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress dirIP = InetAddress.getByName("localhost");
            
            String message = ("Samuel Ruiz\n");
            DatagramPacket data = new DatagramPacket(message.getBytes(), message.getBytes().length, dirIP, 7777);
            ds.send(data);

            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}