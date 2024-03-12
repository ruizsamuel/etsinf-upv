package aplicaciones.hospital;

import aplicaciones.censo.Habitante;

/** Clase Paciente: representa un paciente a operar.
 *      
 *  @author  Profesores EDA
 *  @version Septiembre 2023
 */
 
public class Paciente implements Comparable<Paciente>{
    
    private Habitante id;
    
    /** tipo de cirugia
     *  ...
     */
    private String cirugia; 
    
    /** Indicador de la gravedad del paciente,
     *  i.e., de la urgencia de su cirugía
     *  rango de valores [0..9], mayor valor -> menos urgente
     */
    private int gravedad; 
    
    /** Tiempo (en número de horas) de ingreso del paciente
     *  en Lista de Espera (atributo: ingresoEnEspera)
     *  y en Quirofano (atributo: ingresoEnQuirofano)
     */
    private int ingresoEnEspera;
    private int ingresoEnQuirofano; 
    
    /** Crea un Paciente de cirugía c, gravedad g
     *  y enviado a "lista espera" en la hora h. 
     *  @param c   String
     *  @param g   int
     *  @param h   int (horas)
     */
    public Paciente(String c, int g, int h) {
        id = new Habitante();
        cirugia = c;
        gravedad = g;
        ingresoEnEspera = h;
    }
    
    /** Devuelve la cirugía de un Paciente.
     *  @return   String
     */
    public String getCirugia() { return cirugia; }
    
    /** Devuelve la gravedad de un Paciente.
     *  @return  int
     */
    public int getGravedad() { return gravedad; }
    
    /** Devuelve las horas de ingreso de un Paciente.
     *  @return   int (horas)
     */
    public int getIngresoEnEspera() { return ingresoEnEspera; }
    public int getIngresoEnQuirofano() { return ingresoEnQuirofano; }
    
    /** Devuelve el tiempo (en horas) que un Paciente está en la Lista de Espera
     *  @return   int (horas)
     */
    public int getDemora() { return ingresoEnQuirofano - ingresoEnEspera; }
    
    public void setIngresoEnQuirofano(int t) { ingresoEnQuirofano = t; }
        
    /** Devuelve el valor int que resulta de comparar la gravedad
     *  de un Paciente (this) con la de otro. Dicho valor será:
     *  
     *  ** NEGATIVO si un paciente (this) tiene MÁS gravedad que el otro,
     *     (MÁS gravedad: menor valor del atributo gravedad)
     *     i.e. si su cirugía es MÁS prioritaria que la del otro
     *     
     *  ** POSITIVO si un paciente (this) tiene MENOS gravedad que otro, 
     *     i.e. si su cirugía es MENOS prioritaria que la del otro
     *     
     *  ** CERO si ambos pacientes tienen la misma gravedad
     *  
     *  @param otro Paciente
     *  @return int resultado de la comparación de un Paciente (this) con otro
     */
    public int compareTo(Paciente otro) { 
        return this.gravedad - otro.gravedad;
    }
    
    /** Devuelve el String que representa un Paciente en un cierto formato texto.
     *  @return   String
     */
    public String toString() {
        return cirugia + " (gravedad: " + gravedad + ") "
            + ", \tEspera: " + ((ingresoEnQuirofano - ingresoEnEspera) / 24)
			+ " DIAS. \t " + id;
    }
}
