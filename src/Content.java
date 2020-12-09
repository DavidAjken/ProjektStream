
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;
    //diverse ens metoder i film og serier som kan samles i en abstract klasse
    public abstract class Content {
        public abstract String[] getGenre();
        public abstract void setGenre(String[] genres);
        public abstract double getRating();
        public abstract void setRating(double rating);

    }


