package aplicaciones.hospital;

import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.ArrayCola;

/** Clase ServidorCola: 
 *  implementa un ServidorDeQuirofano 
 *  que usa un modelo FIFO (Cola) 
 *  para gestionar los pacientes en espera.
 *  
 *  @author  Profesores EDA
 *  @version Septiembre 2023
 */

public class ServidorColaFIFO implements ServidorQuirofano {
    
    /** Un ServidorColaFIFO TIENE UNA Cola c de pacientes en espera */
    protected Cola<Paciente> c; 
    
    /** Crea un servidor vacio */
    public ServidorColaFIFO() { c = new ArrayCola<Paciente>(); }
    
    /** Incluye un nuevo Paciente en espera, p, en un ServidorQuirofano.
     *  @param p   Paciente
     */
    public void insertarEnEspera(Paciente p) { c.encolar(p); }
    
    
    /** Comprueba si hay algun Paciente en espera en un ServidorQuirofano.
     *  @return   boolean
     */
    public boolean hayPacientes() { return !c.esVacia(); }
    
    /** SII hayPacientes(): 
     *  devuelve el Paciente de un ServidorQuirofano que va a ser operado.
     *  @return   Paciente
     */
    public Paciente getPaciente() { return c.primero(); }
    
    /** SII hayPacientes(): 
     *  elimina de un ServidorDeQuirofano el Paciente que va a ser operado 
     *  y devuelve el Paciente actualizando valor de ingresoEnQuirofano  
     *  @param h   int (horas)
     *  @return    Paciente 
     */
    public Paciente operarPaciente(int h) { 
        Paciente p = c.desencolar();
        p.setIngresoEnQuirofano(h + TIEMPO_QUIROFANO);
        return p;
    }
}
