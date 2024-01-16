package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author guest-otfeii
 */
public class GUI extends JPanel{
    //komplette Größe des Bildschirms
    static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * @param args the command line arguments
     */
    
    public GUI() {

        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setLocation(0, 0);
        this.setVisible(true);

    }
    
    public static void main(String[] args) {
        GUI g = new GUI();
        JFrame window = new JFrame();
        window.setTitle("TheGame");
        window.setPreferredSize(new Dimension(width, height));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 3
        window.setLayout(new FlowLayout());
        
        window.add(g);
        window.setVisible(true);
        
        
        //immer ganz unten
        window.pack();
    }
    
    //painComponent; erst schwarzer Hintergrund
    @Override
    public void paintComponent(Graphics g) {
        
        }
    
}
