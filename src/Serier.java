import javax.swing.*;

public class Serier extends Content {
    protected String year;
    protected String[] season;

    public Serier(String serieName, String[] genres, String year, double rating, String[] season) {
        this.name = serieName;
        setText(name);
        this.genres = genres;
        this.year = year;
        this.rating = rating;
        this.season = season;
        setupLayout();
    }
    //get og set metoder til serier, resten er extended til Content


    public void popupInfo(JFrame frame) {

        //JOptionPane info = new JOptionPane("hej",);
        JOptionPane.showMessageDialog(frame, "Serie navn " + name + "\n Genre: " + genreToString() +
                        "\n Aktive år: " + year + "\n Bedømmese: " + rating,
                name, JOptionPane.PLAIN_MESSAGE);
    }
    import javax.swing.*;
    import javax.swing.border.LineBorder;
    import java.awt.*;
    import java.util.ArrayList;
    public class Serier extends Content{
        protected String name;
        protected String[] genres;
        protected String year;
        protected double rating;
        protected String[] SE;

        public Serier(String name, String[] genres, String year, double rating, String[] SE) {
            this.name = name;
            this.genres = genres;
            this.year = year;
            this.rating = rating;
            this.SE = SE;
        }
        //get og set metoder til serier, resten er extended til Content.

        public String getYear() {
            return year;
        }

        public String[] getSE() {
            return SE;
        }

        public void setYear(String year) {
            this.year = year;
        }
        public void setSeason(String[] Se) {
            this.SE = SE;
        }

        public void popupInfo(JFrame frame) {

            //JOptionPane info = new JOptionPane("hej",);
            JOptionPane.showMessageDialog(frame,"Serie navn "+name+"\n Genre: "+genreToString()+
                            "\n Aktive år: "+year+"\n Bedømmese: "+rating,
                    name,JOptionPane.PLAIN_MESSAGE);
        }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setSeason(String[] season) {
        this.season = season;
    }

}
