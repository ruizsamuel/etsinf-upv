package pract2;

/**
 * Clase programa para la Actividad 8.
 * También se puede hacer una ejecución parecida utilizando
 * el <B>workbench</B> del BlueJ.
 * @author Samuel Ruiz 
 * @version Práctica 2 - Curso 2021/22
 */
public class Programa {
    /** No hay objetos de esta clase. */
    private Programa() { }
    
    public static void main(String[] args) {        
        
        Blackboard miPizarra = new Blackboard("Actividad 8", 500, 300);
                
        Circle c = new Circle(50, "blue", 300, 100);        
        miPizarra.add(c);
        
        Circle c2 = new Circle(35, "orange", 200, 150);        
        miPizarra.add(c2);
        
        Rectangle r = new Rectangle(100, 10, "red", 50, 155);
        miPizarra.add(r);  
        
        TrIsosceles t = new TrIsosceles(30, 60, "black", 210, 60 );
        miPizarra.add(t);          
    }
}