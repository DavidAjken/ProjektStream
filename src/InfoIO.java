import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class InfoIO {

    //laver en peger på alle filmplakaterne
    public static final File filmDir = new File("src/filmplakater");
    //laver en peger på alle serieforsider
    public static final File seriesDir = new File("src/serieforsider");

    //ArrayLists der indeholder info om film
    private ArrayList<String[]> filmTexts;
    private ArrayList<ImageIcon> filmImages;

    //ArrayLists der indeholder info om serier
    private ArrayList<String[]> serierTexts;
    private ArrayList<ImageIcon> serierImages;

    //inex for den regefølge film infromationen bliver hæntet
    public static final int FILM_NAME = 0;
    public static final int FILM_YEAR = 1;
    public static final int FILM_GENRES = 2;
    public static final int FILM_RATING = 3;

    //inex for den regefølge serie infromationen bliver hæntet
    public static final int SERIES_NAME = 0;
    public static final int SERIES_YEAR = 1;
    public static final int SERIES_GENERS = 2;
    public static final int SERIES_RATING = 3;
    public static final int SERIES_SEASONS = 4;


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

    public InfoIO() throws FileNotFoundException, IOException {
        filmTexts = new ArrayList<>();
        filmImages = new ArrayList<>();
        serierTexts = new ArrayList<>();
        serierImages = new ArrayList<>();
        loadFilmText();
        loadFilmImages();
        loadSerierText();
        loadSerierImages();
    }

    /*
    Denne metode loader alt fra film.txt ind i en arraylist af strings som kan hæntes udefra
     */
    private void loadFilmText() throws FileNotFoundException {
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("film.txt"));
        sc.useDelimiter("\\s*;\\s*");

        while (sc.hasNext()) {
            String filmName = sc.next();
            String filmYear = sc.next();
            String filmGenres = sc.next();
            String rating = sc.next();
            String[] filmText = new String[]{filmName, filmYear, filmGenres, rating};
            filmTexts.add(filmText);
        }
    }

    /*
    denne metoder loader filmplakater fra filmplakater mappen
     */
    private void loadFilmImages() throws IOException {
        if (filmDir.isDirectory()) {
            for (File file : filmDir.listFiles(IMAGE_FILTER)) {
                filmImages.add(new ImageIcon(ImageIO.read(file)));
            }
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
            String serieGenres = sc.next();
            String rating = sc.next();
            String serieSeasons = sc.next();
            String[] serieText = new String[]{serieName, serieYear, serieGenres, rating, serieSeasons};
            serierTexts.add(serieText);
        }
    }

    private void loadSerierImages() throws IOException {
        if (seriesDir.isDirectory()) {
            for (File file : seriesDir.listFiles(IMAGE_FILTER)) {
                serierImages.add(new ImageIcon(ImageIO.read(file)));
            }
        }
    }

    public ArrayList<String[]> getFilmTexts() {
        return filmTexts;
    }

    public ArrayList<ImageIcon> getFilmImages() {
        return filmImages;
    }

    public ArrayList<String[]> getSerierTexts() {
        return serierTexts;
    }

    public ArrayList<ImageIcon> getSerierImages() {
        return serierImages;
    }

}
