import javax.swing.*;
import javax.swing.Popup;
import javax.swing.border.LineBorder;
import java.awt.*;


public class Film extends Content {

    protected String name;
    protected String[] genres;
    protected int year;
    protected double rating;


    protected ImageIcon img;

    public Film(String name, String[] genres, int year, double rating) {
        this.name = name;
        this.genres = genres;
        this.year = year;
        this.rating = rating;
        img = null;
        setupLayout();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String genreToString(){
        String genreString = "";
        for (String s: genres) {
            genreString += s+" ";
        }
        return genreString;
    }

    public void popupInfo(JFrame frame) {

        //JOptionPane info = new JOptionPane("hej",);
        JOptionPane.showMessageDialog(frame,"Film navn "+name+"\n Genre: "+genreToString()+
                        "\n Udgivelsees år: "+year+"\n Bedømmese: "+rating,
                name,JOptionPane.PLAIN_MESSAGE);
    }


}
