import javax.swing.*;

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

    public void print(){
        System.out.println(filmName+year+genres[0]+rating);
    }
}
