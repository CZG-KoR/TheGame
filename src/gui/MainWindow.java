package gui;

import map.Map;
import map.Player;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MainWindow {
    
    static JLabel atTurn;
    static JPanel minimap;

    private MainWindow() {
        throw new IllegalStateException("Utility class should not be instantiated.");
    }

    public static void init(Map m, Player[] players) {
        final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

        JFrame window = new JFrame();
        window.setTitle("TheGame");
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 3
        window.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));// zentriert
        window.setExtendedState(Frame.MAXIMIZED_BOTH);// fullsize
        window.setUndecorated(true);//entfernt topbar von window (fullscreen)
        window.setFocusable(true);//für KeyListener
        window.setFocusTraversalKeysEnabled(false);

        // Zeichenebenen anelegen
        JLayeredPane layer = new JLayeredPane();
        layer.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // bar vor Spielfeld initialisieren
        Bar b = new Bar(WIDTH, HEIGHT);
        // Element, Ebenenwert (je höher, desto weiter oben)
        layer.add(b, 2000);
        layer.add(b.foodAmount, 2500);
        layer.add(b.woodAmount, 2500);
        layer.add(b.motivationAmount, 2500);
        layer.add(b.stoneAmount, 2500);
        //CloseButton; schließt Bar
        JButton close = b.closeBarButton();
        layer.add(close);
        //OpenButton; öffnet Bar, wenn Bar offen: OpenButton nicht aktiviert und unsichtbar, wenn geschlossen: sichtbar
        JButton open = b.openBarButton();
        open.setVisible(false);
        open.setEnabled(false);
        layer.add(open);
           
        // Spielfeld
        Tilemap tM = new Tilemap(WIDTH, HEIGHT, m, b, players);
        layer.add(tM, 1000);
        
        
        // Actin Listener für open und close Buttons
        close.addActionListener( (ActionEvent e) -> {
            b.setVisible(false);
            b.setEnabled(false);
            close.setVisible(false);
            close.setEnabled(false);
            open.setVisible(true);
            open.setEnabled(true);
            tM.limitCamera();
        });
        open.addActionListener( (ActionEvent e) -> {
            b.setVisible(true);
            b.setEnabled(true);
            open.setVisible(false);
            open.setEnabled(false);
            close.setVisible(true);
            close.setEnabled(true);
            tM.barOpened();
        });
       
        // Anzeige wer am Zug ist    
        atTurn = b.atTurn();

        layer.add(atTurn,4000);
        layer.setLayer(atTurn, 4000);

        

        // ----------------------------------------------------//
        // Timer für Zeichnen der Map -> Zeichnung jetzt unabhängig von StatBar
        Timer t = new Timer(10, e -> tM.repaint());
        
        
        //MenuBar
        EscapeMenu eM = new EscapeMenu();
        layer.add(eM, 3000);
        layer.setLayer(eM,9999);
        JButton eB = new JButton();
        eB.setSize(200,100);
        eB.setLocation(0,0);
        eB.setText("(ノಠ益ಠ)ノ彡┻━┻");
        eB.setVisible(true);
        eB.setEnabled(true);
        eM.add(eB);
        eB.addActionListener( (ActionEvent e) -> {
            window.dispose();
            System.exit(0);
        });
        Action keyListener = new Action() {
            @Override
            public Object getValue(String key) {
                return "ESC";
            }

            @Override
            public void putValue(String key, Object value) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setEnabled(boolean b) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
                throw new UnsupportedOperationException();
            }

            boolean eMopen = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!eMopen) {
                    t.stop();
                    eM.setVisible(true);
                    eM.setEnabled(true);
                    close.setEnabled(false);
                    open.setEnabled(false);
                    eMopen = true;
                }else {
                    eM.setVisible(false);
                    eM.setEnabled(false);
                    close.setEnabled(true);
                    open.setEnabled(true);
                    eMopen = false;
                    t.restart();
                }
            }
        };  
        //Inputebene für KeyStrokes etc.
        JPanel inputPanel = (JPanel) window.getContentPane();
        window.getContentPane().add(layer);
        inputPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"EscapeButton");
        inputPanel.getActionMap().put("EscapeButton",keyListener);

        window.addMouseListener(tM);
        window.addMouseMotionListener(tM);

        layer.setVisible(true);
        window.add(layer);
        // immer unter allen Darstellungselementen
        window.setVisible(true);
        window.pack();

        t.start();
    }
    
}
