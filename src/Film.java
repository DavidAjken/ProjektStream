import javax.swing.*;
import javax.swing.Popup;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Film extends Content {
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
        setupLayout();
    }


    public String getName() {
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
        setIcon(filmImg);
        this.filmImg = filmImg;

        //herunder bliver Film's størrelse sat
        setMaximumSize(new Dimension(filmImg.getIconWidth(), filmImg.getIconHeight() + 25));
        setPreferredSize(new Dimension(filmImg.getIconWidth(), filmImg.getIconHeight() + 25));
        setMinimumSize(new Dimension(filmImg.getIconWidth(), filmImg.getIconHeight() + 25));
    }

    private void setupLayout(){
        setHorizontalAlignment(0);
        setBackground(new Color(0, 0, 0));
        setBorder(new LineBorder(Color.red, 1));

        //herunder håndteres tegningen af texten
        setText(getName());
        setHorizontalTextPosition(0);
        setVerticalTextPosition(3);
        setForeground(new Color(255, 255, 255));

    }

    private String genreToString(){
        String genreString = "";
        for (String s: genres) {
            genreString += s+" ";
        }
        return genreString;
    }

    public void popupInfo(JFrame frame) {

        //JOptionPane info = new JOptionPane("hej",);
        JOptionPane.showMessageDialog(frame,"Film navn "+filmName+"\n Genre: "+genreToString()+
                        "\n Udgivelsees år: "+year+"\n Bedømmese: "+rating,
                filmName,JOptionPane.PLAIN_MESSAGE);
    }
}
