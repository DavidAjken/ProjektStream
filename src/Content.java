
/*skal fjernes:*/import java.awt.*;
/*skal fjernes:*/import javax.swing.border.LineBorder;
import javax.swing.*;/*skal ændres til efter Draw klassen er lavet: import javax.swing.ImageIcon;*/


//diverse ens metoder i film og serier som kan samles i en abstract klasse
    public abstract class Content{
    protected String name;
    protected String[] genres;
    protected double rating;
    protected ImageIcon img;
    protected String year;

    protected Content(String name, String[] genres, double rating,String year){
        this.name = name;
        checkName();
        this.genres = genres;
        this.rating = rating;
        this.year = year;
    }
    //Vi ved ikke hvorfor men når scanner klassen læser et ä som Ã¤ når den er i en .jar fil
    protected void checkName() {
        if (name.contains(" Ã¤")) {
            String tempName = name.replaceAll("Ã¤", "ä");
            name = tempName;
        }
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String[] getGenre() {return genres;}
    public void setGenre(String[] genres) {this.genres = genres;}
    public double getRating() {return rating;}
    public void setRating(double rating) {this.rating = rating;}
    public ImageIcon getImg() {return img;}
    public void setImg(ImageIcon img) {this.img = img;}
    public void setYear(String year) {
        this.year = year;
    }
    public String getYear() {
        return year;
    }

    public String genreToString() {
        String genreString = "";
        for (String s : genres) {
            genreString += s + " ";
        }
        return genreString;
    }
    public abstract String popupInfoString() ;

}
