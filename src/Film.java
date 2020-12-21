import javax.swing.*;

public class Film extends Content {

    protected int year;


    public Film(String filmName, String[] genres, int year, double rating) {
        name = filmName;
        setText(filmName);
        this.genres = genres;
        this.year = year;
        this.rating = rating;
        img = null;
        setupLayout();
    }

    public void popupInfo(JFrame frame) {
        Object[] options = {"Play", "Back"};
        JOptionPane.showOptionDialog(frame, "Film navn " + name + "\n Genre: " + genreToString() +
                        "\n Udgivelsees år: " + year + "\n Bedømmese: " + rating,
                name, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
    }


}
