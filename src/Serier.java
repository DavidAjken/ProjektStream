
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
        //get og set metoder til serier

        public String getName() {
            return name;
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

        public void setName(String name) {
            this.name = name;
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
        public ImageIcon getImg() {
            return img;
        }
        public void setImg(ImageIcon img) {
            setIcon(img);
            this.img = img;

            //herunder bliver Serie's størrelse sat
            setMaximumSize(new Dimension(img.getIconWidth(), img.getIconHeight() + 25));
            setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight() + 25));
            setMinimumSize(new Dimension(img.getIconWidth(), img.getIconHeight() + 25));
        }

        private void setupLayout(){
            setHorizontalAlignment(0);
            setBackground(new Color(0, 0, 0));
            setBorder(new LineBorder(Color.red, 1));

            //herunder håndteres tegningen af texten
            setText(getName());
            setHorizontalTextPosition(0);
            setVerticalTextPosition(3);
            setForeground(new Color(255, 255, 255));

        }

        public void print() {
            System.out.println(name + year + genres[0] + rating + SE[0]);
        }
    }
