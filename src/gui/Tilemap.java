package gui;

import building.Building;
import java.awt.Color;
import map.Map;
import gui.MainWindow;

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
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import launcher.Start;
import static launcher.Start.players;
import map.Feld;
import map.Player;
import tools.MiscUtils;
import character.*;

public class Tilemap extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{
    public static int n = 64;
    Map m;
    Bar b;
    Point startPoint;
    int camX = 0;
    int camY = 0;
    int hoveredX = 0;
    int hoveredY = 0;

    
    
    Feld selectedFeld;
    character.Character selectedCharacter;
    Building selectedBuilding;
    
    
    /**
     * @param m
     */
    public Tilemap(int width, int height, Map m, Bar b) {
        super();
        this.m = m;
        this.b = b;
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Map zeichnen
        for (int i = 0; i < m.getWidth(); i++) {
            for (int j = 0; j < m.getHeight(); j++) {
                g.drawImage(m.getTerrainPicture(i, j), n * i + camX, n * j + camY, null);
                
            }
        }
        
        // Charaktere zeichnen
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].getCharacterAmount(); j++) {
                g.drawImage(players[i].getCharacterPicture(j), players[i].getCharacter(j).getXPosition() * n + camX, players[i].getCharacter(j).getYPosition() * n + camY, null);
            }
            
        }
        
        // zeichne markierungen für von maus berührte felder
        g.setColor(Color.DARK_GRAY);
        g.drawRect(n * hoveredX + camX, n * hoveredY + camY, n, n);
        
        if (selectedFeld != null){
            g.setColor(Color.magenta);
            g.drawRect(n * selectedFeld.getXPosition() + camX, n * selectedFeld.getYPosition() + camY, n, n);
        }
        for (int i = 0; i < players.length; i++) {
            if(players[i].isAtTurn() && selectedCharacter != null){
                if(players[i].getPlayername().equals(selectedCharacter.getPlayername()) && selectedCharacter.isCanmove()){
                    selectedCharacter.movementrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
 //                   selectedCharacter.attackrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
                    for (int j = 0; j < selectedCharacter.getMovementrange().size(); j++) {
                        g.setColor(players[i].getColour());
                        g.drawRect(n * selectedCharacter.getMovementrange().get(j)[0] + camX, n * selectedCharacter.getMovementrange().get(j)[1] + camY, n, n);
                        
                    }
//                    for (int j = 0; j < selectedCharacter.getAttackrange().size(); j++) {
//                        g.setColor(Color.red);
//                        g.drawRect(64 * selectedCharacter.getAttackrange().get(j)[0] + camX, 64 * selectedCharacter.getAttackrange().get(j)[1] + camY, 64, 64);
//                    }
                }
            }
        }
        
        //g.drawImage(Toolkit.getDefaultToolkit().getImage("src/GUI/res/ResourceBar.png"), 0, 0, null);
        
    }
    
    
    
    //Grenze für scroll
    private int clamp(int a, int min, int max){
        if (a > max) {
            return max;
        }
        
        if (a < min){
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
    
    private void setSelectedCharacter(){
        Player[] p = Start.getPlayers();
        selectedCharacter = null;
        
        for (int i = 0; i < p.length; i++) {
            Player player = p[i];
            
            if (player.getCharacter(hoveredX, hoveredY) != null){
                selectedCharacter = player.getCharacter(hoveredX, hoveredY);
            }
        }
        
        System.out.println("Selected Character: " + selectedCharacter);
        
        
    }
    
    private void setSelectedBuilding(){
        Player[] p = Start.getPlayers();
        selectedBuilding = null;
        
        for (int i = 0; i < p.length; i++) {
            Player player = p[i];
            
            if (player.getBuilding(hoveredX, hoveredY) != null){
                selectedBuilding = player.getBuilding(hoveredX, hoveredY);
            }
        }
        
        System.out.println("Selected Building: " + selectedBuilding);
        
        
    }
    
    private void movetoPositon() {
        for (int i = 0; i < players.length; i++) {
            if(players[i].isAtTurn() && selectedCharacter != null){
                if(players[i].getPlayername().equals(selectedCharacter.getPlayername()) && selectedCharacter.isCanmove()){
                    selectedCharacter.movementrange(selectedCharacter.getXPosition(), selectedCharacter.getYPosition(), m);
                    for (int j = 0; j < selectedCharacter.getMovementrange().size(); j++) {
                        if(selectedCharacter.getMovementrange().get(j)[0] == hoveredX && selectedCharacter.getMovementrange().get(j)[1] == hoveredY){
                            selectedCharacter.setxPosition(hoveredX);
                            selectedCharacter.setyPosition(hoveredY);
                            selectedCharacter.setCanmove(false);
                            return;
                        }
                        
                        
                    }
                }
            }
        }
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        // angeklicktes Feld auswählen
        selectedFeld = m.getFeld(hoveredX, hoveredY);
        
        // Bewegung zur Position
        movetoPositon();
        
        // angeklickte Character finden
        setSelectedCharacter();
        
        // angeklickte Gebäude finden
        setSelectedBuilding();
        
        if(selectedBuilding == null && selectedCharacter == null && Bar.getPlacement() != 0){
            for (int i = 0; i < players.length; i++) {
                    if(players[i].isAtTurn()){
                        switch(Bar.getPlacement()){
                            case 1: players[i].setCharacter(new Warrior(players[i].getPlayername(), hoveredX, hoveredY));
                                    Bar.setPlacement(0);
            }
        }
            }
        }
        
        
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
        if (startPoint == null) return;
        
        Point location = SwingUtilities.convertPoint(this, e.getPoint(), this.getParent());
        
        if (this.getRootPane().getBounds().contains(location)){
            Point newLocation = this.getLocation();
            newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);

            camX += newLocation.x;
            camY += newLocation.y;
            
            // Grenze von unten rechts und oben links festgelegt
            limitCamera();
           
            
            startPoint = location;
        } else startPoint = null;
    }
    
    public void limitCamera(){
         int minX = (m.getWidth()*n - Toolkit.getDefaultToolkit().getScreenSize().width)*-1;
            camX = clamp(camX, minX, 0);
            
            int minY = (m.getHeight()*n - Toolkit.getDefaultToolkit().getScreenSize().height)*-1;
            camY = clamp(camY,minY - (b.getHeight() * booleanToInt(b.isVisible())), 0);
    }
    
    public void barOpened(){
        int minY = (m.getHeight()*n - Toolkit.getDefaultToolkit().getScreenSize().height)*-1;
        
        if (camY < minY + b.getHeight()){
            camY -= b.getHeight();
        }
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point clicked = SwingUtilities.convertPoint(this.getParent(), e.getPoint(), this.getParent());
        hoveredX = (clicked.x - camX) / n;
        hoveredY = (clicked.y - camY ) / n;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = -e.getWheelRotation();
        if(getN()<64 && rotation>0){
            this.setN(getN()+rotation);
            this.limitCamera();
        } else if(getN()>39 && rotation<0){
            this.setN(getN()+rotation);
            this.limitCamera();
        }
    }

    public int getN(){
        return this.n;
    }
    public void setN(int n){
        this.n = n;
    }
}