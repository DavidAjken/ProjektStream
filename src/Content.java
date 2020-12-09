
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;
<<<<<<< HEAD
import javax.swing.border.LineBorder;

//diverse ens metoder i film og serier som kan samles i en abstract klasse
    public abstract class Content extends JButton {
    protected String name;
    protected String[] genres;
    protected double rating;

    protected ImageIcon img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getGenre() {
        return genres;
    }

    public void setGenre(String[] genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        setIcon(img);
        this.img = img;

        setMaximumSize(new Dimension(img.getIconWidth(), img.getIconHeight() + 25));
        setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight() + 25));
        setMinimumSize(new Dimension(img.getIconWidth(), img.getIconHeight() + 25));
    }

    public void setupLayout() {
        setHorizontalAlignment(0);
        setBackground(new Color(0, 0, 0));
        setBorder(new LineBorder(Color.red, 1));

        setText(getName());
        setHorizontalTextPosition(0);
        setVerticalTextPosition(3);
        setForeground(new Color(255, 255, 255));
=======
    //diverse ens metoder i film og serier som kan samles i en abstract klasse
    public abstract class Content extends JButton{
        public abstract String[] getGenre();
        public abstract void setGenre(String[] genres);
        public abstract double getRating();
        public abstract void setRating(double rating);
>>>>>>> d1661c515fc3bf0af3f53819a9fcba8e9c4825c1

    }

    public String genreToString() {
        String genreString = "";
        for (String s : genres) {
            genreString += s + " ";
        }
        return genreString;
    }

<<<<<<< HEAD
    public abstract void popupInfo(JFrame frame);
}




=======
>>>>>>> d1661c515fc3bf0af3f53819a9fcba8e9c4825c1
