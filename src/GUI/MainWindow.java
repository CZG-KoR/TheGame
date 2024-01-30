package gui;

import map.Map;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class MainWindow {

    public MainWindow(Map m) {

        // komplette Größe des Bildschirms
        final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

        JFrame window = new JFrame();
        window.setTitle("TheGame");
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 3
        window.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));// zentriert
        window.setExtendedState(Frame.MAXIMIZED_BOTH);// fullsize
        // window.setUndecorated(true);//entfernt topbar von window (fullscreen)

        // Zeichenebenen anelegen
        JLayeredPane layer = new JLayeredPane();
        layer.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // bar vor Spielfeld initialisieren
        Bar b = new Bar(WIDTH, HEIGHT);
        // Element, Ebenenwert (je höher, desto weiter oben)
        layer.add(b, 2000);
        // Spielfeld
        Tilemap tM = new Tilemap(WIDTH, HEIGHT, m);
        layer.add(tM, 1000);

        window.addMouseListener(tM);
        window.addMouseMotionListener(tM);

        layer.setVisible(true);
        window.add(layer);

        // immer unter allen Darstellungselementen
        window.setVisible(true);
        window.pack();

        // ----------------------------------------------------//
        // Timer für Zeichnen der Map -> Zeichnung jetzt unabhängig von StatBar
        Timer t = new Timer(10, e -> tM.repaint());

        t.start();
    }
}
