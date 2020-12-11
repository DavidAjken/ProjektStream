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
    private ArrayList<Film> films;
    //Denne ArrayList indeholder alle serierne
    private ArrayList<Serier> series;
    //Denne MenuGui håndtere alt hvad menuen kan
    private MenuGui menuGui;

    //laver en fil som indeholder alle filmplakaterne
    static final File filmDir = new File("src/filmplakater");
    //laver en fil som indeholder alle serielakaterne
    static final File serieDir = new File("src/serieforsider");

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
    Dimension mediaDimension;

    GUI() throws FileNotFoundException, IOException {
        frame = new JFrame("GUI");
        films = new ArrayList<Film>();
        menuGui = new MenuGui(frame.getContentPane(), films);
        loadFilmText();
        loadFilmImages();
        makeFrame();
    }

    private void makeFrame() {

        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuDimension = new Dimension(frame.getWidth(), 100);
        mediaDimension = new Dimension(frame.getWidth(), frame.getHeight() - 135);

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

        for (Film film : films) {
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
            String[] serieSE;
            String tempSE = sc.next();
            serieSE = tempSE.split(",");

            double rating = sc.nextDouble();
            Serier serie = new Serier(serieName, serieGenres, serieYear, rating, serieSE);
            series.add(serie);
        }
    }
    /*
   denne metoder loader serieplakater fra serieplakater mappen
   og tildeler hver serie i ArrayLsiten series en plakat der tilhørre den relevatne serie
    */
    private void loadSerierImages() throws IOException {

        ImageIcon serieImg = null;
        HashMap<String, ImageIcon> serieImages = new HashMap<>();

        if (serieDir.isDirectory()) {
            for (File file : serieDir.listFiles(IMAGE_FILTER)) {
                serieImg = new ImageIcon(ImageIO.read(file));
                serieImages.put(file.getName().split(".jpg")[0], serieImg);
            }
        }

        for (Serier serie : series) {
            String key = serie.getName();
            serieImg = (ImageIcon) serieImages.get(key);
            serie.setImg(serieImg);
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

        //herunder indstillles det JPanel som filmområdet bilver tejnet på
        JPanel filmPanel = new JPanel();
        filmPanel.setMaximumSize(mediaDimension);
        filmPanel.setPreferredSize(mediaDimension);
        filmPanel.setMinimumSize(mediaDimension);
        filmPanel.setLayout(new GridLayout(1, 5));
        filmPanel.setBackground(Color.black);
        filmPanel.setBorder(new LineBorder(Color.red, 5));

        Container filmScrollPane = new ScrollPane();

        filmScrollPane.setSize(contentPane.getWidth(), 850);
        filmScrollPane.setBackground(Color.black);

        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Film film : films) {

            //herunder bliver der tilsat en action til newFilm
            film.addActionListener(
                    e -> film.popupInfo(frame)
            );
            filmBox.add(film);
        }

        filmScrollPane.add(filmBox);
        filmPanel.add(filmScrollPane);
        contentPane.add(filmPanel, BorderLayout.SOUTH);
    }


}
