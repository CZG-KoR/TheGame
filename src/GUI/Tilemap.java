package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import Map.Map;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author guest-otfeii
 */
public class Tilemap extends JPanel {

    Map m;

    /**
     * @param m
     */
    public Tilemap(int width, int height, Map m) {
        super();
        this.m = m;
        this.setSize(new Dimension(width, height));
        this.setLocation(0, 0);
        this.setVisible(true);

        //Testfall
        m.setT(3, 3, "water");

    }

    //painComponent; erst schwarzer Hintergrund
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < m.getWidth(); i++) {
            for (int j = 0; j < m.getHeight(); j++) {
                g.drawImage(m.getTerrainPicture(i, j), 64 * i, 64 * j, null);
            }
        }
        g.drawImage(Toolkit.getDefaultToolkit().getImage("src/GUI/res/ResourceBar.png"), 0, 0, null);
    }
}
