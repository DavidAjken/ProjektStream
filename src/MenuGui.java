import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class MenuGui {

    Container menuContainer;
    Container externalContainer;
    ArrayList<Film> films;

    public MenuGui(Container externalContainer, ArrayList<Film> films) {
        this.externalContainer = externalContainer;
        this.films = films;
        menuGuiSetup();
    }

    private void menuGuiSetup() {
        menuContainer = new Container();
        Container menuArea = new Container();
        Container filmArea = new Container();
        Container seriseArea = new Container();

        menuContainer.setLayout(new BorderLayout(20, 0));
        menuContainer.setBackground(new Color(10, 10, 10));
        menuContainer.add(menuArea, BorderLayout.CENTER);
        addLAndRSpacers(menuContainer);

        menuArea.setLayout(new GridLayout(1,0,10,0));


        menuArea.add(movieButtonSetup());
        menuArea.add(warMovieButtonSetup());


    }


    /*
    Denne metode står for at give plads i siderne så knapperne ikke er lige op af kanterne
     */
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
        Dimension prefferdDimention = new Dimension(100, 100);
        spacer.setPreferredSize(prefferdDimention);
    }

    /*
    Denne metode står for at lave war movie knappen
     */
    private JButton warMovieButtonSetup() {
        JButton warMovieButton = new JButton("Krigs film");
        warMovieButton.setForeground(Color.white);
        warMovieButton.setBackground(new Color(255, 0, 0));

        warMovieButton.setMinimumSize(new Dimension(100, 100));
        warMovieButton.setPreferredSize(new Dimension(100, 100));
        warMovieButton.setMaximumSize(new Dimension(100, 100));

        warMovieButton.addActionListener(
                e -> warMovieSelect()
        );

        return warMovieButton;
    }

    private void warMovieSelect() {

        JPanel filmPanel = (JPanel) externalContainer.getComponent(1);
        ScrollPane filmScrollPane = (ScrollPane) filmPanel.getComponent(0);
        filmScrollPane.removeAll();
        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Film film : films) {
            for (String gerne : film.getGenre()) {
                if (gerne.equals(" War") || gerne.equals("War")) {
                    filmBox.add(film);
                }
            }
        }

        filmScrollPane.add(filmBox);

    }

    /*
    Denne metode står for at lave movie knappen
     */

    private JButton movieButtonSetup() {
        JButton movieButton = new JButton("Film");
        movieButton.setForeground(Color.white);
        movieButton.setBackground(new Color(255, 0, 0));

        movieButton.setMinimumSize(new Dimension(100, 100));
        movieButton.setPreferredSize(new Dimension(100, 100));
        movieButton.setMaximumSize(new Dimension(100, 100));

        movieButton.addActionListener(
                e -> movieSelect()
        );

        return movieButton;
    }

    private void movieSelect() {

        JPanel filmPanel = (JPanel) externalContainer.getComponent(1);
        ScrollPane filmScrollPane = (ScrollPane) filmPanel.getComponent(0);
        filmScrollPane.removeAll();
        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Film film : films) {
            filmBox.add(film);
        }

        filmScrollPane.add(filmBox);

    }

    public Container getMenuContainer() {
        return menuContainer;
    }
}
