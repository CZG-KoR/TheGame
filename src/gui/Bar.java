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
import building.Building;
import building.Barracks;
import building.Fishinghouse;
import building.Lumberjack;
import building.Mine;
import building.Theatre;
import building.Tower;
import building.Wheatfield;
import java.awt.Image;
import java.util.HashMap;
import map.Map;

public class Bar extends JInternalFrame {

    int width, height;
    static int Placement = 0;
    HashMap<Integer, ImageIcon> icons;
    JLabel foodAmount = new JLabel("0");
    JLabel woodAmount = new JLabel("0");
    JLabel motivationAmount = new JLabel("0");
    JLabel stoneAmount = new JLabel("0");

    public Bar(int width, int height, Map m) {
        super();
        this.width = width;
        this.height = height;
        JTabbedPane tabs = new JTabbedPane(SwingConstants.TOP);
        
        // Icons laden
        icons = new HashMap<>();
        
        icons.put(2, new ImageIcon("src/gui/res/building/fishinghouse.png"));
        icons.put(4, new ImageIcon("src/gui/res/building/mine.png"));
        icons.put(7, new ImageIcon("src/gui/res/building/townhall.png"));
        icons.put(8, new ImageIcon("src/gui/res/building/windmill.png"));
        
        icons.put(9, new ImageIcon("src/gui/res/warrior1/idle/idle_1.png"));
        icons.put(10, new ImageIcon("src/gui/res/archer/idel/archer_idel1.png"));
        
        icons.put(0, new ImageIcon("src/gui/res/"));
        
        

        this.setSize(width, height / 3);
        this.setLocation(0, height - height / 3);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        //ressourceBar
        icons.put(99, new ImageIcon("src/gui/res/resources/1resourceBar_left.png"));
        icons.put(98, new ImageIcon("src/gui/res/resources/2resourceBar_mid.png"));
        icons.put(97, new ImageIcon("src/gui/res/resources/3resourceBar_right.png"));
        icons.put(96, new ImageIcon("src/gui/res/resources/4food.png"));
        icons.put(95, new ImageIcon("src/gui/res/resources/5wood.png"));
        icons.put(94, new ImageIcon("src/gui/res/resources/6motivation.png"));
        icons.put(93, new ImageIcon("src/gui/res/resources/7stone.png"));
        foodAmount.setLocation(54, 27);
        foodAmount.setSize(100, 10);
        foodAmount.setForeground(new java.awt.Color(255,0,0));
        foodAmount.setBackground(Color.red);
        foodAmount.setVisible(true);
        //foodAmount.setEnabled(false);
        
        woodAmount.setLocation(118, 27);
        woodAmount.setSize(100, 10);
        woodAmount.setForeground(new java.awt.Color(255,0,0));
        woodAmount.setBackground(Color.red);
        woodAmount.setVisible(true);
        //woodAmount.setEnabled(false);
        
        stoneAmount.setLocation(266, 27);
        stoneAmount.setSize(100, 10);
        stoneAmount.setForeground(new java.awt.Color(255,0,0));
        stoneAmount.setBackground(Color.red);
        stoneAmount.setVisible(true);
        //stoneAmount.setEnabled(false);
        
        motivationAmount.setLocation(185, 27);
        motivationAmount.setSize(100, 10);
        motivationAmount.setForeground(new java.awt.Color(255,0,0));
        motivationAmount.setBackground(Color.red);
        motivationAmount.setVisible(true);
        //motivationAmount.setEnabled(false);
        
        

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
        barracksButton.setIcon(icons.get(0));
        barracksButton.setText("Barracks");
        barracksButton.setVerticalTextPosition(JButton.BOTTOM);
        barracksButton.setHorizontalTextPosition(JButton.CENTER);
        barracksButton.setSize(new Dimension(200, 200));
        barracksButton.setLocation(0, 0);
        barracksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("barracksButton pressed");
                if (Barracks.buildable(Player.getAtTurn())) {
                Placement = 1;    
                }
                Placement = 0;
            }
        });
        panelBuildings.add(barracksButton);

        JButton fishingButton = new JButton();
        fishingButton.setIcon(icons.get(2));
        fishingButton.setText("Fish");
        fishingButton.setVerticalTextPosition(JButton.BOTTOM);
        fishingButton.setHorizontalTextPosition(JButton.CENTER);
        fishingButton.setSize(new Dimension(200, 200));
        fishingButton.setLocation(200, 0);
        fishingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("warriorButton pressed");
                if (Fishinghouse.buildable(Player.getAtTurn())) {
                Placement = 2;    
                }
                Placement = 0;
            }
        });
        panelBuildings.add(fishingButton);

        JButton lumberjackButton = new JButton();
        lumberjackButton.setIcon(icons.get(0));
        lumberjackButton.setText("Lumberjack");
        lumberjackButton.setVerticalTextPosition(JButton.BOTTOM);
        lumberjackButton.setHorizontalTextPosition(JButton.CENTER);
        lumberjackButton.setSize(new Dimension(200, 200));
        lumberjackButton.setLocation(400, 0);
        lumberjackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("warriorButton pressed");
                if (Lumberjack.buildable(Player.getAtTurn())) {
                Placement = 3;    
                }
                Placement = 0;
            }
        });
        panelBuildings.add(lumberjackButton);

        JButton mineButton = new JButton();
        mineButton.setIcon(icons.get(4));
        mineButton.setText("Mine");
        mineButton.setVerticalTextPosition(JButton.BOTTOM);
        mineButton.setHorizontalTextPosition(JButton.CENTER);
        mineButton.setSize(new Dimension(200, 200));
        mineButton.setLocation(600, 0);
        mineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("warriorButton pressed");
                if (Mine.buildable(Player.getAtTurn())) {
                Placement = 4;    
                }
                Placement = 0;
            }
        });
        panelBuildings.add(mineButton);

        JButton theatreButton = new JButton();
        theatreButton.setIcon(icons.get("empty"));
        theatreButton.setText("Theatre");
        theatreButton.setVerticalTextPosition(JButton.BOTTOM);
        theatreButton.setHorizontalTextPosition(JButton.CENTER);
        theatreButton.setSize(new Dimension(200, 200));
        theatreButton.setLocation(800, 0);
        theatreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("warriorButton pressed");
                if (Theatre.buildable(Player.getAtTurn())) {
                Placement = 5;    
                }
                Placement = 0;
            }
        });
        panelBuildings.add(theatreButton);

        JButton tower = new JButton();
        tower.setIcon(icons.get(0));
        tower.setText("Tower");
        tower.setVerticalTextPosition(JButton.BOTTOM);
        tower.setHorizontalTextPosition(JButton.CENTER);
        tower.setSize(new Dimension(200, 200));
        tower.setLocation(1000, 0);
        tower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("warriorButton pressed");
                if (Tower.buildable(Player.getAtTurn())) {
                Placement = 6;    
                }
                Placement = 0;
            }
        });
        panelBuildings.add(tower);

        JButton townhallButton = new JButton();
        townhallButton.setIcon(icons.get(6));
        townhallButton.setText("Townhall");
        townhallButton.setVerticalTextPosition(JButton.BOTTOM);
        townhallButton.setHorizontalTextPosition(JButton.CENTER);
        townhallButton.setSize(new Dimension(200, 200));
        townhallButton.setLocation(1200, 0);
        townhallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("warriorButton pressed");
                Placement = 7;
            }
        });
        panelBuildings.add(townhallButton);

        JButton wheatfieldButton = new JButton();
        wheatfieldButton.setIcon(icons.get(0));
        wheatfieldButton.setText("Wheatfield");
        wheatfieldButton.setVerticalTextPosition(JButton.BOTTOM);
        wheatfieldButton.setHorizontalTextPosition(JButton.CENTER);
        wheatfieldButton.setSize(new Dimension(200, 200));
        wheatfieldButton.setLocation(1400, 0);
        wheatfieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("warriorButton pressed");
                Placement = 8;
            }
        });
        panelBuildings.add(wheatfieldButton);
        
        JButton windmillButton = new JButton();
        windmillButton.setIcon(icons.get(8));
        windmillButton.setText("Windmill");
        windmillButton.setVerticalTextPosition(JButton.BOTTOM);
        windmillButton.setHorizontalTextPosition(JButton.CENTER);
        windmillButton.setSize(new Dimension(200, 200));
        windmillButton.setLocation(1600, 0);
        windmillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("warriorButton pressed");
            }
        });
        panelBuildings.add(windmillButton);

        //Tab für die Auswahl von Truppen
        JPanel panelTroops = new JPanel();
        panelTroops.setLayout(null);

        JButton warriorButton = new JButton();
        warriorButton.setIcon(icons.get(9));
        warriorButton.setText("test");
        warriorButton.setVerticalTextPosition(JButton.BOTTOM);
        warriorButton.setHorizontalTextPosition(JButton.CENTER);
        warriorButton.setSize(new Dimension(200, 200));
        warriorButton.setLocation(0, 0);
        warriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("warriorButton pressed");
                Placement = 9;
            }
        });
        panelTroops.add(warriorButton);

        JButton archer = new JButton();
        archer.setIcon(icons.get(10));
        archer.setText("archer");
        archer.setVerticalTextPosition(JButton.BOTTOM);
        archer.setHorizontalTextPosition(JButton.CENTER);
        archer.setSize(new Dimension(200, 200));
        archer.setLocation(200, 0);
        archer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("archer pressed");
                Placement = 10;
            }
        });
        panelTroops.add(archer);

        JButton catapult = new JButton();
        catapult.setIcon(icons.get(0));
        catapult.setText("catapult");
        catapult.setVerticalTextPosition(JButton.BOTTOM);
        catapult.setHorizontalTextPosition(JButton.CENTER);
        catapult.setSize(new Dimension(200, 200));
        catapult.setLocation(400, 0);
        catapult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("catapult pressed");
                Placement = 11;
            }
        });
        panelTroops.add(catapult);

        JButton horse = new JButton();
        horse.setIcon(icons.get(0));
        horse.setText("horse");
        horse.setVerticalTextPosition(JButton.BOTTOM);
        horse.setHorizontalTextPosition(JButton.CENTER);
        horse.setSize(new Dimension(200, 200));
        horse.setLocation(600, 0);
        horse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("horse pressed");
                Placement = 12;
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
                
                Bar.setPlacement(0);
                Player[] players = Start.getPlayers();
                for (int i = 0; i < players.length; i++) {
                    if (players[i].isAtTurn()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                        }
                        for (int q = 0; q < players[i].getCharacterAmount(); q++) {
                            players[i].setCharacterMovementAllowed(q);
                        }
                        players[i].setAtTurn(false);
                        if (i + 1 == players.length) {
                            players[0].setAtTurn(true);
                            MainWindow.AtTurn.setText("Am Zug:" + players[0].getPlayername());
                            MainWindow.AtTurn.setBackground(players[0].getColour());
                            //recourcen auffuellen
                            for (int j = 0; j < players[0].getBuildingAmount(); j++) {
                                Building build = players[0].getBuilding(j);
                                switch (build.getbuilding(build)) {
                                    case 1:
                                        Lumberjack lumb = (Lumberjack) (build);
                                        lumb.woodchop(players[0], m);
                                        break;
                                    case 2:
                                        Fishinghouse fish = (Fishinghouse) (build);
                                        fish.fish(players[0]);
                                        break;
                                    case 3:
                                        Wheatfield weed = (Wheatfield) (build);
                                        //lel hier bidde noch ma neu machen
                                        weed.harvest(players[0]);
                                        break;
                                    case 4:
                                        Mine mine = (Mine) (build);
                                        mine.mine(players[0]);
                                        break;
                                }
                            }
                            foodAmount.setText(Integer.toString(players[0].getFood()));
                            woodAmount.setText(Integer.toString(players[0].getWood()));
                            motivationAmount.setText(Integer.toString(players[0].getWood()));
                            stoneAmount.setText(Integer.toString(players[0].getWood()));
                        } else {
                            players[i + 1].setAtTurn(true);
                            MainWindow.AtTurn.setText("Am Zug:" + players[i + 1].getPlayername());
                            MainWindow.AtTurn.setBackground(players[i + 1].getColour());
                            //recourcen auffuellen
                            for (int j = 0; j < players[1].getBuildingAmount(); j++) {
                                Building build = players[0].getBuilding(j);
                                switch (build.getbuilding(build)) {
                                    case 1:
                                        Lumberjack lumb = (Lumberjack) (build);
                                        lumb.woodchop(players[0], m);
                                        break;
                                    case 2:
                                        Fishinghouse fish = (Fishinghouse) (build);
                                        fish.fish(players[1]);
                                        System.out.println("essen: " +players[0].getFood());
                                        break;
                                    case 3:
                                        Wheatfield weed = (Wheatfield) (build);
                                        //harvest mehtode bidde neu
                                        weed.harvest(players[1]);
                                        break;
                                    case 4:
                                        Mine mine = (Mine) (build);
                                        mine.mine(players[0]);
                                        break;
                                }
                            }
                           foodAmount.setText(Integer.toString(players[i+1].getFood()));
                           woodAmount.setText(Integer.toString(players[i+1].getWood()));
                           motivationAmount.setText(Integer.toString(players[i+1].getWood()));
                           stoneAmount.setText(Integer.toString(players[i+1].getWood()));
                            
                        }
                        break;
                    }
                }
                for (int i = 0; i < players.length; i++) {
                    Player.checkElements(players[i]);
                }
                Tilemap.setSelectedFeld(null);
                Tilemap.setSelectedCharacter(null);
                Tilemap.setSelectedBuilding(null);
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
    public JLabel AtTurn() {
        JLabel AtTurn = new JLabel();
        AtTurn.setLocation(width / 2 - 100, 20);
        AtTurn.setSize(200, 40);
        AtTurn.setOpaque(true);
        AtTurn.setBackground(Color.cyan);
        AtTurn.setText("Am Zug: Spieler 1");
        //AtTurn.setHorizontalTextPosition(JLabel.CENTER);
        AtTurn.setVisible(true);
        return AtTurn;
    }

    public static int getPlacement() {
        return Placement;
    }

    public static void setPlacement(int i) {
        Placement = i;
    }
    
    public Image getIconImage(Integer name){
        
        if (!icons.containsKey(name)){
            System.out.println("Dieses Icon existiert nicht!");
            return icons.get(0).getImage();
        }
        
        return icons.get(name).getImage();
    }

    public JPanel minimap() {
        JPanel minimap = new JPanel();
        minimap.setLocation(width - 50, 0);
        minimap.setSize(50, 50);
        minimap.setBackground(Color.orange);
        minimap.setBorder(BorderFactory.createLineBorder(Color.black));
        minimap.setVisible(true);
        return minimap;
    }

}