import javax.swing.*;
import javax.swing.Popup;
import java.awt.*;

public class Film {
    protected String filmName;
    protected String[] genres;
    protected int year;
    protected double rating;


    protected ImageIcon filmImg;

    public Film(String filmName, String[] genres, int year, double rating) {
        this.filmName = filmName;
        this.genres = genres;
        this.year = year;
        this.rating = rating;
        filmImg = null;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String[] getGenre() {
        return genres;
    }

    public void setGenre(String[] genres) {
        this.genres = genres;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ImageIcon getFilmImg() {
        return filmImg;
    }

    public void setFilmImg(ImageIcon filmImg) {
        this.filmImg = filmImg;
    }

    private String genreToString(){
        String genreString = new String();
        for (String s: genres) {
            genreString += s+" ";
        }
        return genreString;
    }

    public void print() {
        System.out.println(filmName + year + genres[0] + rating);
    }

    public void popupInfo(JFrame frame) {

        //JOptionPane info = new JOptionPane("hej",);
        JOptionPane.showMessageDialog(frame,"Film navn "+filmName+"\n Genre: "+genreToString()+
                        "\n Udgivelsees år: "+year+"\n Bedømmese: "+rating,
                filmName,JOptionPane.PLAIN_MESSAGE);

    }
}
