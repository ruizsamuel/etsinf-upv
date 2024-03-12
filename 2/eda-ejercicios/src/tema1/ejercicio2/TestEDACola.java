package tema1.ejercicio2;

import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.modelos.Cola;

public class TestEDACola {
  public static void main(String[] args) {      
      Cola<Integer> q = new ArrayCola<>();

      int tallaQ = 0;
      System.out.println("Creada una Cola con " + tallaQ + "Integer, q = " + q);
      q.encolar(10);
      tallaQ++;
      q.encolar(20);
      tallaQ++;
      q.encolar(30);
      tallaQ++;
      System.out.println("La Cola de Integer actual es q = " + q);
      System.out.println("Usando otros métodos para mostrar sus Datos el resultado es ...");
      StringBuilder datosQ = new StringBuilder();
      while (!q.esVacia()) {
          Integer primero = q.primero();
          if (primero.equals(q.desencolar())) datosQ.append(primero).append(" ");
          else datosQ.append("ERROR ");
          tallaQ--;
      }
      System.out.println(" el mismo, " + datosQ 
          + ", PERO q se queda vacia, q = " + q);
  }
}