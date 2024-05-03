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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import launcher.Start;
import map.Feld;
import map.Player;
import character.*;
import character.Character;
import building.Building;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

public class Tilemap extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private static int n = 64;

    private transient Player[] players;
    private transient Map m;
    private Bar bar;
    private Point startPoint;
    private int camX = 0;
    private int camY = 0;
    private int hoveredX = 0;
    private int hoveredY = 0;

    static Feld selectedFeld;
    static character.Character selectedCharacter;
    static Building selectedBuilding;

    /**
     * @param width
     * @param height
     * @param m
     * @param b
     */
    public Tilemap(int width, int height, Map m, Bar b, Player[] players) {
        super();
        this.m = m;
        this.bar = b;
        this.players = players;
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
            for (int j = 0; j < m.getHeight(); j++)
                g.drawImage(m.getTerrainPicture(i, j), n * i + camX, n * j + camY, n, n, null);
        }

        // Charaktere und Gebäude zeichnen
        for (int i = 0; i < players.length; i++) {
            // Charaktere zeichnen
            for (int j = 0; j < players[i].getCharacterAmount(); j++) {
                character.Character c = players[i].getCharacter(j);
                g.drawImage(players[i].getCharacterPicture(j), c.getXPosition() * n + camX + c.getDisplacementX(), c.getYPosition() * n + camY + c.getDisplacementY(), n, n, null);
                
                // Health Bar zeichnen
                g.setColor(new Color(30, 47, 32));
                g.fillRoundRect(c.getXPosition() * n + camX + c.getDisplacementX(), c.getYPosition() * n + camY + c.getDisplacementY(), n, n/6, 6, 6);
                
                g.setColor(new Color(60, 163, 46));
                g.fillRoundRect(c.getXPosition() * n + camX + c.getDisplacementX(), c.getYPosition() * n + camY + c.getDisplacementY(), (n*c.getHealthpoints())/c.getMaxHealth(), n/6, 6, 6);
            }
            // Gebäude zeichnen
            for (int j = 0; j < players[i].getBuildingAmount(); j++) {
                building.Building b = players[i].getBuilding(j);
                g.drawImage(players[i].getBuilding(j).getPicture(), b.getxPosition() * n + camX, camY + n * b.getyPosition(), n, n, null);
            }
        }
        
        g.drawImage(Tower.getPicture1(), 25 * n + camX, camY + n * 25, n, n, null);

        // zeichne markierungen für von maus berührte felder
        g.setColor(Color.DARK_GRAY);
        g.drawRect(n * hoveredX + camX, n * hoveredY + camY, n, n);

        if (selectedFeld != null) {
            g.setColor(Color.magenta);
            g.drawRect(n * selectedFeld.getXPosition() + camX, n * selectedFeld.getYPosition() + camY, n, n);

        }

        // zeichne icon von geplanter Truppe
        if (this.bar.getPlacement() != 0) {
            Image icon = bar.getIconImage(this.bar.getPlacement());

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
            if (players[i].isAtTurn() && selectedCharacter != null && players[i].getPlayername().equals(selectedCharacter.getPlayername()) && selectedCharacter.isCanmove()) {
                    
                // movement range bestimmen und betretbare felder markieren
                selectedCharacter.movementrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
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
            }
        }
        
        // territory range bestimmen und felder markieren
        for (int i = 0; i < players.length; i++) {
            if (players[i].isAtTurn() && selectedCharacter == null) {
                    for (int j = 0; j < players[i].getTerritory().size(); j++) {
                        g.setColor(players[i].getSecondcolour());

                        g.drawRect(n * players[i].getTerritory().get(j)[0] + camX, n * players[i].getTerritory().get(j)[1] + camY, n, n);
                    }
            }
        }

        // resourceBar
        g.drawImage(bar.getIconImage(99), 0, 0, null); //ressourceBarleft
        g.drawImage(bar.getIconImage(98), 64, 0, null); //ressourceBarmid
        g.drawImage(bar.getIconImage(98), 128, 0, null); //ressourceBarmid
        g.drawImage(bar.getIconImage(98), 192, 0, null); //ressourceBarmid
        g.drawImage(bar.getIconImage(97), 256, 0, null); //ressourceBarright
        g.drawImage(bar.getIconImage(96), 10, 4, null); //food 
        g.drawImage(bar.getIconImage(95), 64, 0, null); //wood

        g.drawImage(bar.getIconImage(94), 128, 0, null); //motivation
        g.drawImage(bar.getIconImage(93), 200, 7, null); //stone
  

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
                        g.setColor(Color.cyan);
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
                for (int k = 0; k < players.length; k++) {
                    if (players[k].getBuilding(i, j) != null) {
                        g.setColor(players[k].getColour());
                        this.bar.setPlacement(0);

                        Player.getAtTurn().setWood(Player.getAtTurn().getWood()-1);
                        Player.getAtTurn().setStone(Player.getAtTurn().getStone()-1);
                        players[i].updateterritory(m);

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

    private int booleanToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    private void setSelectedCharacter() {
        Player[] p = Start.getPlayers();
        selectedCharacter = null;

        for (int i = 0; i < p.length; i++) {
            Player player = p[i];

            if (player.getCharacter(hoveredX, hoveredY) != null) {
                selectedCharacter = player.getCharacter(hoveredX, hoveredY);
            }
        }

        Logger.getLogger(Tilemap.class.getName()).log(Level.INFO, () -> "Selected Character: " + selectedCharacter);

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

        Logger.getLogger(Tilemap.class.getName()).log(Level.INFO, () -> "Selected Building: " + selectedBuilding);

    }

    private void movetoPositon() {
        for (int i = 0; i < players.length; i++) {
            if (!players[i].isAtTurn() || selectedCharacter == null || !selectedCharacter.isCanmove() || !players[i].getPlayername().equals(selectedCharacter.getPlayername()))
                continue;

            selectedCharacter.movementrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
            for (int j = 0; j < selectedCharacter.getMovementrange().size(); j++) {
                if (selectedCharacter.getMovementrange().get(j)[0] == hoveredX && selectedCharacter.getMovementrange().get(j)[1] == hoveredY) {
                    selectedCharacter.playMoveAnimation(hoveredX, hoveredY);

                    Map.getFeld(selectedCharacter.getXPosition(), selectedCharacter.getYPosition()).setOccupied(false);
                    Map.getFeld(selectedCharacter.getXPosition(), selectedCharacter.getYPosition()).setOccupiedby(null);

                    selectedCharacter.setxPosition(hoveredX);
                    selectedCharacter.setyPosition(hoveredY);

                    Map.getFeld(selectedCharacter.getXPosition(), selectedCharacter.getYPosition()).setOccupied(true);
                    Map.getFeld(selectedCharacter.getXPosition(), selectedCharacter.getYPosition()).setOccupiedby(players[i].getPlayername());

                    selectedCharacter.setCanmove(false);
                    return;
                }
            }

            selectedCharacter.attackrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
            for (int j = 0; j < selectedCharacter.getAttackrange().size(); j++) {
                if (selectedCharacter.getAttackrange().get(j)[0] != hoveredX || selectedCharacter.getAttackrange().get(j)[1] != hoveredY)
                    continue;

                String playername = Map.getFeld(hoveredX, hoveredY).getOccupiedby();
                for (int k = 0; k < players.length; k++) {
                    if (!players[k].getPlayername().equals(playername))
                        continue;

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

    public boolean characterPlaceable() {
        return selectedBuilding == null && selectedCharacter == null && this.bar.getPlacement() != 0;
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
        if (selectedBuilding != null || selectedCharacter != null || this.bar.getPlacement() == 0)
            return;

        for (Player player : players) {
            if (!player.isAtTurn())
                continue;

            switch (this.bar.getPlacement()) {
                case 1:
                    placeBuilding(player, new Barracks(hoveredX, hoveredY));
                    break;
                case 2:
                    placeBuilding(player, new Fishinghouse(hoveredX, hoveredY));
                    break;
                case 3:
                    placeBuilding(player, new Lumberjack(hoveredX, hoveredY));
                    break;
                case 4:
                    placeBuilding(player, new Mine(hoveredX, hoveredY));
                    break;
                case 5:
                    placeBuilding(player, new Theatre(hoveredX, hoveredY));
                    break;
                case 6:
                    placeBuilding(player, new Tower(hoveredX, hoveredY));
                    break;
                case 7:
                    placeBuilding(player, new Townhall(hoveredX, hoveredY));
                    break;
                case 8:
                    placeBuilding(player, new Wheatfield(hoveredX, hoveredY));
                    break;
                case 9:
                    placeCharacter(player, new Warrior(player.getPlayername(), hoveredX, hoveredY));
                    break;
                case 10:
                    placeCharacter(player, new Archer(player.getPlayername(), hoveredX, hoveredY));
                    break;
                case 11:
                    placeCharacter(player, new Catapult(player.getPlayername(), hoveredX, hoveredY));
                    break;
                case 12:
                    placeCharacter(player, new Horsemen(player.getPlayername(), hoveredX, hoveredY));
                    break;
                default:
            }
        }
    }

    public void placeCharacter(Player player, Character character) {
        player.setCharacter(character);
        this.bar.setPlacement(0);
        selectedFeld.setOccupied(true);
        selectedFeld.setOccupiedby(player.getPlayername());
    }

    public void placeBuilding(Player player, Building building) {
        if (Map.getFeld(hoveredX, hoveredY).getTerritoryplayer() != null &&  (Map.getFeld(hoveredX, hoveredY).getTerritoryplayer().equals(player.getPlayername()))){
            player.setBuilding(building);
            
            //Entfernen des Gebäudes wenn es dort nicht gebaut werden konnte
            if (player.getBuilding(player.getBuildingAmount() - 1).getBuildableterrains().contains(Map.getFeld(hoveredX, hoveredY).getTerrainName())){
                player.deleteBuilding(player.getBuildingAmount() - 1);
            } else {
                //Bauen des Gebäudes
                this.bar.setPlacement(0);
            
                //neues Festlegen des Territoriums des Spielers
                player.updateterritory(m);
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
        // no implementation
        // no throw so no crash can happen on Mouse Interaction
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // no implementation
        // no throw so no crash can happen on Mouse Interaction
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
        camY = clamp(camY, minY - (bar.getHeight() * booleanToInt(bar.isVisible())), 0);
    }

    public void barOpened() {
        int minY = (m.getHeight() * n - Toolkit.getDefaultToolkit().getScreenSize().height) * -1;

        if (camY < minY + bar.getHeight()) {
            camY -= bar.getHeight();
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
        if ((getN() < 128 && rotation > 0) || (getN() > 39 && rotation < 0)) {
            Tilemap.setN(getN() + rotation);
            this.limitCamera();
        }
    }

    public static int getN() {
        return Tilemap.n;
    }

    public static void setN(int n) {
        Tilemap.n = n;
    }
}
