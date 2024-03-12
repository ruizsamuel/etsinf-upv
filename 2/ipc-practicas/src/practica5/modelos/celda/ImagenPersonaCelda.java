package practica5.modelos.celda;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import practica5.modelos.Persona;

public class ImagenPersonaCelda extends TableCell<Persona, String> {
    private final ImageView view = new ImageView();
    @Override
    protected void updateItem(String t, boolean bln) {
        super.updateItem(t, bln);
        setText(null);
        if (t == null || bln) {
            setGraphic(null);
        } else {
            Image imagen = new Image(t, 25, 25, true, true);
            view.setImage(imagen);
            setGraphic(view);
        }
    }
}
