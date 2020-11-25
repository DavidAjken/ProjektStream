import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GUI {
    protected JFrame frame;

    GUI(){
        makeFrame();
    }
    private void makeFrame()    {
        frame = new JFrame("GUI");
        frame.setSize(400,400);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1,2));

        frame.setVisible(true);
    }
}
