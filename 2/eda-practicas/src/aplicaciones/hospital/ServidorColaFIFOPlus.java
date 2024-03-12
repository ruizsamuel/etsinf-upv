package aplicaciones.hospital;

public class ServidorColaFIFOPlus extends ServidorColaFIFO implements ServidorQuirofanoPlus {
    private int talla = 0;

    public ServidorColaFIFOPlus() {
    }

    public void insertarEnEspera(Paciente p) {
        super.insertarEnEspera(p);
        ++this.talla;
    }

    public Paciente operarPaciente(int h) {
        --this.talla;
        return super.operarPaciente(h);
    }

    public Paciente transferirPaciente() {
        --this.talla;
        return (Paciente)this.c.desencolar();
    }

    public int numPacientes() {
        return this.talla;
    }

    public void distribuirPacientes(ServidorQuirofanoPlus s) {
        Paciente[] ap = new Paciente[this.numPacientes()];

        int i;
        for(i = 0; i < ap.length; ++i) {
            ap[i] = this.transferirPaciente();
        }

        for(i = 0; i < ap.length - 1; i += 2) {
            this.insertarEnEspera(ap[i]);
            s.insertarEnEspera(ap[i + 1]);
        }

    }
}
