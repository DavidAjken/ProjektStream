import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InfoHandeler {
    //Denne ArrayList indeholder alle filmne
    private HashMap<String, Content> films;
    //Denne ArrayList indeholder alle serierne
    private HashMap<String, Content> series;

    private ArrayList<String> erroMesseges;


    public InfoHandeler() {
        films = new HashMap<>();
        series = new HashMap<>();
        loadInfo();
    }

    /*
    Denne metode og dens under metoder står for at intage information udefra ved hjælp af InfoIO klassen
     */
    private void loadInfo() {
        InfoIO infoIO = new InfoIO();
        erroMesseges = infoIO.getErrorMessiges();
        loadFilmText(infoIO);
        loadFilmImages(infoIO);
        loadSeriesText(infoIO);
        loadSeriesImages(infoIO);
    }

    private void loadFilmText(InfoIO infoIO) {
        ArrayList<String[]> texts = infoIO.getFilmTexts();
        for (String[] info : texts) {

            String[] genres;
            String tempGenres = info[InfoIO.FILM_GENRES];
            genres = tempGenres.split(",");

            double rating = Double.parseDouble(info[InfoIO.FILM_RATING].replace(',', '.'));

            films.put(info[InfoIO.FILM_NAME], new Film(info[InfoIO.FILM_NAME], genres, rating, info[InfoIO.FILM_YEAR]));
        }
    }

    private void loadFilmImages(InfoIO infoIO) {
        HashMap<String, ImageIcon> images = infoIO.getFilmImages();
        for (Content film : films.values()) {
            film.setImg(images.get(film.getName()));
        }
    }

    private void loadSeriesText(InfoIO infoIO) {
        ArrayList<String[]> texts = infoIO.getSerierTexts();
        for (String[] info : texts) {

            String[] genres;
            String tempGenres = info[InfoIO.SERIES_GENERS];
            genres = tempGenres.split(",");

            double rating = Double.parseDouble(info[InfoIO.SERIES_RATING].replace(',', '.'));

            String[] seasons;
            String tempSeasons = info[InfoIO.SERIES_SEASONS];
            seasons = tempGenres.split(",");

            series.put(info[InfoIO.SERIES_NAME], new Series(info[InfoIO.SERIES_NAME], genres, rating, info[InfoIO.SERIES_YEAR], seasons));
        }
    }

    private void loadSeriesImages(InfoIO infoIO) {
        HashMap<String, ImageIcon> images = infoIO.getSerierImages();
        for (Content serie : series.values()) {
            serie.setImg(images.get(serie.getName()));
        }
    }

    // returns the film array
    public Content[] getFilms() {
        return films.values().toArray(Content[]::new);
    }

    // returns the series array
    public Content[] getSeries() {
        return series.values().toArray(Content[]::new);
    }

    // returns a sigle element form the films array
    public Film getFilm(String contentName) {
        return (Film) films.get(contentName);
    }

    // returns a sigle element form the series array
    public Series getSerie(String contentName) {
        return (Series) series.get(contentName);
    }

    public boolean hasErrores() {
        if (erroMesseges.size() == 0)
            return true;
        else return false;
    }

    public ArrayList<String> getErrorMessiges() {
        return erroMesseges;
    }
}
