package tema6.ejercicio1;

public class Test {

    /**
     * Método main para pruebas
     */
    public static void main(String[] args) {

        // EJERCICIO 1.3

        GrafoDirigidoEj1 dir1 = new GrafoDirigidoEj1(4);
        dir1.insertarArista(0,1, 1); dir1.insertarArista(1,1, 2); dir1.insertarArista(1,2, 3);
        dir1.insertarArista(1,3, 7); dir1.insertarArista(2,0, 5); dir1.insertarArista(2,3, 2);
        dir1.insertarArista(3,2, 2);

        GrafoDirigidoEj1 dir2 = new GrafoDirigidoEj1(3);
        dir2.insertarArista(0,1); dir2.insertarArista(1,2); dir2.insertarArista(2,0);

        // Ejercicio 1.3 (a)
        System.out.println("* Ejercicio 1.3 (a)*");
        System.out.println(dir1.grado());
        // Ejercicio 1.3 (b)
        System.out.println("* Ejercicio 1.3 (b) *");
        System.out.println(dir1.aristaMayorPeso());
        // Ejercicio 1.3 (c)
        System.out.println("* Ejercicio 1.3 (c) *");
        System.out.println(dir1.esRegular());
        System.out.println(dir2.esRegular());

        // EJERCICIO 1.4

        // Ejercicio 1.4 (a)
        System.out.println("* Ejercicio 1.4 (a) *");
        dir2.insertarArista(2,1);
        System.out.println(dir1.getVerticeReceptivo());
        System.out.println(dir2.getVerticeReceptivo());
        // Ejercicio 1.4 (b)
        GrafoDirigidoEj1 sum1 = new GrafoDirigidoEj1(4);
        sum1.insertarArista(0,1); sum1.insertarArista(1,2); sum1.insertarArista(2,0);
        sum1.insertarArista(0,3); sum1.insertarArista(1,3);
        System.out.println("* Ejercicio 1.4 (b) *");
        System.out.println(sum1.esSumidero(3));
        System.out.println(sum1.esSumidero(2));
        // Ejercicio 1.4 (c)
        System.out.println("* Ejercicio 1.4 (c) *");
        System.out.println(sum1.getSumideroU());
        sum1.insertarArista(2,3);
        System.out.println(sum1.getSumideroU());
        // Ejercicio 1.4 (d)
        System.out.println("* Ejercicio 1.4 (d) *");
        GrafoDirigidoEj1 fuente1 = new GrafoDirigidoEj1(3);
        fuente1.insertarArista(0,1);
        System.out.println(fuente1.getFuenteU());
        fuente1.insertarArista(0, 2);
        System.out.println(fuente1.getFuenteU());
        // Ejercicio 1.4 (e)
        System.out.println("* Ejercicio 1.4 (e) *");
        GrafoDirigidoEj1 completo = new GrafoDirigidoEj1(3);
        completo.insertarArista(0,1); completo.insertarArista(1,0);
        completo.insertarArista(0,2); completo.insertarArista(2,0);
        System.out.println(completo.esCompleto());
        completo.insertarArista(1,2); completo.insertarArista(2,1);
        System.out.println(completo.esCompleto());
    }
}
