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


    GUI(InfoHandeler infoHandeler) {
        films = new ArrayList<>();
        series = new ArrayList<>();
        setups(infoHandeler);


        frame = new JFrame("GUI");
        menuGui = new MenuGui(frame.getContentPane(), films, series, infoHandeler);

        makeFrame();
        if (infoHandeler.hasErrores()){
            makeErrormessiges(infoHandeler.getErrorMessiges());
        }
    }

    private void makeErrormessiges(ArrayList<String> errorMessiges) {
        for (String errorMessige: errorMessiges) {
            JOptionPane.showMessageDialog(frame, errorMessige,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setups(InfoHandeler infoHandeler) {
        setupFilmsButtons(infoHandeler);
        setupSeriesButtons(infoHandeler);
    }

    public void setupFilmsButtons(InfoHandeler infoHandeler) {
        makeContentButtons(infoHandeler.getFilms(), films);
    }

    public void setupSeriesButtons(InfoHandeler infoHandeler) {
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
            contentButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, content.popupInfoString(),
                    content.getName(), JOptionPane.PLAIN_MESSAGE));
        }
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