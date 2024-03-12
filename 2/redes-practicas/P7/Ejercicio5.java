import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class Ejercicio5 {

    public static void main(String[] args) {

        try {
            DatagramSocket ds = new DatagramSocket(7777);

            byte[] buffer = new byte[2048];
            DatagramPacket receive = new DatagramPacket(buffer, 2048);
            ds.receive(receive);
            
            Date now = new Date();
            String message = now.toString() + "\r\n";
            
            DatagramPacket send = new DatagramPacket(message.getBytes(), message.getBytes().length, receive.getAddress(), receive.getPort());

            ds.send(send);
            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}