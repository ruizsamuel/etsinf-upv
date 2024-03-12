package aplicaciones.hospital;

import java.util.Scanner;

public class SimuladorServidoresQuirofanoPlus extends SimuladorServidoresQuirofano {
    public SimuladorServidoresQuirofanoPlus() {
    }

    public static void mainPlus() {
        System.out.println("Especifique el Número de pacientes (un entero > 250):");
        Scanner sc = new Scanner(System.in);
        numeroPacientes = sc.nextInt();
        if (testPaciente()) {
            if (testServidorQuirofanoPlus()) {
                Paciente[] tsEnEspera = generarPacientes(numeroPacientes);
                double tMS = simularQuirofano(tsEnEspera, new ServidorColaFIFO());
                mostrarResultados("COLA", tMS);
                System.out.println();
                tMS = simularQuirofano(tsEnEspera, new ServidorColaPrioridad());
                mostrarResultados("COLA DE PRIORIDAD", tMS);
                System.out.println();
                tMS = simularQuirofano(tsEnEspera, new ServidorColaFIFOPlus());
                mostrarResultados("COLA PLUS", tMS);
                System.out.println();
                tMS = simularQuirofano(tsEnEspera, new ServidorColaPrioridadPlus());
                mostrarResultados("COLA DE PRIORIDAD PLUS", tMS);
            }
        }
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
            if (s instanceof ServidorQuirofanoPlus s1) {
                if (s1.numPacientes() > 200) {
                    System.out.println(s1.numPacientes() + " pacientes, abriendo nuevo quirofano");
                    return abrirNuevoServidorQuirofano(s1, hora, tsEnEspera.length);
                }
            }
        }

        return tiempoMedioEspera / (double)tsEnEspera.length;
    }

    private static double abrirNuevoServidorQuirofano(ServidorQuirofanoPlus s1, int hora, int n) {
        Object s2;
        if (s1 instanceof ServidorColaPrioridadPlus) {
            s2 = new ServidorColaPrioridadPlus();
        } else {
            s2 = new ServidorColaFIFOPlus();
        }

        s1.distribuirPacientes((ServidorQuirofanoPlus)s2);

        while(s1.hayPacientes() || ((ServidorQuirofanoPlus)s2).hayPacientes()) {
            hora += operarPaciente(s1, hora);
            hora -= 3;
            hora += operarPaciente((ServidorQuirofano)s2, hora);
        }

        return tiempoMedioEspera / (double)n;
    }

    protected static int operarPaciente(ServidorQuirofano s, int h) {
        return !s.hayPacientes() ? 0 : SimuladorServidoresQuirofano.operarPaciente(s, h);
    }

    protected static boolean testServidorQuirofanoPlus() {
        try {
            Paciente[] ap = new Paciente[]{new Paciente("X", 5, 0), new Paciente("X", 8, 1), new Paciente("X", 2, 2), new Paciente("X", 7, 1), new Paciente("X", 3, 2), new Paciente("X", 1, 0), new Paciente("X", 9, 1), new Paciente("X", 7, 2)};
            int[] pos = new int[]{5, 2, 4, 0, 3, 7, 1, 6};
            int[] temp = new int[]{3, 1, 1, 3, 2, 1, 2, 2};
            ServidorQuirofanoPlus s = new ServidorColaPrioridadPlus();
            if (s.hayPacientes()) {
                throw new Exception("Error en el metodo hayPacientes");
            } else {
                int i;
                for(i = 0; i < ap.length; ++i) {
                    s.insertarEnEspera(ap[pos[i]]);
                }

                if (!s.hayPacientes()) {
                    throw new Exception("Error en el metodo hayPacientes");
                } else if (s.numPacientes() != 8) {
                    throw new Exception("Error en el metodo numPacientes");
                } else {
                    for(i = 0; i < ap.length; ++i) {
                        if (s.getPaciente() != ap[pos[i]]) {
                            throw new Exception("Error en el metodo getPaciente");
                        }

                        Paciente p = s.operarPaciente(0);
                        if (p.getDemora() != temp[i]) {
                            throw new Exception("Error en el metodo operarPaciente");
                        }

                        if (i == 4 && s.numPacientes() != 3) {
                            throw new Exception("Error en el metodo numPacientes");
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
}
