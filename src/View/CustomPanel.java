package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {

    public final String bs = "Business";

    public CustomPanel() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.white);

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width / 2, height / 2);

    }

    public JPanel wrapComponent(JComponent jc) {

        JPanel p = new JPanel();
        p.add(jc);
        return p;
    }

}