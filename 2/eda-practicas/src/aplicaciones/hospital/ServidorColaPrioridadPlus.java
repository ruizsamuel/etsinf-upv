package aplicaciones.hospital;

public class ServidorColaPrioridadPlus extends ServidorColaPrioridad implements ServidorQuirofanoPlus {

    private int talla;

    @Override
    public int numPacientes() {
        return talla;
    }

    @Override
    public Paciente transferirPaciente() {
        talla--;
        return cP.eliminarMin();
    }

    @Override
    public void insertarEnEspera(Paciente p) {
        super.insertarEnEspera(p);
        talla++;
    }

    @Override
    public Paciente operarPaciente(int h) {
        talla--;
        return super.operarPaciente(h);
    }

    @Override
    public void distribuirPacientes(ServidorQuirofanoPlus s) {
        Paciente[] pacientes = new Paciente[talla];
        for (int i = 0; !cP.esVacia(); i++) {
            pacientes[i] = cP.eliminarMin();
            talla--;
        }
        for (int i = 0; i < pacientes.length; i++) {
            if (i % 2 == 0) {
                this.insertarEnEspera(pacientes[i]);
            } else {
                s.insertarEnEspera(pacientes[i]);
            }
        }
    }
}
