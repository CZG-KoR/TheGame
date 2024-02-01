package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import launcher.Start;
import map.Player;


public class Bar extends JInternalFrame {

    int width, height;

    public Bar(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        JTabbedPane tabs = new JTabbedPane(SwingConstants.TOP);

        this.setSize(width, height / 3);
        this.setLocation(0, height - height / 3);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // entfernt leiste bei tabbedpane
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        Color internalBorderColor = new Color(85, 53, 5);
        this.setBorder(BorderFactory.createLineBorder(internalBorderColor, 4));

        // widht -8 für "sauberen" Rahmen
        tabs.setPreferredSize(new Dimension(width - 100, height / 3));
        JPanel panelBuildings = new JPanel();
        panelBuildings.setBackground(Color.GRAY);
        JPanel panelTroops = new JPanel();
 
        // Tab für das Beenden eines Zuges
        JPanel panelTurn = new JPanel();
        JButton EndTurn = new JButton("End Turn");
        EndTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       // Runden Button Turn End noch init
       // Das in ActionPerformed vom ButtonTurnEnd übernehmen
                Player[] players = Start.getPlayers();
                for (int i = 0; i < players.length; i++) {
                    if(players[i].isAtTurn()){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                        }
                        players[i].setAtTurn(false);
                        if(i+1 == players.length){
                            players[0].setAtTurn(true);
                            MainWindow.AtTurn.setText("Am Zug:" + players[0].getPlayername());
                            MainWindow.AtTurn.setBackground(players[0].getColour());
                        }
                        else{
                            players[i+1].setAtTurn(true);
                            MainWindow.AtTurn.setText("Am Zug:" + players[i+1].getPlayername());
                            MainWindow.AtTurn.setBackground(players[i+1].getColour());
                        }
                        break;
                    }
                }
                        for (int i = 0; i < players.length; i++) {
                            Player.checkElements(players[i]);
                        }
                
            }
        });
        panelTurn.add(EndTurn);
        
        
        tabs.addTab("Gebäude", panelBuildings);
        tabs.addTab("Truppen", panelTroops);
        tabs.addTab("End Turn", panelTurn);
        tabs.setVisible(true);

        this.add(tabs);
        this.requestFocus();

        // immer am Ende
        this.setVisible(true);
    }

    public JButton closeBarButton() {
        JButton close = new JButton();
        close.setLocation(width - 50, height - height / 3 - 20);
        close.setText("v");
        close.setSize(50, 20);
        close.setVisible(true);
        return close;
    }

    public JButton openBarButton() {
        JButton open = new JButton();
        open.setLocation(width - 50, height - 20);
        open.setText("^");
        open.setSize(50, 20);
        open.setVisible(true);
        return open;
    }
    
    // Anzeige wer am Zug ist, noch Text zentrieren
    public JLabel AtTurn(){
        JLabel AtTurn = new JLabel();
        AtTurn.setLocation(width/2 - 100, 20);
        AtTurn.setSize(200, 40);
        AtTurn.setOpaque(true);
        AtTurn.setBackground(Color.cyan);
        AtTurn.setText("Am Zug: Spieler 1");
        AtTurn.setVisible(true);
        return AtTurn;
    }
    
}
