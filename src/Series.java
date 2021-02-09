
import javax.swing.*;

public class Series extends Content {
    protected String year;
    protected String[] seasons;

    public Series(String name, String[] genres, String year, double rating, String[] seasons) {
        this.name = name;
        checkName();
        this.genres = genres;
        this.year = year;
        this.rating = rating;
        this.seasons = seasons;
        setupLayout();
    }

    //get og set metoder til serier, resten er extended til Content.
    public String getYear() {
        return year;
    }

    public String[] getSE() {
        return seasons;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSeason(String[] seasons) {
        this.seasons = seasons;
    }

    public void popupInfo(JFrame frame) {

        //JOptionPane info = new JOptionPane("hej",);
        JOptionPane.showMessageDialog(frame, "Serie navn " + name + "\n Genre: " + genreToString() +
                        "\n Aktive år: " + year + "\n Bedømmese: " + rating,
                name, JOptionPane.PLAIN_MESSAGE);
    }

}
