
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.border.LineBorder;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class MenuGui {

    Container menuContainer;
    Container externalContainer;
    HashMap<String, JButton> films = new HashMap<>();
    HashMap<String, JButton> series = new HashMap<>();

    InfoHandeler infoHandeler;


    public MenuGui(Container externalContainer, ArrayList<JButton> films, ArrayList<JButton> series, InfoHandeler infoHandeler) {
        this.infoHandeler = infoHandeler;
        this.externalContainer = externalContainer;

        for (JButton film : films) {
            this.films.put(film.getText(), film);
        }
        for (JButton serie : series) {
            this.films.put(serie.getText(), serie);
        }
        menuGuiSetup();
    }

    private void menuGuiSetup() {
        menuContainer = new Container();
        Container menuArea = new Container();
        Container filmArea = new Container();
        Container seriesArea = new Container();
        Container searchingArea = new Container();
        Container brugerArea = new Container();


        menuContainer.setLayout(new BorderLayout(20, 0));
        menuContainer.setBackground(new Color(10, 10, 10));
        menuContainer.add(menuArea, BorderLayout.CENTER);
        addLAndRSpacers(menuContainer);

        menuArea.setLayout(new GridLayout(1, 0, 50, 0));

        brugerArea.setLayout(new BorderLayout());
        brugerArea.add(brugerMenuSetup(), BorderLayout.WEST);

        filmArea.setLayout(new BorderLayout());
        filmArea.add(movieButtonSetup(), BorderLayout.WEST);
        filmArea.add(warMovieButtonSetup(), BorderLayout.EAST);

        seriesArea.setLayout(new BorderLayout());
        seriesArea.add(seriesButtonSetup(), BorderLayout.WEST);
        seriesArea.add(dramaSeriesButtonSetup(), BorderLayout.EAST);

        searchingArea.setLayout(new BorderLayout());
        searchingArea.add(searchingAreaSetup(), BorderLayout.CENTER);

        menuArea.add(brugerArea);
        menuArea.add(filmArea);
        menuArea.add(seriesArea);
        menuArea.add(searchingArea);


    }

    /*
    Denne metode står for at give plads i siderne så knapperne ikke er lige op af kanterne
     */
    private void addLAndRSpacers(Container container) {
        container.add(setupSpacer(100, 100), BorderLayout.WEST);
        container.add(setupSpacer(100, 100), BorderLayout.EAST);
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


    /*
        Metoden som skal oprette menuen til brugeren, nåede ikke at få til at virke grundet mangelnde medlem
     */
    private JMenu generalMenuSetup(String name) {
        JMenu menu = new JMenu(name);
        menu.setForeground(Color.white);
        menu.setBackground(new Color(255, 0, 0));

        menu.setMinimumSize(new Dimension(100, 100));
        menu.setPreferredSize(new Dimension(100, 100));
        menu.setMaximumSize(new Dimension(100, 100));

        return menu;
    }

    /*
    Denne metode laver bruger knappen, samt drop-down menuen for "lod ud" og "Min liste"
    virker ikke, grundet tidsmangel
     */
    private JMenu brugerMenuSetup() {
        JMenu brugerMenu = generalMenuSetup("Bruger");
        JMenuItem logUdItem = new JMenuItem("Log ud");
        brugerMenu.add(logUdItem);
        logUdItem.addActionListener(
                e -> System.exit(0)
        );
        JMenuItem minListeItem = new JMenuItem("Min liste");
        brugerMenu.add(minListeItem);

        return brugerMenu;
    }


    private JButton movieButtonSetup() {
        JButton movieButton = generalButtonSetup("Film");
        movieButton.addActionListener(
                e -> movieSelect()
        );
        return movieButton;
    }

    private JButton warMovieButtonSetup() {
        JButton warMovieButton = generalButtonSetup("Krigs film");
        warMovieButton.addActionListener(
                e -> warMovieSelect()
        );
        return warMovieButton;
    }

    private void movieSelect() {
        generalMovieGenreSelect("");
    }

    private void warMovieSelect() {
        generalMovieGenreSelect("War");
    }

    /*
    Denne metode står for det alle valg metoderne skal kunne/have i menuen for film
    */
    private void generalMovieGenreSelect(String genre) {
        JPanel mediaPanel = (JPanel) externalContainer.getComponent(1);
        ScrollPane mediaScrollPane = (ScrollPane) mediaPanel.getComponent(0);
        mediaScrollPane.removeAll();
        Container mediaBox = new Container();
        mediaBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Content film : infoHandeler.getFilms()) {
            for (String gerne : film.getGenre()) {
                if (gerne.equals(" " + genre) || gerne.equals(genre) || genre.equals("")) {
                    mediaBox.add(addContent(film));
                }
            }
        }
        mediaScrollPane.add(mediaBox);
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

        for (Content serie : infoHandeler.getSeries()) {
            for (String gerne : serie.getGenre()) {
                if (gerne.equals(" " + genre) || gerne.equals(genre) || genre.equals("")) {
                    mediaBox.add(addContent(serie));
                }
            }
        }
        mediaScrollPane.add(mediaBox);
    }

    private JButton addContent(Content content) {
        return films.get(content.getName());
        /*JButton contentButton = new JButton();
        contentButton.setName(content.getName());
        contentButton.setIcon(content.getImg());
        contentButton.setMaximumSize(new Dimension(contentButton.getIcon().getIconWidth(), contentButton.getIcon().getIconHeight() + 25));
        contentButton.setPreferredSize(new Dimension(contentButton.getIcon().getIconWidth(), contentButton.getIcon().getIconHeight() + 25));
        contentButton.setMinimumSize(new Dimension(contentButton.getIcon().getIconWidth(), contentButton.getIcon().getIconHeight() + 25));
        contentButton.setHorizontalAlignment(0);
        contentButton.setBackground(new Color(0, 0, 0));
        contentButton.setBorder(new LineBorder(Color.red, 1));

        contentButton.setText(contentButton.getName());
        contentButton.setHorizontalTextPosition(0);
        contentButton.setVerticalTextPosition(3);
        contentButton.setForeground(new Color(255, 255, 255));
        return contentButton;

         */
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

        ArrayList<JButton> allContent = new ArrayList<>();
        allContent.addAll(films.values());
        allContent.addAll(series.values());


        for (JButton content : allContent) {
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
