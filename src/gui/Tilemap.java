package gui;

import building.*;
import java.awt.Color;
import map.Map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import launcher.Start;
import static launcher.Start.players;
import map.Feld;
import map.Player;
import character.*;
import building.Building;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

public class Tilemap extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    public static int n = 64;

    Map m;
    Bar b;
    ResourceBar rb;
    Point startPoint;
    int camX = 0;
    int camY = 0;
    int hoveredX = 0;
    int hoveredY = 0;

    static Feld selectedFeld;
    static character.Character selectedCharacter;
    static Building selectedBuilding;

    /**
     * @param width
     * @param height
     * @param m
     * @param b
     */
    public Tilemap(int width, int height, Map m, Bar b) {
        super();
        this.m = m;
        this.b = b;
        this.rb = rb;
        this.setSize(new Dimension(width, height));
        this.setLocation(0, 0);
        this.setVisible(true);

        camX = -(m.getWidth() * n) / 4;
        camY = -(m.getHeight() * n) / 4;
        //dreckiger workaround -> Event triggert nicht ohne, Ursache unklar
        this.addMouseWheelListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    //painComponent; erst schwarzer Hintergrund
    @Override
    public void paintComponent(Graphics g2) {
        super.paintComponent(g2);

        Graphics2D g = (Graphics2D) g2;

        // Map zeichnen
        for (int i = 0; i < m.getWidth(); i++) {
            for (int j = 0; j < m.getHeight(); j++) {

                g.drawImage(m.getTerrainPicture(i, j), n * i + camX, n * j + camY, n, n, null);
            }
        }

        // Charaktere und Gebäude zeichnen
        for (int i = 0; i < players.length; i++) {
            // Charaktere zeichnen
            for (int j = 0; j < players[i].getCharacterAmount(); j++) {
                character.Character c = players[i].getCharacter(j);
                g.drawImage(players[i].getCharacterPicture(j), c.getXPosition() * n + camX + c.getDisplacementX(), c.getYPosition() * n + camY + c.getDisplacementY(), n, n, null);
                
                // Health Bar zeichnen
                g.setColor(new Color(30, 47, 32));
                g.fillRoundRect(c.getXPosition() * n + camX + c.getDisplacementX(), 
                        c.getYPosition() * n + camY + c.getDisplacementY(), n, n/6, 6, 6);
                
                g.setColor(new Color(60, 163, 46));
                g.fillRoundRect(c.getXPosition() * n + camX + c.getDisplacementX(), 
                        c.getYPosition() * n + camY + c.getDisplacementY(), (n*c.getHealthpoints())/c.getMaxHealth(), n/6, 6, 6);
            }
            // Gebäude zeichnen
            for (int j = 0; j < players[i].getBuildingAmount(); j++) {
                building.Building b = players[i].getBuilding(j);
                g.drawImage(players[i].getBuilding(j).getPicture(), b.getxPosition() * n + camX, camY + n * b.getyPosition(), n, n, null);
            }
        }

        // zeichne markierungen für von maus berührte felder
        g.setColor(Color.DARK_GRAY);
        g.drawRect(n * hoveredX + camX, n * hoveredY + camY, n, n);

        if (selectedFeld != null) {
            g.setColor(Color.magenta);
            g.drawRect(n * selectedFeld.getXPosition() + camX, n * selectedFeld.getYPosition() + camY, n, n);

        }

        // zeichne icon von geplanter Truppe
        if (Bar.getPlacement() != 0) {
            Image icon = b.getIconImage(Bar.getPlacement());

            // Icon zeichen (Transparenz setzen)
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));

            g.drawImage(icon, hoveredX * n + camX, hoveredY * n + camY, null);

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

            // Rahmen zeichen, zeigt ob platzierbar oder nicht
            if (characterPlaceable()) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.red);
            }

            g.drawRect(n * hoveredX + camX, n * hoveredY + camY, n, n);
        }

        // zeichne felder, die betreten werden können
        for (int i = 0; i < players.length; i++) {
            if (players[i].isAtTurn() && selectedCharacter != null) {
                if (players[i].getPlayername().equals(selectedCharacter.getPlayername()) && selectedCharacter.isCanmove()) {

                    // movement range bestimmen und betretbare felder markieren
                    selectedCharacter.movementrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
                    //                   selectedCharacter.attackrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
                    for (int j = 0; j < selectedCharacter.getMovementrange().size(); j++) {
                        g.setColor(players[i].getColour());

                        g.drawRect(n * selectedCharacter.getMovementrange().get(j)[0] + camX, n * selectedCharacter.getMovementrange().get(j)[1] + camY, n, n);

                    }

                    // attack range bestimmen und betretbare felder markieren
                    selectedCharacter.attackrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
                    for (int j = 0; j < selectedCharacter.getAttackrange().size(); j++) {
                        g.setColor(Color.WHITE);
                        g.drawRect(64 * selectedCharacter.getAttackrange().get(j)[0] + camX, 64 * selectedCharacter.getAttackrange().get(j)[1] + camY, 64, 64);

                    }
//                    for (int j = 0; j < selectedCharacter.getAttackrange().size(); j++) {
//                        g.setColor(Color.red);
//                        g.drawRect(64 * selectedCharacter.getAttackrange().get(j)[0] + camX, 64 * selectedCharacter.getAttackrange().get(j)[1] + camY, 64, 64);
//                    }
                }
            }
        }

        // resourceBar
        g.drawImage(b.getIconImage(99), 0, 0, null); //ressourceBarleft
        g.drawImage(b.getIconImage(98), 64, 0, null); //ressourceBarmid
        g.drawImage(b.getIconImage(98), 128, 0, null); //ressourceBarmid
        g.drawImage(b.getIconImage(97), 192, 0, null); //ressourceBarright
        g.drawImage(b.getIconImage(96), 10, 4, null); //food 
        g.drawImage(b.getIconImage(95), 64, 0, null); //wood

        //g.drawImage(Toolkit.getDefaultToolkit().getImage("src/GUI/res/ResourceBar.png"), 0, 0, null);        
        //minimap
        for (int i = 0; i < m.getHeight(); i++) {
            for (int j = 0; j < m.getWidth(); j++) {
                switch (m.getTerrainName(j, i)) {
                    case "desert":
                        g.setColor(Color.yellow);
                        break;

                    case "forest":

                        g.setColor(new Color(10, 140, 40));
                        break;

                    case "grass":
                        g.setColor(Color.green);
                        break;

                    case "water":
                        g.setColor(Color.blue);
                        break;

                    case "light_mountain":

                        g.setColor(Color.gray);
                        break;

                    case "dark_mountain":

                        g.setColor(Color.gray);
                        break;

                    default:
                }
                int minimapscale = 5;

                g.fillRect(Toolkit.getDefaultToolkit().getScreenSize().width - m.getWidth() * minimapscale + j * minimapscale, i * minimapscale, minimapscale, minimapscale);

                //Gebeude weden mit Player-Farben dargestellt
                for (int k = 0; k < Start.players.length; k++) {
                    if (Start.players[k].getBuilding(i, j) != null) {
                        g.setColor(Start.players[k].getColour());
                        g.fillRect(Toolkit.getDefaultToolkit().getScreenSize().width - m.getWidth() * minimapscale + i * minimapscale, j * minimapscale, minimapscale, minimapscale);
                    }

                }
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    //Grenze für scroll
    private int clamp(int a, int min, int max) {
        if (a > max) {
            return max;
        }

        if (a < min) {
            return min;
        }

        return a;

    }

    private int booleanToInt(boolean foo) {
        int bar = 0;
        if (foo) {
            bar = 1;
        }
        return bar;
    }

    private void setSelectedCharacter() {
        Player[] p = Start.getPlayers();
        selectedCharacter = null;

        for (int i = 0; i < p.length; i++) {
            Player player = p[i];

            if (player.getCharacter(hoveredX, hoveredY) != null) {
                selectedCharacter = player.getCharacter(hoveredX, hoveredY);
//                if(!selectedCharacter.getalive()){
//                    selectedCharacter = null;
//                }
            }
        }

        System.out.println("Selected Character: " + selectedCharacter);

    }

    private void setSelectedBuilding() {
        Player[] p = Start.getPlayers();
        selectedBuilding = null;

        for (int i = 0; i < p.length; i++) {
            Player player = p[i];

            if (player.getBuilding(hoveredX, hoveredY) != null) {
                selectedBuilding = player.getBuilding(hoveredX, hoveredY);
            }
        }

        System.out.println("Selected Building: " + selectedBuilding);

    }

    private void movetoPositon() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].isAtTurn() && selectedCharacter != null) {
                if (players[i].getPlayername().equals(selectedCharacter.getPlayername()) && selectedCharacter.isCanmove()) {
                    selectedCharacter.movementrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
                    for (int j = 0; j < selectedCharacter.getMovementrange().size(); j++) {
                        if (selectedCharacter.getMovementrange().get(j)[0] == hoveredX && selectedCharacter.getMovementrange().get(j)[1] == hoveredY) {
                            selectedCharacter.playMoveAnimation(hoveredX, hoveredY);

                            m.getFeld(selectedCharacter.getXPosition(), selectedCharacter.getYPosition()).setOccupied(false);
                            m.getFeld(selectedCharacter.getXPosition(), selectedCharacter.getYPosition()).setOccupiedby(null);

                            selectedCharacter.setxPosition(hoveredX);
                            selectedCharacter.setyPosition(hoveredY);

                            m.getFeld(selectedCharacter.getXPosition(), selectedCharacter.getYPosition()).setOccupied(true);
                            m.getFeld(selectedCharacter.getXPosition(), selectedCharacter.getYPosition()).setOccupiedby(players[i].getPlayername());

                            selectedCharacter.setCanmove(false);
                            return;
                        }
                    }
                    selectedCharacter.attackrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
                    for (int j = 0; j < selectedCharacter.getAttackrange().size(); j++) {
                        if (selectedCharacter.getAttackrange().get(j)[0] == hoveredX && selectedCharacter.getAttackrange().get(j)[1] == hoveredY) {
                            String playername = m.getFeld(hoveredX, hoveredY).getOccupiedby();
                            for (int k = 0; k < players.length; k++) {
                                if (players[k].getPlayername().equals(playername)) {
                                    for (int l = 0; l < players[k].getCharacterAmount(); l++) {
                                        if (players[k].getCharacter(l).getXPosition() == hoveredX && players[k].getCharacter(l).getYPosition() == hoveredY) {
                                            selectedCharacter.fight(selectedCharacter, players[k].getCharacter(l));
                                            selectedCharacter.setCanmove(false);
                                            return;
                                        }
                                    }
                                }
                            }

                        }
                    }

                }
            }
        }
    }

    public boolean characterPlaceable() {
        if (selectedBuilding == null && selectedCharacter == null && Bar.getPlacement() != 0) {
            return true;
        }

        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        // angeklicktes Feld auswählen
        selectedFeld = Map.getFeld(hoveredX, hoveredY);

        // Bewegung zur Position
        movetoPositon();

        // angeklickte Character finden
        setSelectedCharacter();

        // angeklickte Gebäude finden
        setSelectedBuilding();

        // etwas platzieren
        placeSelected();
        

    }

    public void placeSelected() {
        if (selectedBuilding == null && selectedCharacter == null && Bar.getPlacement() != 0) {
            for (int i = 0; i < players.length; i++) {
                if (players[i].isAtTurn()) {
                    switch (Bar.getPlacement()) {
                        case 1:
                            //neues Gebaeude kann nur in Territorium des Spielers gebaut werden, eigene Bedingung falls Name null
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer()!=null){
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(players[i].getPlayername())){
                                players[i].setBuilding(new Barracks(players[i].getPlayername(), hoveredX, hoveredY));
                                Bar.setPlacement(0);
                                
                                //neues Festlegen des Territoriums des Spielers
                                players[i].updateterritory(m);
                            }
                            }
                            break;
                        case 2:
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer()!=null){
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(players[i].getPlayername())){
                                players[i].setBuilding(new Fishinghouse(players[i].getPlayername(), hoveredX, hoveredY));
                                Bar.setPlacement(0);
                                players[i].updateterritory(m);
                            }
                            }
                            break;
                        case 3:
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer()!=null){
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(players[i].getPlayername())){
                                players[i].setBuilding(new Lumberjack(players[i].getPlayername(), hoveredX, hoveredY));
                                Bar.setPlacement(0);
                                players[i].updateterritory(m);
                            }
                            }
                            break;
                        case 4:
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer()!=null){
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(players[i].getPlayername())){
                                players[i].setBuilding(new Mine(players[i].getPlayername(), hoveredX, hoveredY, m));
                                Bar.setPlacement(0);
                                players[i].updateterritory(m);
                            }
                            }
                            break;
                        case 5:
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer()!=null){
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(players[i].getPlayername())){
                                players[i].setBuilding(new Theatre(players[i].getPlayername(), hoveredX, hoveredY));
                                Bar.setPlacement(0);
                                players[i].updateterritory(m);
                            }
                            }
                            break;
                        case 6:
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer()!=null){
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(players[i].getPlayername())){
                                players[i].setBuilding(new Tower(players[i].getPlayername(), hoveredX, hoveredY));
                                Bar.setPlacement(0);
                                players[i].updateterritory(m);
                            }
                            }
                            break;
                        case 7:
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer()!=null){
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(players[i].getPlayername())){
                                players[i].setBuilding(new Townhall(players[i].getPlayername(), hoveredX, hoveredY));
                                Bar.setPlacement(0);
                                players[i].updateterritory(m);
                            }
                            }
                            break;
                        case 8:
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer()!=null){
                            if(m.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(players[i].getPlayername())){
                                players[i].setBuilding(new Wheatfield(players[i].getPlayername(), hoveredX, hoveredY));
                                Bar.setPlacement(0);
                                players[i].updateterritory(m);
                            }
                            }
                            break;
                        case 9:
                            players[i].setCharacter(new Warrior(players[i].getPlayername(), hoveredX, hoveredY));
                            Bar.setPlacement(0);
                            selectedFeld.setOccupied(true);
                            selectedFeld.setOccupiedby(players[i].getPlayername());
                            break;
                        case 10:
                            players[i].setCharacter(new Archer(players[i].getPlayername(), hoveredX, hoveredY));
                            Bar.setPlacement(0);
                            selectedFeld.setOccupied(true);
                            selectedFeld.setOccupiedby(players[i].getPlayername());
                            break;
                        case 11:
                            players[i].setCharacter(new Catapult(players[i].getPlayername(), hoveredX, hoveredY));
                            Bar.setPlacement(0);
                            selectedFeld.setOccupied(true);
                            selectedFeld.setOccupiedby(players[i].getPlayername());
                            break;
                        case 12:
                            players[i].setCharacter(new Horsemen(players[i].getPlayername(), hoveredX, hoveredY));
                            Bar.setPlacement(0);
                            selectedFeld.setOccupied(true);
                            selectedFeld.setOccupiedby(players[i].getPlayername());
                            break;
                        default:
                    }
                }
            }
        }
    }

    public Feld getSelectedFeld() {
        return selectedFeld;
    }

    public static void setSelectedFeld(Feld selectedFeld1) {
        selectedFeld = selectedFeld1;
    }

    public character.Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public static void setSelectedCharacter(character.Character selectedCharacter1) {
        selectedCharacter = selectedCharacter1;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public static void setSelectedBuilding(Building selectedBuilding1) {
        selectedBuilding = selectedBuilding1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = SwingUtilities.convertPoint(this.getParent(), e.getPoint(), this.getParent());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        startPoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (startPoint == null) {
            return;
        }

        Point location = SwingUtilities.convertPoint(this, e.getPoint(), this.getParent());

        if (this.getRootPane().getBounds().contains(location)) {
            Point newLocation = this.getLocation();
            newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);

            camX += newLocation.x;
            camY += newLocation.y;

            // Grenze von unten rechts und oben links festgelegt
            limitCamera();

            startPoint = location;
        } else {
            startPoint = null;
        }
    }

    public void limitCamera() {
        int minX = (m.getWidth() * n - Toolkit.getDefaultToolkit().getScreenSize().width) * -1;
        camX = clamp(camX, minX, 0);

        int minY = (m.getHeight() * n - Toolkit.getDefaultToolkit().getScreenSize().height) * -1;
        camY = clamp(camY, minY - (b.getHeight() * booleanToInt(b.isVisible())), 0);
    }

    public void barOpened() {
        int minY = (m.getHeight() * n - Toolkit.getDefaultToolkit().getScreenSize().height) * -1;

        if (camY < minY + b.getHeight()) {
            camY -= b.getHeight();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point clicked = SwingUtilities.convertPoint(this.getParent(), e.getPoint(), this.getParent());

        hoveredX = (clicked.x - camX) / n;
        hoveredY = (clicked.y - camY) / n;

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = -e.getWheelRotation();
        if (getN() < 128 && rotation > 0) {
            this.setN(getN() + rotation);
            this.limitCamera();
        } else if (getN() > 39 && rotation < 0) {
            this.setN(getN() + rotation);
            this.limitCamera();
        }
    }

    public int getN() {
        return this.n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
