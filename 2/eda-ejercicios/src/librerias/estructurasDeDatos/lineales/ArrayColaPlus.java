package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

// Un ArrayColaPlus ES UN ArrayCola que implementa ColaPlus

public class ArrayColaPlus<E> extends ArrayCola<E> implements ColaPlus<E> {
    /** obtiene la talla de una Cola **/
    public final int talla() { 
        // usando unicamente los metodos que hereda de ArrayCola
        /* int res = 0;
           while (!this.esVacia()) { 
               E primero = this.desencolar(); 
               res++; 
           }
           return res;
        */

        // usando unicamente los atributos que hereda de ArrayCola, 
        // la mas eficiente
        return super.talla;
    }
}