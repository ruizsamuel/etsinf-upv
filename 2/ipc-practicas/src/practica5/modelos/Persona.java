package practica5.modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {

    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty apellidos = new SimpleStringProperty();
    private final String rutaImagen;

    public Persona(String nombre, String apellidos, String imagen) {
        this.nombre.setValue(nombre);
        this.apellidos.setValue(apellidos);
        rutaImagen = imagen;
    }

    public final StringProperty NombreProperty() {
        return this.nombre;
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public final StringProperty ApellidosProperty() {
        return this.apellidos;
    }

    public final String getApellidos() {
        return this.apellidos.get();
    }

    public final String getRutaImagen() { return rutaImagen; }
}
