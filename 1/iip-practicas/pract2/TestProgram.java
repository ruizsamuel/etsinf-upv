package pract2;

/**
 * Clase programa para probar todas las figuras definidas.
 * También se puede hacer una ejecución parecida utilizando
 * el <B>workbench</B> del BlueJ.
 * @author IIP 
 * @version Práctica 2 - Curso 2021/22
 */
public class TestProgram {
    /** No hay objetos de esta clase. */
    private TestProgram() { }
    
    public static void main(String[] args) {        
        // Inicializar el lugar de dibujo, dándole
        // un nombre y dimensiones:
        Blackboard miPizarra = new Blackboard("UNA VENTANA AL MUNDO", 500, 300);
                
        // Inicializar un círculo amarillo:
        Circle c = new Circle(50, "yellow", 100, 100);        
        // ponerlo en el espacio de dibujo:
        miPizarra.add(c);
        // mostrar por pantalla el perímetro del círculo:
        System.out.println("El perímetro del círculo es " + c.perimeter());
     
        // Inicializar un rectángulo largo y rojo:
        Rectangle r = new Rectangle(100, 10, "red", 50, 155);
        // ponerlo en el espacio de dibujo:
        miPizarra.add(r);  
        
        // Inicializar un triángulo isósceles negro:
        TrIsosceles t = new TrIsosceles(30, 60, "black", 210, 60 );
        // ponerlo en el espacio de dibujo:
        miPizarra.add(t);          
    }
}