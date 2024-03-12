package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.modelos.Cola;

public class TestGrafo {
    private static final int NUMV = 7;

    public TestGrafo() {
    }

    public static void main(String[] args) {
        if (validar("Validando la clase Arista", testArista())) {
            if (validar("Validando Spanning Tree para un Grafo NO Conexo", testSTNoConexo())) {
                if (validar("Validando Spanning Tree para un Grafo Conexo", testSTConexo())) {
                    ;
                }
            }
        }
    }

    public static boolean validar(String test, String res) {
        System.out.print(test + "... ");
        if (res == null) {
            System.out.println("OK!\n");
            return true;
        } else {
            System.out.println("ERROR");
            System.out.println("* " + res);
            return false;
        }
    }

    public static String testArista() {
        Arista prueba = new Arista(0, 2, 12.5);
        if (prueba.origen == 0 && prueba.destino == 2 && prueba.peso == 12.5) {
            String sPrueba = prueba.toString();
            String sPruebaOK = "(0, 2, 12.5)";
            if (!sPrueba.equals(sPruebaOK)) {
                return "PORQUE el metodo toString() de tu clase Arista NO es correcto...\n  Comprueba primero que sobrescribe al de Object y, luego, que el\n  formato del String resultante es el especificado!!";
            } else {
                double sumaPesos = 0.0;
                Cola<Arista> c = new ArrayCola();
                Arista[] datos = new Arista[]{new Arista(0, 2, 12.5), new Arista(1, 3, 6.5), new Arista(2, 3, 4.3), new Arista(0, 3, 14.3), new Arista(0, 1, 6.2)};

                for(int i = 0; i < datos.length; ++i) {
                    c.encolar(datos[i]);
                }

                int[] orden = new int[]{0, 1, 2, 3, 4};
                int i = 0;

                while(i < datos.length) {
                    Arista a = (Arista)c.desencolar();
                    sumaPesos += a.getPeso();
                    Arista s = datos[orden[i]];
                    if (a.getOrigen() != a.getDestino() && s.getOrigen() != s.getDestino()) {
                        if (a.getOrigen() == s.getOrigen() && a.getDestino() == s.getDestino() && a.getPeso() == s.getPeso()) {
                            ++i;
                            continue;
                        }

                        return "PORQUE las componentes de tus aristas no son correctas...\n  Comprueba los metodos consultores de tu clase Arista!!";
                    }

                    return "PORQUE los vertices origen y destino de una arista NO pueden coincidir...\n  Comprueba los metodos getOrigen y getDestino de tu clase Arista!!";
                }

                if (Math.abs(sumaPesos - 43.8) >= 1.0E-8) {
                    return "PORQUE los pesos de las aristas no son correctos...\n  Comprueba los metodos consultores de tu clase Arista!!";
                } else {
                    return null;
                }
            }
        } else {
            return "PORQUE el metodo constructor de tu clase Arista NO es correcto...\n  Comprueba que la declaracion de los atributos de la clase y\n  su incializacion en el constructor sean las especificadas!!";
        }
    }

    public static String testSTNoConexo() {
        GrafoNoDirigido g = new GrafoNoDirigido(7);
        g.insertarArista(2, 3, 4.0);
        g.insertarArista(4, 5, 4.0);
        g.insertarArista(0, 1, 6.0);
        g.insertarArista(1, 3, 6.0);
        g.insertarArista(0, 2, 12.0);
        g.insertarArista(0, 3, 14.0);
        g.insertarArista(5, 6, 15.0);
        g.insertarArista(4, 6, 20.0);
        return g.arbolRecubrimientoBFS() == null ? null : "Tu metodo NO devuelve null para un Grafo como, por ejemplo,\n" + g.toString() + "\n";
    }

    private static String testSTConexo() {
        GrafoNoDirigido g = new GrafoNoDirigido(7);
        g.insertarArista(2, 3, 4.0);
        g.insertarArista(4, 5, 4.0);
        g.insertarArista(0, 1, 6.0);
        g.insertarArista(1, 3, 6.0);
        g.insertarArista(3, 4, 9.0);
        g.insertarArista(0, 2, 12.0);
        g.insertarArista(2, 4, 12.0);
        g.insertarArista(3, 5, 12.0);
        g.insertarArista(0, 3, 14.0);
        g.insertarArista(5, 6, 15.0);
        g.insertarArista(1, 5, 20.0);
        g.insertarArista(4, 6, 20.0);
        String traza = "Tu metodo NO obtiene el ST correcto para un Grafo como, por ejemplo,\n" + g.toString() + "\n";
        Arista[] sol = new Arista[]{new Arista(0, 1, 6.0), new Arista(0, 2, 12.0), new Arista(0, 3, 14.0), new Arista(1, 5, 20.0), new Arista(2, 4, 12.0), new Arista(5, 6, 15.0)};
        Arista[] sT = g.arbolRecubrimientoBFS();
        boolean okNumA = sT != null && sT.length == 6;
        if (okNumA) {
            String comparativa = " Arista\t   de tu ST\t   del ST correcto\n";
            int numAristasOK = 0;

            for(int i = 0; i < sT.length; ++i) {
                Arista a = sT[i];
                Arista s = sol[i];
                if (a.getOrigen() == s.getOrigen() && a.getDestino() == s.getDestino() && a.getPeso() == s.getPeso()) {
                    ++numAristasOK;
                }

                comparativa = comparativa + "   " + i + "\t   " + a.toString() + "\t   " + s.toString() + "\n";
            }

            if (numAristasOK == 6) {
                return null;
            } else {
                return traza + "PORQUE algunas de sus Aristas NO son correctas:\n" + comparativa + "\n";
            }
        } else {
            return sT == null ? "PORQUE devuelves un conjunto de aristas NULL!!" : "PORQUE devuelves un conjunto de " + sT.length + " aristas(!!), " + "y debiera tener |V| - 1 = " + 6;
        }
    }
}