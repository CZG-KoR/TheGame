/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;

/**
 *
 * @author guest-c7rmqm
 */
public class EscapeMenu extends JMenuBar {

    JMenuBar mB;

    public EscapeMenu() {
        mB = new JMenuBar();
        mB.setSize(200, 300);
        mB.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150);
        mB.setVisible(false);
        mB.setEnabled(false);
    }
}
