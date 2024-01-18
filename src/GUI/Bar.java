/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Bar extends JInternalFrame{

    public Bar(int width, int height) {
        super();
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);

        this.setSize(width, height/3);
        this.setLocation(0,height - height/3);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));       
        
        //entfernt leiste bei tabbedpane
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Color InternalBorderColor = new Color(85, 53, 5);
        this.setBorder(BorderFactory.createLineBorder(InternalBorderColor,4));
        
        // widht -8 für "sauberen" Rahmen
        tabs.setPreferredSize(new Dimension(width-10,height/3));
        JPanel panelBuildings = new JPanel();
        panelBuildings.setBackground(Color.GRAY);
        JPanel panelTroops = new JPanel();
        tabs.addTab("Gebäude", panelBuildings);
        tabs.addTab("Truppen", panelTroops);
        tabs.setVisible(true);
        
        this.add(tabs);
        this.requestFocus();
        
        // immer am Ende
        this.setVisible(true);
    }   
    
}
