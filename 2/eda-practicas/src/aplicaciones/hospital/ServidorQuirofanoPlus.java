package aplicaciones.hospital;

/** Interfaz ServidorDeQuirofanoPlus: 
 *  ...
 *  
 *  @author  Profesores EDA
 *  @version Septiembre 2023
 */

public interface ServidorQuirofanoPlus extends ServidorQuirofano {
    
    int numPacientes();
    
    Paciente transferirPaciente();
    
    void distribuirPacientes(ServidorQuirofanoPlus s);
    
}
