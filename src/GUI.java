import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI {
    protected JFrame frame;

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
        makeFrame();

        loadFilmText();
        //frame.update(frame.getGraphics());
    }

    private void makeFrame()    {

        frame = new JFrame("GUI");
        frame.setSize(800,800);

        //Container contentPane = frame.getContentPane();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2,1));

        loadFilmImages(contentPane);

        frame.setVisible(true);
    }

    /*
    Denne metode loader alt fra film.txt ind i en arraylist af film
     */
    public void loadFilmText() throws FileNotFoundException {
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("film.txt"));
        ArrayList<Film> films = new ArrayList<Film>();
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
        for (Film f: films) {
            f.print();
        }
    }

    public void loadFilmImages(Container contentPane) {
        Container filmScrollPane = new ScrollPane();
        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0,4,10,10));
        filmScrollPane.add(filmBox);
        ImageIcon img = null;
        if(filmDir.isDirectory()){
            for (File f: filmDir.listFiles(IMAGE_FILTER)) {
                try {
                    img = new ImageIcon(ImageIO.read(f));
                    JLabel newFilmImage = new JLabel(img);
                    filmBox.add(newFilmImage);
                } catch(IOException e){}
            }
        }


        contentPane.add(filmScrollPane);

    }

}
