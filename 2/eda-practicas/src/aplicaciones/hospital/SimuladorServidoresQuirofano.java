package aplicaciones.hospital;

import java.text.DecimalFormat;
import java.util.Random;

public class SimuladorServidoresQuirofano {
    protected static double tiempoMedioEspera;
    protected static int ESCALA_GRAVEDAD = 10;
    protected static double[] tiempoEsperaArray;
    protected static double[] tiempoMedioEsperaArray;
    protected static int[] pacientesArray;
    protected static int numeroPacientes;
    protected static String verbose;

    public SimuladorServidoresQuirofano() {
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            numeroPacientes = Integer.parseInt(args[0]);
            verbose = args[1];
        } else if (args.length != 0) {
            System.out.println("Especifique los valores de 2 argumentos:");
            System.out.println("- Número de pacientes (un entero positivo)");
            System.out.println("- Activar modo verbose(valores: SI, NO)");
            return;
        }

        if (testPaciente()) {
            if (testServidorQuirofano()) {
                Paciente[] tsEnEspera = generarPacientes(numeroPacientes);
                double tMS = simularQuirofano(tsEnEspera, new ServidorColaFIFO());
                mostrarResultados("COLA", tMS);
                System.out.println();
                tMS = simularQuirofano(tsEnEspera, new ServidorColaPrioridad());
                mostrarResultados("COLA DE PRIORIDAD", tMS);
            }
        }
    }

    protected static void mostrarResultados(String tipo, double tMS) {
        System.out.println("\n*** SIMULACION CON " + tipo + ": Numero de pacientes = " + numeroPacientes + " ; Tiempo medio de espera = " + doubleToString(tMS / 24.0) + " DIAS.");

        for(int g = 0; g < ESCALA_GRAVEDAD; ++g) {
            tiempoMedioEsperaArray[g] = tiempoEsperaArray[g] / (double)pacientesArray[g];
            System.out.println("Tiempo medio de espera, pacientes gravedad " + g + " = " + doubleToString(tiempoMedioEsperaArray[g] / 24.0) + " DIAS.");
        }

    }

    protected static Paciente[] generarPacientes(int n) {
        Paciente[] res = new Paciente[n];
        Random rnd = new Random();
        int tiempo = 0;

        for(int i = 0; i < res.length; ++i) {
            int gravedad = rnd.nextInt(10);
            tiempo += rnd.nextInt(1);
            res[i] = new Paciente("cirugia", gravedad, tiempo);
        }

        return res;
    }

    private static double simularQuirofano(Paciente[] tsEnEspera, ServidorQuirofano s) {
        int hora = 0;
        int i = 0;
        tiempoMedioEspera = 0.0;

        for(int g = 0; g < ESCALA_GRAVEDAD; ++g) {
            tiempoEsperaArray[g] = 0.0;
            pacientesArray[g] = 0;
        }

        while(i < tsEnEspera.length) {
            if (s.hayPacientes()) {
                hora += operarPaciente(s, hora);
            } else {
                hora = tsEnEspera[i].getIngresoEnEspera();
            }

            while(i < tsEnEspera.length && tsEnEspera[i].getIngresoEnEspera() <= hora) {
                s.insertarEnEspera(tsEnEspera[i]);
                ++i;
            }
        }

        while(s.hayPacientes()) {
            hora += operarPaciente(s, hora);
        }

        return tiempoMedioEspera / (double)tsEnEspera.length;
    }

    protected static int operarPaciente(ServidorQuirofano s, int h) {
        Paciente p = s.getPaciente();
        int g = p.getGravedad();
        s.operarPaciente(h);
        int horaFin = h + 3;
        int espera = h - p.getIngresoEnEspera();
        if (verbose.equals("SI")) {
            System.out.println("[" + horaFin / 24 + "] " + p.toString());
        }

        double[] var10000 = tiempoEsperaArray;
        var10000[g] += (double)espera;
        int var10002 = pacientesArray[g]++;
        tiempoMedioEspera += (double)espera;
        return 3;
    }

    protected static String doubleToString(double n) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(n);
    }

    protected static boolean testPaciente() {
        try {
            Paciente d1 = new Paciente("X", 1, 0);
            Paciente d2 = new Paciente("X", 5, 1);
            Paciente d3 = new Paciente("X", 1, 2);
            if (d1.compareTo(d2) <= 0 && d1.compareTo(d3) == 0 && d2.compareTo(d3) >= 0) {
                return true;
            } else {
                throw new Exception("Error en el metodo compareTo");
            }
        } catch (Exception var3) {
            System.out.println("Error en la clase Paciente: " + var3.getMessage());
            return false;
        }
    }

    protected static boolean testServidorQuirofano() {
        try {
            Paciente[] ap = new Paciente[]{new Paciente("X", 5, 0), new Paciente("X", 8, 1), new Paciente("X", 2, 2), new Paciente("X", 7, 1), new Paciente("X", 3, 2), new Paciente("X", 1, 0), new Paciente("X", 9, 1), new Paciente("X", 7, 2)};
            int[] pos = new int[]{5, 2, 4, 0, 3, 7, 1, 6};
            int[] temp = new int[]{3, 1, 1, 3, 2, 1, 2, 2};
            ServidorQuirofano s = new ServidorColaPrioridad();
            if (s.hayPacientes()) {
                throw new Exception("Error en el metodo hayPacientes");
            } else {
                int i;
                for(i = 0; i < ap.length; ++i) {
                    s.insertarEnEspera(ap[pos[i]]);
                }

                if (!s.hayPacientes()) {
                    throw new Exception("Error en el metodo hayPacientes");
                } else {
                    for(i = 0; i < ap.length; ++i) {
                        if (s.getPaciente() != ap[pos[i]]) {
                            throw new Exception("Error en el metodo getPaciente");
                        }

                        Paciente p = s.operarPaciente(0);
                        if (p.getDemora() != temp[i]) {
                            throw new Exception("Error en el metodo operarPaciente");
                        }
                    }

                    return true;
                }
            }
        } catch (Exception var6) {
            System.out.println("Error en la clase ServidorColaPrioridad: " + var6.getMessage());
            return false;
        }
    }

    static {
        tiempoEsperaArray = new double[ESCALA_GRAVEDAD];
        tiempoMedioEsperaArray = new double[ESCALA_GRAVEDAD];
        pacientesArray = new int[ESCALA_GRAVEDAD];
        numeroPacientes = 100;
        verbose = "NO";
    }
}
