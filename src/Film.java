import javax.swing.*;

public class Film extends Content {

    public Film(String name, String[] genres, double rating, String year) {
        super(name, genres, rating, year);
    }

    public String popupInfoString() {
        return "Film navn " + name + "\n Genre: " + genreToString() +
                "\n Udgivelsees år: " + year + "\n Bedømmese: " + rating;
    }


}
