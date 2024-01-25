/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Map.Map;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

/**
 *
 * @author administrator
 */
public class MainWindow {

    public MainWindow(Map m) {

        //komplette Größe des Bildschirms
        final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

        JFrame window = new JFrame();
        window.setTitle("TheGame");
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 3
        window.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));//zentriert
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);//fullsize
        //window.setUndecorated(true);//entfernt topbar von window (fullscreen)
        
        //Zeichenebenen anelegen
        JLayeredPane layer = new JLayeredPane();
        layer.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // bar vor Spielfeld initialisieren
        Bar b = new Bar(WIDTH, HEIGHT);
        // Element, Ebenenwert (je höher, desto weiter oben)
        layer.add(b,2000);

        // Spielfeld
        Tilemap tM = new Tilemap(WIDTH, HEIGHT, m);
        layer.add(tM, 1000);
        
        layer.setVisible(true);      
        window.add(layer);
        
        
        //immer unter allen Darstellungselementen
        window.setVisible(true);
        window.pack();
        
        //----------------------------------------------------//
        // Timer für Zeichnen der Map -> Zeichnung jetzt unabhängig von StatBar  
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tM.repaint();
            }
        });
        
        t.start();

    }
}
