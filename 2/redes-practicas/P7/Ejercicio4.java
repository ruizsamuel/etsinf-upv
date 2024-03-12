import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ejercicio4 {

    public static void main(String[] args) {

        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress dirIP = InetAddress.getByName("localhost");
            
            String message = ("Soy un cliente\n");
            DatagramPacket send = new DatagramPacket(message.getBytes(), message.getBytes().length, dirIP, 7777);

            byte[] buffer = new byte[2048];
            DatagramPacket receive = new DatagramPacket(buffer, 2048);

            ds.send(send);
            ds.receive(receive);

            String respuesta = new String(receive.getData(), 0, receive.getLength());
            System.out.println(respuesta);

            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}