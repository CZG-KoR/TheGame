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
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import Map.Map;
import Map.Terrain;
import Map.ImageLoader;

/**
 *
 * @author guest-otfeii
 */
public class GUI extends JPanel{
    //komplette Größe des Bildschirms
    static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    static Map M;

    static JInternalFrame internal = new JInternalFrame();
    /**
     */
    
    public GUI() {

        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setLocation(0, 0);
        this.setVisible(true);

    }
    
    public static void main(String[] args) {
        ImageLoader imageload = new ImageLoader();
        GUI g = new GUI();
        JFrame window = new JFrame();
        window.setTitle("TheGame");
        window.setPreferredSize(new Dimension(width, height));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 3
        window.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));//zentriert
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);//fullsize
//        window.setUndecorated(true);//entfernt topbar von window (fullscreen)
        window.add(g);
        window.setVisible(true);
        window.add(internal);
        
        
        //immer ganz unten
        window.pack();
        M = new Map(50,50);
    }
    
    
    //painComponent; erst schwarzer Hintergrund
    @Override
    public void paintComponent(Graphics g) {
            for (int i = 0; i < M.getWidth(); i++) {
                for (int j = 0; j < M.getHeight(); j++) {
                    g.drawImage(M.getTerrainPicture(i, j), 64*i, 64*j, null);
                }
            }
            g.drawImage(Toolkit.getDefaultToolkit().getImage("src/GUI/res/ResourceBar.png"), 0, 0, null);
            botBar();
       }
    
    private static void botBar(){
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        
        internal.setSize(width, height/3);
        internal.setLocation(0,height - height/3);
        internal.setVisible(true);
        internal.show();
        //entfernt leiste bei tabbedpane
        ((javax.swing.plaf.basic.BasicInternalFrameUI)internal.getUI()).setNorthPane(null);
        Color InternalBorderColor = new Color(85, 53, 5);
        internal.setBorder(BorderFactory.createLineBorder(InternalBorderColor,4));
        
        tabs.setSize(width,height/3);
        JPanel panelBuildings = new JPanel();
        panelBuildings.setBackground(Color.GRAY);
        JPanel panelTroops = new JPanel();
        tabs.addTab("Gebäude", panelBuildings);
        tabs.addTab("Truppen", panelTroops);
        tabs.setVisible(true);
        
        internal.add(tabs);
        
    }
}
