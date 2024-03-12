package aplicaciones.censo;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class TestCenso {
    private static final int TALLA = 30;

    public TestCenso() {
    }

    public static void listar() {
        System.out.println("EJEMPLO. ListaElectores. 30 ELECTORES. SIN ORDENAR.");
        System.out.println("---------------------------------------------------");
        ListaElectores a = new ListaElectores(false, TALLA);
        System.out.println(a);
        System.out.println("---------------------------------------------------\n");
        System.out.println("EJEMPLO. ListaElectores. 30 ELECTORES. LISTA ORDENADA.");
        System.out.println("---------------------------------------------------");
        ListaElectores b = new ListaElectores(true, TALLA);
        System.out.println(b);
        System.out.println("---------------------------------------------------\n");
    }

    public static void comprobar() {
        boolean ok = mostrar(testListaOrdenada()) && mostrar(testHabitante()) && mostrar(testIndice()) && mostrar(testListaElectores());
        if (ok) {
            System.out.println("*** CORRECTO ***");
        } else {
            System.out.println("*** SE ENCONTRARON ERRORES ***");
        }

    }

    public static void listarCP() {
        ListaElectores a = new ListaElectores(true, TALLA * 10);
        System.out.println("EJEMPLO. ListaElectores. " + a.getTalla() + " ELECTORES. LISTA ORDENADA.");
        System.out.println("---------------------------------------------------");
        System.out.println(a);
        System.out.println("---------------------------------------------------\n");
        System.out.println("Escriba el rango de códigos postales de la ListaElectores a generar.");
        System.out.println("Escriba el código postal inferior o mínimo:");
        Scanner sc = new Scanner(System.in);
        int cp1 = sc.nextInt();
        System.out.println("Escriba el código postal superior o máximo:");
        int cp2 = sc.nextInt();
        ListaElectores b = a.getCensoCP(cp1, cp2);
        System.out.println("EJEMPLO. ListaElectores. " + b.getTalla() + " ELECTORES EN CÓDIGOS POSTALES [" + cp1 + " .. " + cp2 + "]:");
        System.out.println("---------------------------------------------------");
        System.out.println(b);
        System.out.println("---------------------------------------------------\n");
    }

    public static void buscar() {
        ListaElectores a = new ListaElectores(true, TALLA * 10);
        System.out.println("EJEMPLO. ListaElectores. " + a.getTalla() + " ELECTORES. LISTA ORDENADA.");
        System.out.println("---------------------------------------------------");
        System.out.println(a);
        System.out.println("---------------------------------------------------\n");
        System.out.println("Escriba el prefijo de los apellidos de la ListaElectores a generar:");
        Scanner sc = new Scanner(System.in);
        String prefijo = sc.next().toUpperCase();
        ListaElectores b = a.buscador(prefijo);
        PrintStream var10000 = System.out;
        int var10001 = b.getTalla();
        var10000.println("EJEMPLO. ListaElectores. " + var10001 + " ELECTORES CON APELLIDOS QUE EMPIEZAN CON " + prefijo);
        System.out.println("---------------------------------------------------");
        System.out.println(b);
        System.out.println("---------------------------------------------------\n");
    }

    private static boolean mostrar(boolean ok) {
        if (ok) {
            System.out.println("\tCorrecto");
        } else {
            System.out.println("\tError");
        }

        return ok;
    }

    private static boolean testListaOrdenada() {
        System.out.println("Comprobando la clase LEGListaConPIOrdenada...");
        ListaConPI<Integer> lista = new LEGListaConPIOrdenada<>();
        ArrayList<Integer> v = new ArrayList<>();
        Random r = new Random();

        int i;
        for(i = 1; i <= TALLA * 100; ++i) {
            int n = r.nextInt();
            v.add(n);
            lista.insertar(n);
        }

        Collections.sort(v);
        if (lista.talla() != v.size()) {
            return false;
        } else {
            i = 0;
            lista.inicio();

            while(!lista.esFin()) {
                if (!v.get(i).equals(lista.recuperar())) {
                    return false;
                }

                lista.siguiente();
                ++i;
            }

            return true;
        }
    }

    private static boolean testHabitante() {
        System.out.println("Comprobando la clase Habitante...");

        for(int i = 1; i <= TALLA; ++i) {
            Habitante a = new Habitante();
            Habitante b = new Habitante();
            if (!b.equals(b)) {
                return false;
            }

            if (b.compareTo(b) != 0) {
                return false;
            }

            if (a.toString().equals(b.toString())) {
                if (!a.equals(b)) {
                    return false;
                }

                if (a.compareTo(b) != 0) {
                    return false;
                }
            } else {
                if (a.equals(b)) {
                    return false;
                }

                if (a.toString().compareTo(b.toString()) < 0) {
                    if (a.compareTo(b) >= 0) {
                        return false;
                    }
                } else if (a.compareTo(b) <= 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean testIndice() {
        System.out.println("Comprobando el método indice de la clase ListaElectores... ");
        return testIndice(false) && testIndice(true);
    }

    private static boolean testIndice(boolean ordenada) {
        ListaElectores a = new ListaElectores(ordenada, 1000);
        ArrayList<Habitante> c = obtenerCenso(a);

        try {
            Iterator<Habitante> var3 = c.iterator();

            Habitante h;
            int pos;
            do {
                if (!var3.hasNext()) {
                    for(int i = 0; i <= TALLA; ++i) {
                        h = new Habitante();
                        pos = a.indice(h);
                        if (pos == -1) {
                            if (c.contains(h)) {
                                return false;
                            }
                        } else if (pos != c.indexOf(h)) {
                            return false;
                        }
                    }

                    return true;
                }

                h = var3.next();
                pos = a.indice(h);
                if (pos == -1) {
                    return false;
                }
            } while(pos == c.indexOf(h));

            return false;
        } catch (Exception var6) {
            return false;
        }
    }

    private static boolean testListaElectores() {
        System.out.println("Comprobando el constructor de la clase ListaElectores... ");
        return testListaElectores(false) && testListaElectores(true);
    }

    private static boolean testListaElectores(boolean ordenada) {
        int size = 1000;

        for(int i = 0; i <= 30; ++i) {
            ListaElectores a = new ListaElectores(ordenada, size);
            ArrayList<Habitante> c = obtenerCenso(a);
            if (c.size() != size) {
                return false;
            }

            ArrayList<String> lista = new ArrayList<>();
            Habitante prev = new Habitante("", "", "", "", 0);

            for(int j = 0; j < size; ++j) {
                Habitante h = c.get(j);
                String dni = h.getDni();
                if (lista.contains(dni)) {
                    return false;
                }

                lista.add(dni);
                if (ordenada && h.compareTo(prev) < 0) {
                    return false;
                }

                prev = h;
            }
        }
        return true;
    }

    private static ArrayList<Habitante> obtenerCenso(ListaElectores a) {
        ArrayList<Habitante> c = new ArrayList<>();
        a.getCenso().inicio();

        for(int i = 0; i < a.getCenso().talla(); ++i) {
            c.add(a.getCenso().recuperar());
            a.getCenso().siguiente();
        }

        return c;
    }

    /**
     * Método main para ejecutar los métodos de prueba
     * @param args argumentos programa
     */
    public static void main(String[] args) {
        System.out.println("Ejecución del método listar()");
        listar();
        System.out.println("Ejecución del método comprobar()");
        comprobar();
        System.out.println("Ejecución del método getCensoCP()");
        listarCP();
        System.out.println("Ejecución del método buscador()");
        buscar();
    }

}
