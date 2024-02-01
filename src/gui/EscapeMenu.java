/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JMenuBar;

/**
 *
 * @author guest-c7rmqm
 */
public class EscapeMenu extends JMenuBar {

    JMenuBar mB;

    public EscapeMenu() {
        mB = new JMenuBar();
        //mB.setSize(200, 300);
        mB.setPreferredSize(new Dimension(200, 300));
        mB.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150);
        mB.setVisible(false);
        mB.setEnabled(false);
    }
    
    public JButton escapeButton(){
        JButton eB = new JButton();
        eB.setSize(50,50);
        eB.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 150, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);
        eB.setVisible(true);
        eB.setEnabled(true);
        mB.add(eB);
        return eB;
    }
}
