import java.awt.*;
import javax.swing.*;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class MenuGui extends Container {

    public MenuGui() {
        setLayout(new FlowLayout());
        setBackground(new Color(10, 10, 10));
        add(krigsFilmButton());

    }

    public JButton krigsFilmButton(){
        JButton krigsFilm = new JButton("Krigs film");
        krigsFilm.setBackground(new Color(255,0,0,100));
        krigsFilm.setSize(25,25);
        return krigsFilm;
    }
}
