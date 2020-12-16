
    import javax.swing.*;
    public class Serier extends Content{
        protected String year;
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

}
