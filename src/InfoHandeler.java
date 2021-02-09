import java.io.IOException;
import java.util.ArrayList;

public class InfoHandeler {
    //Denne ArrayList indeholder alle filmne
    private ArrayList<Content> films;
    //Denne ArrayList indeholder alle serierne
    private ArrayList<Content> series;


    public InfoHandeler() throws IOException {
        films = new ArrayList<Content>();
        series = new ArrayList<Content>();
        loadInfo();
    }

    private void loadInfo() throws IOException {
        InfoIO infoIO = new InfoIO();
        loadFilmText(infoIO);
        loadSeriesText(infoIO);
    }

    private void loadFilmText(InfoIO infoIO) {
        ArrayList<String[]> texts = infoIO.getFilmTexts();
        for (String[] info : texts) {

            String[] genres;
            String tempGenres = info[InfoIO.FILM_GENRES];
            genres = tempGenres.split(",");

            int year = Integer.parseInt(info[InfoIO.FILM_YEAR]);

            double rating = Double.parseDouble(info[InfoIO.FILM_RATING].replace(',', '.'));

            films.add(new Film(info[InfoIO.FILM_NAME], genres, year, rating));
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

            series.add(new Series(info[InfoIO.SERIES_NAME], genres, info[InfoIO.SERIES_YEAR], rating,seasons));
        }
    }

}
