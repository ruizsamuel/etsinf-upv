package librerias.estructurasDeDatos.grafos;

import java.util.Arrays;
import librerias.estructurasDeDatos.jerarquicos.ForestUFSet;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.modelos.UFSet;

public class TestKruskal {
    private static final int NUMV = 7;

    public TestKruskal() {
    }

    public static void main(String[] args) {
        if (validar("Validando la clase Arista para Kruskal", testArista())) {
            if (validar("Validando Kruskal para un Grafo NO Conexo", testKruskalNoConexo())) {
                if (validar("Validando Kruskal para un Grafo Conexo", testKruskal())) {
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
        double sumaPesos = 0.0;
        ColaPrioridad<Arista> cP = new MonticuloBinario();
        Arista[] datos = new Arista[]{new Arista(0, 2, 12.5), new Arista(1, 3, 6.5), new Arista(2, 3, 4.3), new Arista(0, 3, 14.3), new Arista(0, 1, 6.2)};
        String a1 = datos[1].toString();
        String a1OK = "(1, 3, 6.5)";
        if (!a1.equals(a1OK)) {
            return "PORQUE el metodo toString() de tu clase Arista NO es correcto...\n  Comprueba primero que sobrescribe al de Object y, luego, que el\n  formato del String resultante es el especificado!!";
        } else {
            for(int i = 0; i < datos.length; ++i) {
                cP.insertar(datos[i]);
            }

            int[] orden = new int[]{2, 4, 1, 0, 3};
            int i = 0;

            while(i < datos.length) {
                Arista a = (Arista)cP.eliminarMin();
                int aOrigen = a.getOrigen();
                int aDestino = a.getDestino();
                double aPeso = a.getPeso();
                sumaPesos += aPeso;
                Arista s = datos[orden[i]];
                int sOrigen = s.getOrigen();
                int sDestino = s.getDestino();
                double sPeso = s.getPeso();
                if (aOrigen != aDestino && sOrigen != sDestino) {
                    if (aOrigen == sOrigen && aDestino == sDestino && aPeso == sPeso) {
                        ++i;
                        continue;
                    }

                    return "PORQUE las aristas no se comparan adecuadamente...\n  Comprueba el metodo compareTo de tu clase Arista!!";
                }

                return "PORQUE los vertices origen y destino de una arista NO pueden coincidir...\n  Comprueba los metodos getOrigen y getDestino de tu clase Arista!!";
            }

            if (Math.abs(sumaPesos - 43.8) >= 1.0E-8) {
                return "PORQUE los pesos de las aristas no son correctos...\n  Comprueba los metodos consultores de tu clase Arista!!";
            } else {
                return null;
            }
        }
    }

    public static String testKruskalNoConexo() {
        GrafoNoDirigido g = new GrafoNoDirigido(7);
        g.insertarArista(2, 3, 4.0);
        g.insertarArista(4, 5, 4.0);
        g.insertarArista(0, 1, 6.0);
        g.insertarArista(1, 3, 6.0);
        g.insertarArista(0, 2, 12.0);
        g.insertarArista(0, 3, 14.0);
        g.insertarArista(5, 6, 15.0);
        g.insertarArista(4, 6, 20.0);
        return g.kruskal() == null ? null : "Tu metodo kruskal NO devuelve null para un Grafo como, por ejemplo,\n" + g.toString() + "\n";
    }

    public static String testKruskal() {
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
        String traza = "Tu metodo NO obtiene el MST correcto para un Grafo como, por ejemplo,\n" + g.toString() + "\n";
        Arista[] mST = g.kruskal();
        boolean okNumA = mST != null && mST.length == 6;
        if (!okNumA) {
            return mST == null ? "PORQUE devuelves un conjunto de aristas NULL...\n  Comprueba las Aristas que insertas en la Cola de Prioridad!!" : "PORQUE devuelves un conjunto de " + mST.length + " aristas(!!), y debiera tener |V| - 1 = 6";
        } else {
            String comparativa = " Arista\t   de tu ST\t   del MST correcto\n";
            double coste = 0.0;

            int mstFailure;
            for(mstFailure = 0; mstFailure < mST.length; ++mstFailure) {
                coste += mST[mstFailure].getPeso();
            }

            mstFailure = isKruskal(g, mST);
            double diff = Math.abs(coste - 44.0);
            boolean okCoste = diff < 1.0E-8;
            if (mstFailure == -1) {
                return okCoste ? null : traza + "PORQUE su coste NO es el correcto...\n\n\tSi sus Aristas son:\n" + Arrays.toString(mST) + "\tsu coste deberia ser 44.0 PERO, sin embargo es " + coste + "\n";
            } else {
                Arista a = mST[mstFailure];
                return g.existeArista(a.getOrigen(), a.getDestino()) && g.pesoArista(a.getOrigen(), a.getDestino()) == a.getPeso() ? String.format("%sPORQUE su Arista #%d genera un ciclo:\n%s", traza, mstFailure, arr2str(mST)) : String.format("%sPORQUE no existe la Arista %s\n", traza, a);
            }
        }
    }

    protected static int isKruskal(Grafo g, Arista[] kruskal) {
        int vertices = kruskal.length + 1;
        UFSet cc = new ForestUFSet(vertices);

        for(int i = 0; i < kruskal.length; ++i) {
            Arista a = kruskal[i];
            int origen = a.getOrigen();
            int destino = a.getDestino();
            if (cc.find(origen) == cc.find(destino)) {
                return i;
            }

            if (!g.existeArista(origen, destino) || g.pesoArista(origen, destino) != a.getPeso()) {
                return i;
            }

            cc.union(cc.find(origen), cc.find(destino));
        }

        return -1;
    }

    protected static String arr2str(Object[] arr) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < arr.length; ++i) {
            builder.append(i).append('\t').append(arr[i]).append('\n');
        }

        return builder.toString();
    }
}