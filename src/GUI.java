import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;


public class GUI {
    protected JFrame frame;





    GUI() throws FileNotFoundException {
        loadText();
    }
    private void makeFrame()    {
        frame = new JFrame("GUI");
        frame.setSize(400,400);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1,2));

        frame.setVisible(true);
    }

    public void loadText() throws FileNotFoundException {
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


}
