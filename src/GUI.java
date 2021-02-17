import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class GUI {

    //Dette er den JFrame som bliver vist når programmet kørre
    private JFrame frame;

    //Denne MenuGui håndtere alt hvad menuen kan
    private MenuGui menuGui;

    private ArrayList<JButton> films;

    private ArrayList<JButton> series;

    Dimension menuDimension;
    Dimension contentDimension;

    InfoHandeler infoHandeler;

    GUI(InfoHandeler infoHandeler) {
        films = new ArrayList<>();
        series = new ArrayList<>();
        this.infoHandeler = infoHandeler;
        setups(infoHandeler);

        frame = new JFrame("GUI");

        setups(infoHandeler);

        menuGui = new MenuGui(frame.getContentPane(), films, series, infoHandeler);

        makeFrame();
    }

    private void setups(InfoHandeler infoHandeler) {
        setupFilmsButtons();
        setupSeriesButtons();

        setupPopUp();
    }

    public void setupFilmsButtons() {
        makeContentButtons(infoHandeler.getFilms(), films);
    }

    public void setupSeriesButtons() {
        makeContentButtons(infoHandeler.getSeries(), series);
    }

    private void makeContentButtons(Content[] contents, ArrayList<JButton> buttons) {

        for (Content content : contents) {
            JButton contentButton = new JButton();
            Dimension iconDimention = new Dimension(content.getImg().getIconWidth(), content.getImg().getIconHeight() + 25);

            contentButton.setName(content.getName());
            contentButton.setIcon(content.getImg());
            contentButton.setMaximumSize(iconDimention);
            contentButton.setPreferredSize(iconDimention);
            contentButton.setMinimumSize(iconDimention);
            contentButton.setHorizontalAlignment(0);
            contentButton.setBackground(new Color(0, 0, 0));
            contentButton.setBorder(new LineBorder(Color.red, 1));

            contentButton.setText(contentButton.getName());
            contentButton.setHorizontalTextPosition(0);
            contentButton.setVerticalTextPosition(3);
            contentButton.setForeground(new Color(255, 255, 255));
            buttons.add(contentButton);
        }
    }

    public void setupPopUp() {
        for (JButton film : films) {
            film.addActionListener(e -> {
                Film filmInfo = infoHandeler.getFilm(film.getText());
                JOptionPane.showMessageDialog(frame, filmInfo.popupInfoString(),
                        film.getText(), JOptionPane.PLAIN_MESSAGE);
            });
        }
        /*
        for (JButton serie : series) {
            serie.addActionListener(e -> {
                Series serieInfo = infoHandeler.getSerie(serie.getText());
                JOptionPane.showMessageDialog(frame, serieInfo.popupInfoString(),
                        serie.getText(), JOptionPane.PLAIN_MESSAGE);
            });
        }
         */
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
        for (JButton film : films) {
            filmBox.add(film);
        }

        filmScrollPane.add(filmBox);
        mediaPanel.add(filmScrollPane);
        contentPane.add(mediaPanel, BorderLayout.SOUTH);
    }

}