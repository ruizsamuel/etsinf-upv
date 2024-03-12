package practica2;

import javax.swing.*;
import java.awt.*;

class StateRenderer extends JLabel implements ListCellRenderer<State> {
    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
    public StateRenderer() {setOpaque(true);} 
    public Component getListCellRendererComponent(
            JList<? extends State> list, State value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString()); setFont(new Font("Courier",Font.BOLD,16));
        setBackground(isSelected?HIGHLIGHT_COLOR:Color.white);
        setForeground(isSelected?Color.white: Color.black); 
        return this;
    }
}

