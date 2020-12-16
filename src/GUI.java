import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.border.LineBorder;


public class GUI {

    //Dette er den JFrame som bliver vist når programmet kørre
    private JFrame frame;
    //Denne ArrayList indeholder alle filmne
    private ArrayList<Content> films;
    //Denne ArrayList indeholder alle serierne
    private ArrayList<Content> series;
    //Denne MenuGui håndtere alt hvad menuen kan
    private MenuGui menuGui;
    //laver en peger på alle filmplakaterne
    static final File filmDir = new File("src/filmplakater");
    //laver en peger på alle serieforsider
    static final File seriesDir = new File("src/serieforsider");

    //laver et filter der skal bruges til at loade billeder
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            if (name.endsWith(".jpg")) {
                return true;
            }
            return false;
        }
    };

    Dimension menuDimension;
    Dimension contentDimension;

    GUI() throws FileNotFoundException, IOException {
        frame = new JFrame("GUI");
        films = new ArrayList<Content>();
        series = new ArrayList<Content>();
        menuGui = new MenuGui(frame.getContentPane(), films, series);

        try {
            loadFilmText();
            loadFilmImages();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Filmplakaterne kunne ikke findes",
                    "Film fejl", JOptionPane.PLAIN_MESSAGE);
        }

        try {
            loadSerierText();
            loadSerierImages();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        makeFrame();
    }

    private void makeFrame() {

        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuDimension = new Dimension(frame.getWidth(), 100);
        contentDimension = new Dimension(frame.getWidth(), frame.getHeight() - 135);

        Container contentPane = frame.getContentPane();
        contentPane.setBackground(new Color(155, 5, 5));
        contentPane.setLayout(new BorderLayout());


        drawMenuGui(contentPane);
        drawFilms(contentPane);
        frame.setVisible(true);
    }

    /*
    Denne metode loader alt fra film.txt ind i en arraylist af film
     */
    private void loadFilmText() throws FileNotFoundException {
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("film.txt"));
        sc.useDelimiter("\\s*;\\s*");

        while (sc.hasNext()) {
            String filmName = sc.next();
            int filmYear = sc.nextInt();
            String[] filmGenres;
            String tempGenres = sc.next();
            filmGenres = tempGenres.split(",");

            double rating = sc.nextDouble();
            Film film = new Film(filmName, filmGenres, filmYear, rating);
            films.add(film);
        }
    }

    /*
    denne metoder loader filmplakater fra filmplakater mappen
    og tildeler hver film i ArrayLsiten films en plakat der tilhørre den relevatne film
     */
    private void loadFilmImages() throws IOException {

        ImageIcon filmImg = null;
        HashMap<String, ImageIcon> filmImages = new HashMap<>();

        if (filmDir.isDirectory()) {
            for (File file : filmDir.listFiles(IMAGE_FILTER)) {
                filmImg = new ImageIcon(ImageIO.read(file));
                filmImages.put(file.getName().split(".jpg")[0], filmImg);
            }
        }

        for (Content film : films) {
            String key = film.getName();
            filmImg = (ImageIcon) filmImages.get(key);
            film.setImg(filmImg);
        }
    }

    /*
    Denne metode loader alt fra serier.txt ind i en arraylist af film
     */
    private void loadSerierText() throws FileNotFoundException {
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("serier.txt"));
        sc.useDelimiter("\\s*;\\s*");

        while (sc.hasNext()) {
            String serieName = sc.next();
            String serieYear = sc.next();

            String[] serieGenres;
            String tempGenres = sc.next();
            serieGenres = tempGenres.split(",");

            double rating = sc.nextDouble();

            String[] serieSeasons;
            String tempSeasons = sc.next();
            serieSeasons = tempSeasons.split(",");

            Serier serie = new Serier(serieName, serieGenres, serieYear, rating, serieSeasons);
            series.add(serie);
        }
    }

    private void loadSerierImages() throws IOException {

        ImageIcon serierImg = null;
        HashMap<String, ImageIcon> serierImages = new HashMap<>();

        if (seriesDir.isDirectory()) {
            for (File file : seriesDir.listFiles(IMAGE_FILTER)) {
                serierImg = new ImageIcon(ImageIO.read(file));
                serierImages.put(file.getName().split(".jpg")[0], serierImg);
            }
        }

        for (Content serie : series) {
            String key = serie.getName();
            serierImg = (ImageIcon) serierImages.get(key);
            serie.setImg(serierImg);
        }
    }

    private void drawMenuGui(Container contentPane) {

        JLabel menuGuiPanel = new JLabel();

        menuGuiPanel.setMaximumSize(menuDimension);
        menuGuiPanel.setPreferredSize(menuDimension);
        menuGuiPanel.setMinimumSize(menuDimension);

        menuGuiPanel.setLayout(new GridLayout(1, 5));
        menuGuiPanel.setBackground(Color.black);
        menuGuiPanel.setBorder(new LineBorder(Color.red, 5));

        menuGuiPanel.add(menuGui.getMenuContainer());

        contentPane.add(menuGuiPanel, BorderLayout.NORTH);
    }

    private void drawFilms(Container contentPane) {

        //herunder indsteillles det JPanel som filmområdet bilver tejnet på
        JPanel mediaPanel = new JPanel();
        mediaPanel.setMaximumSize(contentDimension);
        mediaPanel.setPreferredSize(contentDimension);
        mediaPanel.setMinimumSize(contentDimension);
        mediaPanel.setLayout(new GridLayout(1, 5));
        mediaPanel.setBackground(Color.black);
        mediaPanel.setBorder(new LineBorder(Color.red, 5));

        Container filmScrollPane = new ScrollPane();

        filmScrollPane.setSize(contentPane.getWidth(), 850);
        filmScrollPane.setBackground(Color.black);

        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Content film : films) {

            //herunder bliver der tilsat en action til newFilm
            film.addActionListener(
                    e -> film.popupInfo(frame)
            );
            filmBox.add(film);
        }

        filmScrollPane.add(filmBox);
        mediaPanel.add(filmScrollPane);
        contentPane.add(mediaPanel, BorderLayout.SOUTH);
    }


}
