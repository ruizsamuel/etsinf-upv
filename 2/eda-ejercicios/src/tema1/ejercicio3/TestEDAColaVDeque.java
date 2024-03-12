package tema1.ejercicio3;


import librerias.estructurasDeDatos.modelos.ColaPlus;

public class TestEDAColaVDeque {
    public static void main(String[] args) {
        ColaPlus<Integer> q = new ArrayDequeColaPlus<>();

        System.out.println("Creada una Cola con " + q.talla() + "Integer, q = " + q);
        q.encolar(10);
        q.encolar(20);
        q.encolar(30);
        System.out.println("La Cola de Integer actual es q = " + q);
        System.out.println("Usando otros métodos para mostrar sus Datos el resultado es ...");
        StringBuilder datosQ = new StringBuilder();
        while (!q.esVacia()) {
            Integer primero = q.primero();
            if (primero.equals(q.desencolar())) datosQ.append(primero).append(" ");
            else datosQ.append("ERROR ");
        }
        System.out.println(" el mismo, " + datosQ
                + ", PERO q se queda vacia, q = " + q);
    }
}
