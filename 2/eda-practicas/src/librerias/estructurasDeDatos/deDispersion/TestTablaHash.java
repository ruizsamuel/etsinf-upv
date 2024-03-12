package librerias.estructurasDeDatos.deDispersion;

public class TestTablaHash {
    private TestTablaHash() {
    }

    public static void main(String[] args) {
        System.out.println();
        boolean desv = testDesvTipica();
        if (!desv) {
            System.out.println(":( desviacionTipica NO es correcto");
        }

        boolean costeM = testCosteMLocalizar();
        if (!costeM) {
            System.out.println(":( costeMLocalizar NO es correcto");
        }

        boolean rehash = testRehashing();
        if (!rehash) {
            System.out.println(":( rehashing NO es correcto");
        }

        if (desv && costeM && rehash) {
            System.out.println("\n:) Codigo de TablaHash correcto!");
        }

    }

    private static boolean testRehashing() {
        System.out.print("Test rehashing... ");
        boolean ok = true;
        String res = "";
        TablaHash<Integer, Integer> t = new TablaHash(10);
        int t1 = t.elArray.length;
        int nrH1 = 1 + (int)((double)t1 * 0.75);

        int t2;
        for(t2 = 0; t2 < nrH1 - 1; ++t2) {
            t.insertar((t2), 0);
        }

        t.insertar((29), 0);
        if (t.talla() != nrH1) {
            ok = false;
            res = res + "\n\tTalla incorrecta";
        } else {
            t2 = t.elArray.length;
            int t2Ref = nuevaCapacidad(t1);
            if (t2 != t2Ref) {
                ok = false;
                res = res + "\n\tCapacidad del nuevo array INcorrecta ";
            } else {
                for(int i = 0; i < nrH1 - 1 && ok; ++i) {
                    Integer oi = (Integer)t.recuperar((i));
                    if (oi == null) {
                        ok = false;
                        res = res + "\n\tNo se han copiado todos los datos";
                    }
                }

                if (ok) {
                    Integer oi = (Integer)t.recuperar((29));
                    if (oi == null) {
                        ok = false;
                        res = res + "\n\tNo se han copiado todos los datos";
                    }
                }
            }
        }

        if (res.equals("")) {
            res = "ok!";
        }

        System.out.println(res);
        return ok;
    }

    private static int nuevaCapacidad(int old) {
        return siguientePrimo(old * 2);
    }

    private static final int siguientePrimo(int n) {
        int nn = n;
        if (n % 2 == 0) {
            nn = n + 1;
        }

        while(!esPrimo(nn)) {
            nn += 2;
        }

        return nn;
    }

    private static final boolean esPrimo(int n) {
        for(int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean testDesvTipica() {
        System.out.print("Test desviacionTipica... ");
        TablaHash<String, Integer> th = new TablaHash(40);

        for(int i = 0; i < 40; ++i) {
            th.insertar("N" + i, i);
        }

        double tuDT = th.desviacionTipica();
        double miDT = miDesviacionTipica(th);
        boolean res = Math.abs(tuDT - miDT) < 1.0E-8;
        if (res) {
            System.out.println("ok!");
        }

        return res;
    }

    private static final double miDesviacionTipica(TablaHash t) {
        double lMedia = (double)t.talla / (double)t.elArray.length;
        double suma = 0.0;

        for(int i = 0; i < t.elArray.length; ++i) {
            double di = (double)t.elArray[i].talla() - lMedia;
            suma += di * di;
        }

        return Math.sqrt(suma / (double)t.elArray.length);
    }

    private static boolean testCosteMLocalizar() {
        System.out.print("Test costeMLocalizar... ");
        TablaHash<String, Integer> th = new TablaHash(40);

        for(int i = 0; i < 40; ++i) {
            th.insertar("N" + i, i);
        }

        double tuCML = th.costeMLocalizar();
        double miCML = miCosteMLocalizar(th);
        boolean res = Math.abs(tuCML - miCML) < 1.0E-8;
        if (res) {
            TablaHash<Integer, Integer> thI = new TablaHash(40);

            for(int i = 0; i < 10; ++i) {
                thI.insertar((i * 53), i);
            }

            tuCML = thI.costeMLocalizar();
            miCML = miCosteMLocalizar(thI);
            res = Math.abs(tuCML - miCML) < 1.0E-8;
        }

        if (res) {
            System.out.println("ok!");
        }

        return res;
    }

    private static double miCosteMLocalizar(TablaHash t) {
        double colisiones = 0.0;

        for(int i = 0; i < t.elArray.length; ++i) {
            colisiones += (double)(t.elArray[i].talla() * (t.elArray[i].talla() - 1) / 2);
        }

        return colisiones / (double)t.talla;
    }
}