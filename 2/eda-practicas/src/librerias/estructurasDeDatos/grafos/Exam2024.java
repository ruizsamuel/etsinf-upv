package librerias.estructurasDeDatos.grafos;

public class Exam2024 {
    public static int cheapest(Grafo[] grafos) {
        int pos = -1;
        int n = grafos.length;
        Arista[][] arboles = new Arista[n][]; 
        
        for (int i = 0; i < grafos.length; i++) arboles[i] = grafos[i].kruskal();
        
        double min = Double.MAX_VALUE;
        for (int i = 0; i < arboles.length; i++) {
            double suma = 0;
            for (Arista a : arboles[i]) suma += a.peso;
            if (suma < min) {
                min = suma;
                pos = i;
            }
        }
        return pos;
    }
    
    public static void main(String[] args) {
        Grafo[] gs = new GrafoNoDirigido[3];        
        gs[0] = new GrafoNoDirigido(7);
        gs[0].insertarArista(2, 3, 4);  gs[0].insertarArista(4, 5, 4);  gs[0].insertarArista(0, 1, 6);
        gs[0].insertarArista(1, 3, 6);  gs[0].insertarArista(3, 4, 9);  gs[0].insertarArista(0, 2, 12);        
        gs[0].insertarArista(2, 4, 12); gs[0].insertarArista(3, 5, 12); gs[0].insertarArista(0, 3, 14);
        gs[0].insertarArista(5, 6, 15); gs[0].insertarArista(1, 5, 20); gs[0].insertarArista(4, 6, 20);         
        gs[1] = new GrafoNoDirigido(7);
        gs[1].insertarArista(2, 3, 4);  gs[1].insertarArista(4, 5, 4);  gs[1].insertarArista(0, 1, 6);
        gs[1].insertarArista(1, 3, 6);  gs[1].insertarArista(3, 4, 9);  gs[1].insertarArista(0, 2, 2);        
        gs[1].insertarArista(2, 4, 12); gs[1].insertarArista(3, 5, 2);  gs[1].insertarArista(0, 3, 14);
        gs[1].insertarArista(5, 6, 5);  gs[1].insertarArista(1, 5, 20); gs[1].insertarArista(4, 6, 20);         
        gs[2] = new GrafoNoDirigido(7);
        gs[2].insertarArista(2, 3, 4);  gs[2].insertarArista(4, 5, 4);  gs[2].insertarArista(0, 1, 6);
        gs[2].insertarArista(1, 3, 6);  gs[2].insertarArista(3, 4, 9);  gs[2].insertarArista(0, 2, 12);        
        gs[2].insertarArista(2, 4, 2);  gs[2].insertarArista(3, 5, 12); gs[2].insertarArista(0, 3, 14);
        gs[2].insertarArista(5, 6, 15); gs[2].insertarArista(1, 5, 2);  gs[2].insertarArista(4, 6, 20);         
        int res = cheapest(gs);
        System.out.println("cheapest is in position: " + res);
    }    
}
