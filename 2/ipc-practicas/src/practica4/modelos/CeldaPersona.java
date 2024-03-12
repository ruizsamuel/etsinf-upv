package practica4.modelos;

import javafx.scene.control.ListCell;

public class CeldaPersona extends ListCell<Persona> {
    @Override
    protected void updateItem(Persona t, boolean bln) {
        super.updateItem(t, bln);
        if (bln || t == null) {
            setText(null);
        } else {
            setText(t.getApellidos() + ", " + t.getNombre());
        }
    }
}