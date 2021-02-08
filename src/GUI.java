import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class GUI {

    //Dette er den JFrame som bliver vist når programmet kørre
    private JFrame frame;
    //Denne ArrayList indeholder alle filmne
    private ArrayList<Content> films;
    //Denne ArrayList indeholder alle serierne
    private ArrayList<Content> series;
    //Denne MenuGui håndtere alt hvad menuen kan
    private MenuGui menuGui;




    Dimension menuDimension;
    Dimension contentDimension;

    GUI() {
        frame = new JFrame("GUI");
        films = new ArrayList<Content>();
        series = new ArrayList<Content>();
        menuGui = new MenuGui(frame.getContentPane(), films , series );
        menuGui = new MenuGui(frame.getContentPane(), films, series);
        makeFrame();
    }

    private void makeFrame() {

        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuDimension = new Dimension(frame.getWidth(), 100);
        contentDimension = new Dimension(frame.getWidth(), frame.getHeight() - 135);

        Container contentPane = frame.getContentPane();
        contentPane.setBackground(new Color(155, 5, 5));
        contentPane.setLayout(new BorderLayout());


        drawMenuGui(contentPane);
        drawFilms(contentPane);
        frame.setVisible(true);
    }

    private void drawMenuGui(Container contentPane) {

        JLabel menuGuiPanel = new JLabel();

        menuGuiPanel.setMaximumSize(menuDimension);
        menuGuiPanel.setPreferredSize(menuDimension);
        menuGuiPanel.setMinimumSize(menuDimension);

        menuGuiPanel.setLayout(new GridLayout(1, 5));
        menuGuiPanel.setBackground(Color.black);
        menuGuiPanel.setBorder(new LineBorder(Color.red, 5));

        menuGuiPanel.add(menuGui.getMenuContainer());

        contentPane.add(menuGuiPanel, BorderLayout.NORTH);
    }

    private void drawFilms(Container contentPane) {

        //herunder indsteillles det JPanel som filmområdet bilver tejnet på
        JPanel mediaPanel = new JPanel();
        mediaPanel.setMaximumSize(contentDimension);
        mediaPanel.setPreferredSize(contentDimension);
        mediaPanel.setMinimumSize(contentDimension);
        mediaPanel.setLayout(new GridLayout(1, 5));
        mediaPanel.setBackground(Color.black);
        mediaPanel.setBorder(new LineBorder(Color.red, 5));
        Container filmScrollPane = new ScrollPane();

        filmScrollPane.setSize(contentPane.getWidth(), 850);
        filmScrollPane.setBackground(Color.black);

        Container filmBox = new Container();
        filmBox.setLayout(new GridLayout(0, 8, 10, 10));

        for (Content film : films) {

            //herunder bliver der tilsat en action til newFilm
            film.addActionListener(
                    e -> film.popupInfo(frame)
            );
            filmBox.add(film);
        }
        for (Content serie : series) {

            //herunder bliver der tilsat en action til newFilm
            serie.addActionListener(
                    e -> serie.popupInfo(frame)
            );
        }

        filmScrollPane.add(filmBox);
        mediaPanel.add(filmScrollPane);
        contentPane.add(mediaPanel, BorderLayout.SOUTH);
    }


}
