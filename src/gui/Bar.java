package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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
import building.Fishinghouse;
import building.Lumberjack;
import building.Mine;
import building.Wheatfield;
import building.WinCondition;
import java.awt.Image;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bar extends JInternalFrame {

    private int bWidth;
    private int bHeight;
    int placement = 0;
    HashMap<Integer, ImageIcon> icons;
    JLabel foodAmount = new JLabel("0");
    JLabel woodAmount = new JLabel("0");
    JLabel motivationAmount = new JLabel("0");
    JLabel stoneAmount = new JLabel("0");

    private static final String RESOURCE_PATH = "src/gui/res";
    private static final String BUTTON_PRESSED = "warriorButton pressed";

    public Bar(int width, int height) {
        super();
        this.bWidth = width;
        this.bHeight = height;
        JTabbedPane tabs = new JTabbedPane(SwingConstants.TOP);

        // Icons laden
        icons = new HashMap<>();

        icons.put(2, new ImageIcon(RESOURCE_PATH + "/building/fishinghouse.png"));
        icons.put(4, new ImageIcon(RESOURCE_PATH + "/building/mine.png"));
        icons.put(7, new ImageIcon(RESOURCE_PATH + "/building/townhall.png"));
        icons.put(8, new ImageIcon(RESOURCE_PATH + "/building/windmill.png"));

        icons.put(9, new ImageIcon(RESOURCE_PATH + "/warrior1/idle/idle_1.png"));
        icons.put(10, new ImageIcon(RESOURCE_PATH + "/archer/idel/archer_idel1.png"));

        icons.put(0, new ImageIcon(RESOURCE_PATH + "/"));

        this.setSize(width, height / 3);
        this.setLocation(0, height - height / 3);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //ressourceBar
        icons.put(99, new ImageIcon(RESOURCE_PATH + "/resources/1resourceBar_left.png"));
        icons.put(98, new ImageIcon(RESOURCE_PATH + "/resources/2resourceBar_mid.png"));
        icons.put(97, new ImageIcon(RESOURCE_PATH + "/resources/3resourceBar_right.png"));
        icons.put(96, new ImageIcon(RESOURCE_PATH + "/resources/4food.png"));
        icons.put(95, new ImageIcon(RESOURCE_PATH + "/resources/5wood.png"));
        icons.put(94, new ImageIcon(RESOURCE_PATH + "/resources/6motivation.png"));
        icons.put(93, new ImageIcon(RESOURCE_PATH + "/resources/7stone.png"));

        jlabelMaker(foodAmount, 54, 27);
        jlabelMaker(woodAmount, 118, 27);
        jlabelMaker(stoneAmount, 266, 27);
        jlabelMaker(motivationAmount, 185, 27);
        
        // entfernt leiste bei tabbedpane
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        Color internalBorderColor = new Color(85, 53, 5);
        this.setBorder(BorderFactory.createLineBorder(internalBorderColor, 4));

        // widht -8 für "sauberen" Rahmen
        tabs.setPreferredSize(new Dimension(width - 8, height / 3));

        //Tab für Gebeude
        JPanel panelBuildings = new JPanel();
        panelBuildings.setLayout(null);

        jbuttonMaker(panelBuildings, icons.get(0), "Barracks", 0, 1); // buildable check
        jbuttonMaker(panelBuildings, icons.get(2), "Fish", 200, 2); // buildable check
        jbuttonMaker(panelBuildings, icons.get(0), "Lumberjack", 400, 3); // buildable check
        jbuttonMaker(panelBuildings, icons.get(4), "Mine", 600, 4); // buildable check
        jbuttonMaker(panelBuildings, null, "Theatre", 800, 5); // buildable check
        jbuttonMaker(panelBuildings, icons.get(0), "Tower", 1000, 6); // buildable check
        jbuttonMaker(panelBuildings, icons.get(6), "Townhall", 1200, 7); // NO buildable check
        jbuttonMaker(panelBuildings, icons.get(0), "Wheatfield", 1400, 8); // NO buildable check
        jbuttonMaker(panelBuildings, icons.get(8), "Windmill", 1600, 8); // NO buildable check, NO reassignment of placement therefor 8 (previous value)

        //Tab für die Auswahl von Truppen
        JPanel panelTroops = new JPanel();
        panelTroops.setLayout(null);

        jbuttonMaker(panelTroops, icons.get(9), "test", 0, 9);
        jbuttonMaker(panelTroops, icons.get(10), "archer", 200, 10);
        jbuttonMaker(panelTroops, icons.get(0), "catapult", 400, 11);
        jbuttonMaker(panelTroops, icons.get(0), "horse", 600, 12);

        // Tab für das Beenden eines Zuges
        JPanel panelTurn = new JPanel();
        JButton endTurn = new JButton("End Turn");
        endTurn.addActionListener( (ActionEvent e) -> {
            // Runden Button Turn End noch init
            // Das in ActionPerformed vom ButtonTurnEnd übernehmen
            WinCondition.einnehmenProzess();
            this.placement = 0;
            Player[] players = Start.getPlayers();
            for (int i = 0; i < players.length; i++) {
                if (!players[i].isAtTurn()) 
                    continue;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                for (int q = 0; q < players[i].getCharacterAmount(); q++) {
                    players[i].setCharacterMovementAllowed(q);
                }

                players[i].setAtTurn(false);
                fillUpResources(players, i);
            }

            for (int i = 0; i < players.length; i++) {
                Player.checkElements(players[i]);
            }

            Tilemap.setSelectedFeld(null);
            Tilemap.setSelectedCharacter(null);
            Tilemap.setSelectedBuilding(null);
        });
        panelTurn.add(endTurn);

        tabs.addTab("Gebäude", panelBuildings);
        tabs.addTab("Truppen", panelTroops);
        tabs.addTab("End Turn", panelTurn);
        tabs.setVisible(true);

        this.add(tabs);
        this.requestFocus();

        // immer am Ende
        this.setVisible(true);
    }

    // Achtung: Die Implementation hier ist wie eure (nur vereinfacht)
    // aber ich verstehe nicht wie ihr die Spieler auswählt
    // die Index sind sehr komisch und scheinen mir zumindest fast schon willkürlich
    // ich habe mich da natürlich nicht so doll mit beschäftigt wie ihr 
    // aber hier sollte meiner Meinung nach definitiv nochmal drüber geschaut werden
    // gerne auch in älteren Commits zB - d3f1f78b8866f5f88ce50d2fd780e4ff3f557220
    // in dieser Implementation habe ich die Indexe geändert sodass sie für mich Sinn ergeben haben
    // es ist natürlich sehr gut möglich das ich etwas an eurem Prozess/Algorithmus nicht verstanden habe
    // deswegen bitte diese Implementierung nochmals überprüfen
    public void fillUpResources(Player[] players, int i) {
        int index = i + 1 == players.length ? 0 : i + 1;
        int ind = i + 1 == players.length ? 0 : 1;

        if (i + 1 == players.length) {
            players[index].setAtTurn(true);
            MainWindow.atTurn.setText("Am Zug:" + players[index].getPlayername());
            MainWindow.atTurn.setBackground(players[index].getColour());
            //recourcen auffuellen
            for (int j = 0; j < players[ind].getBuildingAmount(); j++) {
                Building build = players[ind].getBuilding(j);
                switch (build.getbuilding(build)) {
                    case 1:
                        Lumberjack lumb = (Lumberjack) (build);
                        lumb.woodchop(players[ind]);
                        break;
                    case 2:
                        Fishinghouse fish = (Fishinghouse) (build);
                        fish.fish(players[ind]);
                        break;
                    case 3:
                        Wheatfield weed = (Wheatfield) (build);
                        //lel hier bidde noch ma neu machen
                        weed.harvest(players[ind]);
                        break;
                    case 4:
                        Mine mine = (Mine) (build);
                        mine.mine(players[ind]);
                        break;
                    default:
                        throw new UnsupportedOperationException("The implementation needs to be expanded for an additional building");
                }
            }

            if (index == 0) {    
                players[0].setStone(players[0].getStone() + 5);
                players[0].setWood(players[0].getWood() + 5);
            }

            foodAmount.setText(Integer.toString(players[index].getFood()));
            woodAmount.setText(Integer.toString(players[index].getWood()));
            motivationAmount.setText(Integer.toString(players[index].getWood()));
            stoneAmount.setText(Integer.toString(players[index].getWood()));
        }
    }

    public void jlabelMaker(JLabel label, int x, int y) {
        label.setLocation(x, y);
        label.setSize(100, 10);
        label.setForeground(new java.awt.Color(255,0,0));
        label.setBackground(Color.RED);
        label.setVisible(true);
    }

    public void jbuttonMaker(JPanel panel, ImageIcon icon, String text, int xCoord, int plc) {
        JButton button = new JButton();
        button.setIcon(icon);
        button.setText(text);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setSize(new Dimension(200, 200));
        button.setLocation(xCoord, 0);
        button.addActionListener( (ActionEvent e) -> {
            Logger.getLogger(Bar.class.getName()).log(Level.INFO, BUTTON_PRESSED);
            placement = plc;
        });
        panel.add(button);
    }

    public BasicArrowButton closeBarButton() {
        BasicArrowButton close = new BasicArrowButton(SwingConstants.SOUTH);
        close.setLocation(bWidth - 50, bHeight - bHeight / 3 - 20);
        close.setText("v");
        close.setSize(50, 20);
        close.setVisible(true);
        return close;
    }

    public BasicArrowButton openBarButton() {
        BasicArrowButton open = new BasicArrowButton(SwingConstants.NORTH);
        open.setLocation(bWidth - 50, bHeight - 20);
        open.setSize(50, 20);
        open.setVisible(true);
        return open;
    }

    // Anzeige wer am Zug ist, noch Text zentrieren
    public JLabel atTurn() {
        JLabel atTurn = new JLabel();
        atTurn.setLocation(bWidth / 2 - 100, 20);
        atTurn.setSize(200, 40);
        atTurn.setOpaque(true);
        atTurn.setBackground(Color.cyan);
        atTurn.setText("Am Zug: Spieler 1");
        atTurn.setVisible(true);
        return atTurn;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int i) {
        placement = i;
    }

    public Image getIconImage(Integer name) {

        if (!icons.containsKey(name)) {
            Logger.getLogger(Bar.class.getName()).log(Level.INFO,"Dieses Icon existiert nicht!");
            return icons.get(0).getImage();
        }

        return icons.get(name).getImage();
    }

    public JPanel minimap() {
        JPanel minimap = new JPanel();
        minimap.setLocation(bWidth - 50, 0);
        minimap.setSize(50, 50);
        minimap.setBackground(Color.orange);
        minimap.setBorder(BorderFactory.createLineBorder(Color.black));
        minimap.setVisible(true);
        return minimap;
    }

}
