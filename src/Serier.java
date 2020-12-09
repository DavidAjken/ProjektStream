
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

<<<<<<< HEAD
=======
        public String getSerieName() {
            return serieName;
        }

        public String[] getGenre() {
            return genres;
        }

>>>>>>> d1661c515fc3bf0af3f53819a9fcba8e9c4825c1
        public String getYear() {
            return year;
        }

        public String[] getSE() {
            return SE;
        }

<<<<<<< HEAD
=======
        public void setSerieName(String serieName) {
            this.serieName = serieName;
        }

        public void setGenre(String[] genres) {
            this.genres = genres;
        }

>>>>>>> d1661c515fc3bf0af3f53819a9fcba8e9c4825c1
        public void setYear(String year) {
            this.year = year;
        }

<<<<<<< HEAD
        public void popupInfo(JFrame frame) {

            //JOptionPane info = new JOptionPane("hej",);
            JOptionPane.showMessageDialog(frame,"Serie navn "+name+"\n Genre: "+genreToString()+
                            "\n Aktive år: "+year+"\n Bedømmese: "+rating,
                    name,JOptionPane.PLAIN_MESSAGE);
        }

=======
        public void setRating(double rating) {
            this.rating = rating;
        }

        public void setSE(String[] SE) {
            this.SE = SE;
        }

        public void print() {
            System.out.println(serieName + year + genres[0] + rating + SE[0]);
        }
>>>>>>> d1661c515fc3bf0af3f53819a9fcba8e9c4825c1
    }
