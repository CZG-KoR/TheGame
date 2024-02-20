package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
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
        tabs.setPreferredSize(new Dimension(width - 8, height / 3));
        
        //Tab für Gebeude
        JPanel panelBuildings = new JPanel();
        panelBuildings.setLayout(null);
        
        JButton barracksButton = new JButton();
        barracksButton.setIcon(new ImageIcon("src/gui/res/"));
        barracksButton.setText("Barracks");
        barracksButton.setVerticalTextPosition(JButton.BOTTOM);
        barracksButton.setHorizontalTextPosition(JButton.CENTER);
        barracksButton.setSize(new Dimension(200,200));
        barracksButton.setLocation(0,0);
           barracksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(barracksButton);
        
        JButton fishingButton = new JButton();
        fishingButton.setIcon(new ImageIcon("src/gui/res/"));
        fishingButton.setText("Fish");
        fishingButton.setVerticalTextPosition(JButton.BOTTOM);
        fishingButton.setHorizontalTextPosition(JButton.CENTER);
        fishingButton.setSize(new Dimension(200,200));
        fishingButton.setLocation(200, 0);
           fishingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(fishingButton);
        
        JButton lumberjackButton = new JButton();
        lumberjackButton.setIcon(new ImageIcon("src/gui/res/"));
        lumberjackButton.setText("Lumberjack");
        lumberjackButton.setVerticalTextPosition(JButton.BOTTOM);
        lumberjackButton.setHorizontalTextPosition(JButton.CENTER);
        lumberjackButton.setSize(new Dimension(200,200));
        lumberjackButton.setLocation(400,0);
           lumberjackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(lumberjackButton);
        
        JButton mineButton = new JButton();
        mineButton.setIcon(new ImageIcon("src/gui/res/"));
        mineButton.setText("Mine");
        mineButton.setVerticalTextPosition(JButton.BOTTOM);
        mineButton.setHorizontalTextPosition(JButton.CENTER);
        mineButton.setSize(new Dimension(200,200));
        mineButton.setLocation(600,0);
           mineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(mineButton);
        
        JButton theatreButton = new JButton();
        theatreButton.setIcon(new ImageIcon("src/gui/res/"));
        theatreButton.setText("Theatre");
        theatreButton.setVerticalTextPosition(JButton.BOTTOM);
        theatreButton.setHorizontalTextPosition(JButton.CENTER);
        theatreButton.setSize(new Dimension(200,200));
        theatreButton.setLocation(800, 0);
           theatreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(theatreButton);
        
        JButton tower = new JButton();
        tower.setIcon(new ImageIcon("src/gui/res/"));
        tower.setText("Tower");
        tower.setVerticalTextPosition(JButton.BOTTOM);
        tower.setHorizontalTextPosition(JButton.CENTER);
        tower.setSize(new Dimension(200,200));
        tower.setLocation(1000,0);
           tower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(tower);
        
        JButton townhallButton = new JButton();
        townhallButton.setIcon(new ImageIcon("src/gui/res/"));
        townhallButton.setText("Townhall");
        townhallButton.setVerticalTextPosition(JButton.BOTTOM);
        townhallButton.setHorizontalTextPosition(JButton.CENTER);
        townhallButton.setSize(new Dimension(200,200));
        townhallButton.setLocation(1200,0);
           townhallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(townhallButton);
        
        JButton wheatfieldButton = new JButton();
        wheatfieldButton.setIcon(new ImageIcon("src/gui/res/"));
        wheatfieldButton.setText("Wheatfield");
        wheatfieldButton.setVerticalTextPosition(JButton.BOTTOM);
        wheatfieldButton.setHorizontalTextPosition(JButton.CENTER);
        wheatfieldButton.setSize(new Dimension(200,200));
        wheatfieldButton.setLocation(1400,0);
           wheatfieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(wheatfieldButton);
        
        
        
        
        //Tab für die Auswahl von Truppen
        JPanel panelTroops = new JPanel();
        panelTroops.setLayout(null);
        
        JButton warriorButton = new JButton();
        warriorButton.setIcon(new ImageIcon("src/gui/res/warrior1/idle/idle_1.png"));
        warriorButton.setText("test");
        warriorButton.setVerticalTextPosition(JButton.BOTTOM);
        warriorButton.setHorizontalTextPosition(JButton.CENTER);
        warriorButton.setSize(new Dimension(200,200));
        warriorButton.setLocation(0,0);
           warriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("warriorButton pressed");
            }
        });
        panelTroops.add(warriorButton);
        
        JButton archer = new JButton();
        archer.setIcon(new ImageIcon("src/gui/res/"));
        archer.setText("archer");
        archer.setVerticalTextPosition(JButton.BOTTOM);
        archer.setHorizontalTextPosition(JButton.CENTER);
        archer.setSize(new Dimension(200,200));
        archer.setLocation(200,0);
           archer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("archer pressed");
            }
        });
        panelTroops.add(archer);
        
        JButton catapult = new JButton();
        catapult.setIcon(new ImageIcon("src/gui/res/"));
        catapult.setText("catapult");
        catapult.setVerticalTextPosition(JButton.BOTTOM);
        catapult.setHorizontalTextPosition(JButton.CENTER);
        catapult.setSize(new Dimension(200,200));
        catapult.setLocation(400,0);
           warriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("catapult pressed");
            }
        });
        panelTroops.add(catapult);
        
        JButton horse = new JButton();
        horse.setIcon(new ImageIcon("src/gui/res/"));
        horse.setText("horse");
        horse.setVerticalTextPosition(JButton.BOTTOM);
        horse.setHorizontalTextPosition(JButton.CENTER);
        horse.setSize(new Dimension(200,200));
        horse.setLocation(600,0);
           horse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                       System.out.println("horse pressed");
            }
        });
        panelTroops.add(horse);
        
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

    public BasicArrowButton closeBarButton() {
        BasicArrowButton close = new BasicArrowButton(BasicArrowButton.SOUTH);
        close.setLocation(width - 50, height - height / 3 - 20);
        close.setText("v");
        close.setSize(50, 20);
        close.setVisible(true);
        return close;
    }

    public BasicArrowButton openBarButton() {
        BasicArrowButton open = new BasicArrowButton(BasicArrowButton.NORTH);
        open.setLocation(width - 50, height - 20);
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
