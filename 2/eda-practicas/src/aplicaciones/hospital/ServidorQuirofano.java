package aplicaciones.hospital;

/** Interfaz ServidorDeQuirofano: 
 *  especifica la capacidad de servicio de un servidor de quirofano 
 *  y los metodos que este ha de implementar **independientemente**   
 *  de si usa una Cola o una Cola de Prioridad 
 *  para gestionar los pacientes en espera.
 *  
 *  @author  Profesores EDA
 *  @version Septiembre 2023
 */

public interface ServidorQuirofano {
    public static final int TIEMPO_QUIROFANO = 3; //  3 horas, tiempo de cualquier operacion
    
    /** Incluye un nuevo Paciente en espera, p, en un ServidorDeQuirofano.
     *  @param p   Paciente
     */
    void insertarEnEspera(Paciente p);
    
    /** Comprueba si hay algun Paciente en espera en un ServidorDeQuirofano.
     *  @return   boolean
     */
    boolean hayPacientes();
    
    /** SII hayPacientes(): 
     *  devuelve el Paciente de un ServidorDeQuirofano que va a ser operado.
     *  @return   Paciente
     */
    Paciente getPaciente();
    
    /** SII hayPacientes(): 
     *  elimina de un ServidorDeQuirofano el Paciente que va a ser operado 
     *  y devuelve el Paciente actualizando valor de ingresoEnQuirofano  
     *  @param h   int (horas)
     *  @return    Paciente 
     */
    Paciente operarPaciente(int h);
}
