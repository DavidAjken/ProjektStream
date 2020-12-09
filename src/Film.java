import javax.swing.*;

public class Film extends Content {

    protected String filmName;
    protected String[] genres;
    protected int year;
    protected double rating;

    protected ImageIcon img;


    public Film(String filmName, String[] genres, int year, double rating) {
        this.filmName = filmName;
        this.genres = genres;
        this.year = year;
        this.rating = rating;
        img = null;
        setupLayout();
    }

    public void popupInfo(JFrame frame) {

        //JOptionPane info = new JOptionPane("hej",);
        JOptionPane.showMessageDialog(frame, "Film navn " + filmName + "\n Genre: " + genreToString() +
                        "\n Udgivelsees år: " + year + "\n Bedømmese: " + rating,
                filmName, JOptionPane.PLAIN_MESSAGE);
    }


}
