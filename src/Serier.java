import javax.swing.*;

public class Serier extends Content {
    protected String serieName;
    protected String[] genres;
    protected String year;
    protected double rating;
    protected String[] season;

    public Serier(String serieName, String[] genres, String year, double rating, String[] season) {
        this.serieName = serieName;
        this.genres = genres;
        this.year = year;
        this.rating = rating;
        this.season = season;
    }
    //get og set metoder til serier, resten er extended til Content


    public void popupInfo(JFrame frame) {

        //JOptionPane info = new JOptionPane("hej",);
        JOptionPane.showMessageDialog(frame, "Serie navn " + name + "\n Genre: " + genreToString() +
                        "\n Aktive år: " + year + "\n Bedømmese: " + rating,
                name, JOptionPane.PLAIN_MESSAGE);
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setSeason(String[] season) {
        this.season = season;
    }

}
