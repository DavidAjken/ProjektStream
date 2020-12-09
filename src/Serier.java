
    import java.util.ArrayList;
    public class Serier extends Content{
        protected String serieName;
        protected String[] genres;
        protected String year;
        protected double rating;
        protected String[] SE;

        public Serier(String serieName, String[] genres, String year, double rating, String[] SE) {
            this.serieName = serieName;
            this.genres = genres;
            this.year = year;
            this.rating = rating;
            this.SE = SE;
        }
        //get og set metoder til serier

        public String getSerieName() {
            return serieName;
        }

        public String[] getGenre() {
            return genres;
        }

        public String getYear() {
            return year;
        }

        public double getRating() {
            return rating;
        }

        public String[] getSE() {
            return SE;
        }

        public void setSerieName(String serieName) {
            this.serieName = serieName;
        }

        public void setGenre(String[] genres) {
            this.genres = genres;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public void setSE(String[] SE) {
            this.SE = SE;
        }

        public void print() {
            System.out.println(serieName + year + genres[0] + rating + SE[0]);
        }
    }
