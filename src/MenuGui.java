import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.accessibility.AccessibleContext;
import javax.swing.*;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class MenuGui {

    KeyListener keyListener = new KeyListener() {
        public void keyPressed(KeyEvent keyEvent) {}

        public void keyReleased(KeyEvent keyEvent) {
            searching(KeyEvent.getKeyText(keyEvent.getKeyCode()));
        }

        public void keyTyped(KeyEvent keyEvent) {}

    };

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
        Container seriesArea = new Container();
        Container searchingArea = new Container();

        menuContainer.setLayout(new BorderLayout(20, 0));
        menuContainer.setBackground(new Color(10, 10, 10));
        menuContainer.add(menuArea, BorderLayout.CENTER);
        addLAndRSpacers(menuContainer);

        menuArea.setLayout(new GridLayout(1, 0, 10, 0));

        filmArea.setLayout(new BorderLayout());
        filmArea.add(movieButtonSetup(), BorderLayout.EAST);
        filmArea.add(warMovieButtonSetup(), BorderLayout.WEST);

        searchingArea.setLayout(new BorderLayout());
        searchingArea.add(searchingAreaSetup(), BorderLayout.CENTER);

        menuArea.add(filmArea);
        menuArea.add(seriesArea);
        menuArea.add(searchingArea);


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
    Denne metode står for det alle knapperne skal kunn/have i menuen
     */
    private JButton generalButtonSetup(String name) {
        JButton button = new JButton(name);
        button.setForeground(Color.white);
        button.setBackground(new Color(255, 0, 0));

        button.setMinimumSize(new Dimension(100, 100));
        button.setPreferredSize(new Dimension(100, 100));
        button.setMaximumSize(new Dimension(100, 100));

        return button;
    }

    /*
    Denne metode står for det alle valg metoderne skal kunne/have i menuen
     */
    private void generalGenreSelect(String genre) {
        JPanel filmPanel = (JPanel) externalContainer.getComponent(1);
        ScrollPane filmScrollPane = (ScrollPane) filmPanel.getComponent(0);
        filmScrollPane.removeAll();
        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Film film : films) {
            for (String gerne : film.getGenre()) {
                if (gerne.equals(" " + genre) || gerne.equals(genre) || genre.equals("")) {
                    filmBox.add(film);
                }
            }
        }
        filmScrollPane.add(filmBox);
    }

    /*
    Denne metode står for at lave war movie knappen
     */
    private JButton warMovieButtonSetup() {
        JButton warMovieButton = generalButtonSetup("Krigs film");
        warMovieButton.addActionListener(
                e -> warMovieSelect()
        );
        return warMovieButton;
    }

    private void warMovieSelect() {
        generalGenreSelect("War");

    }

    /*
    Denne metode står for at lave movie knappen
     */
    private JButton movieButtonSetup() {
        JButton movieButton = generalButtonSetup("Film");
        movieButton.addActionListener(
                e -> movieSelect()
        );
        return movieButton;
    }

    /*
    Denne metode står for at tegne alle filmne på media området
     */
    private void movieSelect() {
        generalGenreSelect("");
    }

    public Container getMenuContainer() {
        return menuContainer;
    }

    public Container searchingAreaSetup() {
        Container searchingSetup = new Container();
        searchingSetup.setBackground(Color.black);
        searchingSetup.setLayout(new BorderLayout());



        JPanel searchingTextArea = new JPanel();
        searchingTextArea.setLayout(new BorderLayout());

        searchingTextArea.setPreferredSize(new Dimension(100,25));

        JTextField searchingText = new JTextField("");

        searchingTextArea.add(searchingText, BorderLayout.CENTER);

        searchingText.addKeyListener(keyListener);

        searchingSetup.add(searchingTextArea, BorderLayout.EAST);
        return searchingSetup;
    }

    public void searching(String searchingText) {
        System.out.println(searchingText);
    }

}
