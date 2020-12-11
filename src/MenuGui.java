import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.accessibility.AccessibleContext;
import javax.swing.*;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class MenuGui {

    Container menuContainer;
    Container externalContainer;
    ArrayList<Content> films;
    ArrayList<Content> series;

    public MenuGui(Container externalContainer, ArrayList<Content> films, ArrayList<Content> series) {
        this.externalContainer = externalContainer;
        this.films = films;
        this.series = series;
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

        menuArea.setLayout(new GridLayout(1, 0, 50, 0));

        filmArea.setLayout(new BorderLayout());
        filmArea.add(movieButtonSetup(), BorderLayout.WEST);
        filmArea.add(warMovieButtonSetup(), BorderLayout.EAST);

        seriesArea.setLayout(new BorderLayout());
        seriesArea.add(seriesButtonSetup(), BorderLayout.WEST);
        seriesArea.add(dramaSeriesButtonSetup(), BorderLayout.EAST);

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
        container.add(setupSpacer(300, 100), BorderLayout.WEST);
        container.add(setupSpacer(300, 100), BorderLayout.EAST);
    }

    private Container setupSpacer(int xSize, int ySize) {
        Container spacer = new Container();
        Dimension prefferdDimension = new Dimension(xSize, ySize);
        spacer.setPreferredSize(prefferdDimension);
        return spacer;
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

    /*   /*
    Denne metode står for det alle valg metoderne skal kunne/have i menuen for film
     */
    private void generalMovieGenreSelect(String genre) {
        JPanel mediaPanel = (JPanel) externalContainer.getComponent(1);
        ScrollPane mediaScrollPane = (ScrollPane) mediaPanel.getComponent(0);
        mediaScrollPane.removeAll();
        Container mediaBox = new Container();
        mediaBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Content film : films) {
            for (String gerne : film.getGenre()) {
                if (gerne.equals(" " + genre) || gerne.equals(genre) || genre.equals("")) {
                    mediaBox.add(film);
                }
            }
        }
        mediaScrollPane.add(mediaBox);
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
        generalMovieGenreSelect("War");
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
        generalMovieGenreSelect("");
    }


    /*
    Denne metode står for det alle valg metoderne skal kunne/have i menuen for film
     */
    private void generalSeriesGenreSelect(String genre) {
        JPanel mediaPanel = (JPanel) externalContainer.getComponent(1);
        ScrollPane mediaScrollPane = (ScrollPane) mediaPanel.getComponent(0);
        mediaScrollPane.removeAll();
        Container mediaBox = new Container();
        mediaBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Content serie : series) {
            for (String gerne : serie.getGenre()) {
                if (gerne.equals(" " + genre) || gerne.equals(genre) || genre.equals("")) {
                    mediaBox.add(serie);
                }
            }
        }
        mediaScrollPane.add(mediaBox);
    }

    /*
    Denne metode står for at lave war movie knappen
     */
    private JButton dramaSeriesButtonSetup() {
        JButton dramaSeriesButton = generalButtonSetup("Drama serier");
        dramaSeriesButton.addActionListener(
                e -> dramaSeriseSelect()
        );
        return dramaSeriesButton;
    }

    private void dramaSeriseSelect() {
        generalSeriesGenreSelect("Drama");
    }

    private JButton seriesButtonSetup() {
        JButton movieButton = generalButtonSetup("Serier");
        movieButton.addActionListener(
                e -> serierSelect()
        );
        return movieButton;
    }

    private void serierSelect() {
        generalSeriesGenreSelect("");
    }


    /*
    Denne metode står for at lave søgeområdet
     */
    public Container searchingAreaSetup() {
        Container searchingSetupContainer = new Container();
        searchingSetupContainer.setBackground(Color.black);
        searchingSetupContainer.setLayout(new BorderLayout());


        JPanel searchingTextArea = new JPanel();
        searchingTextArea.setLayout(new BorderLayout());

        searchingTextArea.setPreferredSize(new Dimension(100, 25));

        JButton searchButton = new JButton("Press to search");
        searchingSetupContainer.add(searchButton, BorderLayout.WEST);


        JTextField searchingText = new JTextField("");
        searchingTextArea.add(searchingText, BorderLayout.CENTER);

        searchButton.addActionListener(
                e -> searching(searchingText.getText())
        );

        searchingSetupContainer.add(searchingTextArea, BorderLayout.CENTER);
        searchingSetupContainer.add(setupSpacer(100, 25), BorderLayout.NORTH);
        searchingSetupContainer.add(setupSpacer(100, 25), BorderLayout.SOUTH);
        return searchingSetupContainer;
    }

    public void searching(String searchingText) { //skal ændre så den virker med media klassen
        if (searchingText.equals("")) {
            movieSelect();
            return;
        }

        JPanel filmPanel = (JPanel) externalContainer.getComponent(1);
        ScrollPane filmScrollPane = (ScrollPane) filmPanel.getComponent(0);
        filmScrollPane.removeAll();
        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0, 8, 10, 10));

        ArrayList<Content> allContent = films;
        allContent.addAll(series);


        for (Content content : allContent) {
            String mediaName = content.getName();
            String[] mediaNames = mediaName.split(" ");
            for (String s : mediaNames) {
                if (s.equals(searchingText)) {
                    filmBox.add(content);
                }
            }

        }

        filmScrollPane.add(filmBox);
    }

    public Container getMenuContainer() {
        return menuContainer;
    }

}
