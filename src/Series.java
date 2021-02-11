
import javax.swing.*;

public class Series extends Content {
    protected String[] seasons;

    public Series(String name, String[] genres, double rating, String year, String[] seasons) {
        super(name, genres, rating, year);
        this.seasons = seasons;
    }

    //get og set metoder til serier, resten er extended til Content.
    public String[] getSE() {
        return seasons;
    }
    public void setSeason(String[] seasons) {
        this.seasons = seasons;
    }

    public String popupInfoString() {
        return "Serie navn " + name + "\n Genre: " + genreToString() +
                "\n Aktive år: " + year + "\n Bedømmese: " + rating;
    }

}
