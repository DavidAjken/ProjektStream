import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Flow;
import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.border.LineBorder;


public class GUI {

    //Dette er den JFrame som bliver vist når programmet kørre
    private JFrame frame;
    //Denne ArrayList indeholder alle filmne
    private ArrayList<Film> films;
    //Denne MenuGui håndtere alt hvad menuen kan
    private MenuGui menuGui;

    //laver en fil som indeholder alle filmplakaterne
    static final File filmDir = new File("src/filmplakater");

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
        films = new ArrayList<Film>();
        menuGui = new MenuGui();
        loadFilmText();
        loadFilmImages();
        makeFrame();
    }

    private void makeFrame() {

        frame = new JFrame("GUI");
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuDimension = new Dimension(frame.getWidth(), 100);
        mediaDimension = new Dimension(frame.getWidth(), frame.getHeight()-135);

        Container contentPane = frame.getContentPane();
        contentPane.setBackground(new Color(155, 5, 5));
        contentPane.setLayout(new BorderLayout());


        drawMenuGui(contentPane);
        drawFilms(contentPane);
        frame.setVisible(true);
        menuGui.krigsFilm(contentPane);
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
        HashMap filmImages = new HashMap<String, ImageIcon>();

        if (filmDir.isDirectory()) {
            for (File file : filmDir.listFiles(IMAGE_FILTER)) {
                filmImg = new ImageIcon(ImageIO.read(file));
                filmImages.put(file.getName().split(".jpg")[0], filmImg);
            }
        }

        for (Film film : films) {
            String key = film.getFilmName();
            filmImg = (ImageIcon) filmImages.get(key);
            film.setFilmImg(filmImg);
        }


    }

    private void drawMenuGui(Container contentPane) {


        JPanel menuGuiPanel = new JPanel();

        menuGuiPanel.setMaximumSize(menuDimension);
        menuGuiPanel.setPreferredSize(menuDimension);
        menuGuiPanel.setMinimumSize(menuDimension);
        menuGuiPanel.setLayout(new GridLayout(1, 5));
        menuGuiPanel.setBackground(Color.black);
        menuGuiPanel.setBorder(new LineBorder(Color.red, 5));

        menuGuiPanel.add(menuGui);

        contentPane.add(menuGuiPanel, BorderLayout.NORTH);
    }

    private void drawFilms(Container contentPane) {

        //herunder indsteillles det JPanel som filmområdet bilver tejnet på
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

        ImageIcon filmImg = null;
        for (Film film : films) {
            filmImg = film.getFilmImg();

            JButton newFilm = new JButton(filmImg);
            newFilm.setHorizontalAlignment(0);
            newFilm.setBackground(new Color(0, 0, 0));
            newFilm.setBorder(new LineBorder(Color.red, 1));

            //herunder håndteres tegningen af texten
            newFilm.setText(film.getFilmName());
            newFilm.setHorizontalTextPosition(0);
            newFilm.setVerticalTextPosition(3);
            newFilm.setForeground(new Color(255, 255, 255));

            //herunder bliver newFilm's størrelse sat
            newFilm.setMaximumSize(new Dimension(filmImg.getIconWidth(), filmImg.getIconHeight() + 25));
            newFilm.setPreferredSize(new Dimension(filmImg.getIconWidth(), filmImg.getIconHeight() + 25));
            newFilm.setMinimumSize(new Dimension(filmImg.getIconWidth(), filmImg.getIconHeight() + 25));

            //herunder bliver der tilsat en action til newFilm
            newFilm.addActionListener(
                    e -> film.popupInfo(frame)
            );

            filmBox.add(newFilm);

        }

        filmScrollPane.add(filmBox);

        filmPanel.add(filmScrollPane);
        contentPane.add(filmPanel, BorderLayout.SOUTH);
    }


}
