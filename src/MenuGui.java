import java.awt.*;
import javax.swing.*;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class MenuGui extends Container {

    public MenuGui() {
        Container menuArea = new Container();
        menuArea.setLayout(new BoxLayout(menuArea, BoxLayout.X_AXIS));
        setLayout(new BorderLayout(20,0));
        setBackground(new Color(10, 10, 10));
        menuArea.add(krigsFilmButton());
        addLAndRSpacers(this);
        add(menuArea, BorderLayout.CENTER);
    }

    private JButton krigsFilmButton() {
        JButton krigsFilm = new JButton("Krigs film");
        krigsFilm.setForeground(Color.white);
        krigsFilm.setBackground(new Color(255, 0, 0));

        krigsFilm.setMinimumSize(new Dimension(100,100));
        krigsFilm.setPreferredSize(new Dimension(100,100));
        krigsFilm.setMaximumSize(new Dimension(100,100));

        return krigsFilm;
    }

    private void addLAndRSpacers(Container container) {
        JPanel leftSpacer = new JPanel();
        JPanel rightSpacer = new JPanel();
        setupSpacer(leftSpacer);
        setupSpacer(rightSpacer);

        container.add(leftSpacer, BorderLayout.WEST);
        container.add(rightSpacer, BorderLayout.EAST);
    }

    private void setupSpacer(JPanel spacer) {
        spacer.setBackground(new Color(5, 5, 255));
        Dimension prefferdDimention = new Dimension(100,100);
        spacer.setPreferredSize(prefferdDimention);
    }

    public void krigsFilm(Container container){


        container.update(new Container());


    }
}
