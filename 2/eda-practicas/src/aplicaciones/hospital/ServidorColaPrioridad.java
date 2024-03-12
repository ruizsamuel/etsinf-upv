package aplicaciones.hospital;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;

/** Clase ServidorColaPrioridad: 
 *  implementa un ServidorDeQuirofano 
 *  que usa un modelo de maxima prioridad (ColaPrioridad) 
 *  para gestionar los pacientes en espera.
 *  
 *  @author  Profesores EDA
 *  @version Septiembre 2023
 */

public class ServidorColaPrioridad implements ServidorQuirofano {
    
    /** Un ServidorColaPrioridad TIENE UNA ColaPrioridad cP de pacientes en espera */
    protected ColaPrioridad<Paciente> cP;
    
    /** Crea un servidor vacio */
    public ServidorColaPrioridad() { 
        cP = new MonticuloBinario<>();
    }
    
    /** Incluye un nuevo Paciente en espera, p, en un ServidorQuirofano.
     *  @param p   Paciente
     */
    public void insertarEnEspera(Paciente p) { 
        cP.insertar(p);
    }
    
    /** Comprueba si hay algún Paciente en espera en un ServidorQuirofano.
     *  @return boolean
     */
    public boolean hayPacientes() { 
        return !cP.esVacia();
    }
    
    /** SII hayPacientes(): 
     *  devuelve el Paciente de un ServidorQuirofano que va a ser operado.
     *  @return Paciente
     */
    public Paciente getPaciente() { 
        return cP.recuperarMin();
    }
    
    /** SII hayPacientes(): 
     *  elimina de un ServidorDeQuirofano el Paciente que va a ser operado 
     *  y devuelve el Paciente actualizando valor de ingresoEnQuirofano,
     *  resultado de sumar el tiempo h, recibido como argumento, 
     *  y la duración de la operación (constante definida en la interfaz)
     *  @param h   int (horas)
     *  @return    Paciente 
     */
    public Paciente operarPaciente(int h) { 
        Paciente p = cP.recuperarMin();
        p.setIngresoEnQuirofano(h + ServidorQuirofano.TIEMPO_QUIROFANO);
        cP.eliminarMin();
		return p;
    }
     
}
