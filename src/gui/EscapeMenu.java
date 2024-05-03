package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JMenuBar;

public class EscapeMenu extends JMenuBar {

    public EscapeMenu() {
        setSize(new Dimension(200, 300));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150);
        setLayout(null);
        setVisible(false);
        setEnabled(false);
    }
}
