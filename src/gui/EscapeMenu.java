/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JMenuBar;

/**
 *
 * @author guest-c7rmqm
 */
public class EscapeMenu extends JMenuBar {

    public EscapeMenu() {
        setSize(new Dimension(200, 300));
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150);
        setVisible(false);
        setEnabled(false);
    }
}
