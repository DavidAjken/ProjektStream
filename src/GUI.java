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
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI {

    //Dette er den JFrame som bliver vist når programmet kørre
    protected JFrame frame;
    //Denne ArrayList indeholder alle filmne
    protected ArrayList<Film> films;

    //laver en fil som indeholder alle filmplakaterne
    static final File filmDir = new File("src/filmplakater");

    //laver et filter der skal bruges til at loade billeder
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            if(name.endsWith(".jpg")){
                return true;
            }
            return false;
        }
    };





    GUI() throws FileNotFoundException, IOException{
        films = new ArrayList<Film>();
        loadFilmText();
        loadFilmImages();
        makeFrame();
    }

    private void makeFrame() {

        frame = new JFrame("GUI");
        frame.setSize(800,800);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2,1));

        drawFilms(contentPane);
        frame.setVisible(true);
    }

    /*
    Denne metode loader alt fra film.txt ind i en arraylist af film
     */
    private void loadFilmText() throws FileNotFoundException {
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("film.txt"));
        sc.useDelimiter("\\s*;\\s*");

        while (sc.hasNext()){
            String filmName = sc.next();
            int filmYear = sc.nextInt();
            String[] filmGenres;
            String tempGenres = sc.next();
            filmGenres = tempGenres.split(",");

            double rating = sc.nextDouble();
            Film film = new Film(filmName,filmGenres,filmYear,rating);
            films.add(film);
        }
    }

    /*
    denne metoder loader filmplakater fra filmplakater mappen
    og tildeler hver film i ArrayLsiten films en plakat der tilhørre den relevatne film
     */
    private void loadFilmImages() throws IOException {

        ImageIcon filmImg = null;
        HashMap filmImages = new HashMap<String,ImageIcon>();

        if(filmDir.isDirectory()){
            for (File f: filmDir.listFiles(IMAGE_FILTER)) {
                filmImg = new ImageIcon(ImageIO.read(f));
                filmImages.put(f.getName().split(".jpg")[0], filmImg);
            }
        }

        for (Film f: films) {
            String key = f.getFilmName();
            filmImg = (ImageIcon) filmImages.get(key);
            f.setFilmImg(filmImg);
        }


    }

    private void drawFilms(Container contentPane){
        Container filmScrollPane = new ScrollPane();
        filmScrollPane.setBackground(new Color(5,5,5));
        filmScrollPane.doLayout();

        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0,8,10,10));

        ImageIcon filmImg = null;
        for (Film f: films) {
            filmImg = f.getFilmImg();

            JLabel newFilm = new JLabel(filmImg);
            newFilm.setHorizontalAlignment(0);

            //herunder håndteres tegningen af texten
            newFilm.setText(f.getFilmName());
            newFilm.setHorizontalTextPosition(0);
            newFilm.setVerticalTextPosition(3);
            newFilm.setForeground(new Color(255,255,255));

            //herunder bliver newFilm's størrelse sat
            newFilm.setMaximumSize(new Dimension(filmImg.getIconWidth(),filmImg.getIconHeight()+20));
            newFilm.setPreferredSize(new Dimension(filmImg.getIconWidth(),filmImg.getIconHeight()+20));
            newFilm.setMinimumSize(new Dimension(filmImg.getIconWidth(),filmImg.getIconHeight()+20));

            filmBox.add(newFilm);

        }
        filmScrollPane.add(filmBox);
        contentPane.add(filmScrollPane);
    }

}
