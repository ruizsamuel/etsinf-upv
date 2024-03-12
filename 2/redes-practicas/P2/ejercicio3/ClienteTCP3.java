package ejercicio3;
import java.io.IOException;
import java.net.*;

public class ClienteTCP3 {
    
    public static void main(String[] args) {

        Socket s;
        try {
            s = new Socket("www.upehfev.es", 80);
            System.out.println("¡Conectado!");
            s.close();
        } catch (UnknownHostException e) {
            System.err.println("Nombre de servidor desconocido");
            return;
        } catch (NoRouteToHostException e) {
            System.err.println("No es posible realizar la conexión");
            return;
        } catch (IOException e) {
            System.err.println("Error" + e);
            return;
        }
    }
}