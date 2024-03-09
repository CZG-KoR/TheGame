package character;

import gui.Animation;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import map.Map;
import tools.MiscUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import static launcher.Start.players;

public abstract class Character implements Killable {

    // Anzahl an Leben für ein Character bis dieser "entfernt" wird
    protected int healthpoints;
    // Bewegungsreichweite (niemals hoeher als 28)
    protected int movement;

    // Angriffsreichweite
    protected int attackrange;

    // Wurde die Figur schon bewegt?
    protected boolean canmove = true;
    // Motivation
    protected int motivation;
    // Abstand den man laufen kann
    protected int moverange;
    // Position
    protected int xPosition;
    protected int yPosition;

    // Animation
    protected Animation curAnimation;
    protected HashMap<String, Animation> animationen;
    protected Image picture;

    protected int displacementX = 0;
    protected int displacementY = 0;

    protected boolean flipped = false;

    protected ArrayList<String> blockedterrains = new ArrayList();
    protected ArrayList<String> blockedterrainsattack = new ArrayList();

    //Movementrange, die bei jedem Zug neu berechnet werden muss
    protected ArrayList<int[]> movementrange = new ArrayList();
    protected ArrayList<int[]> attackrangel = new ArrayList();

    // Spieler zu dem Character gehoert
    private String playername;

    protected boolean alive = true;

    protected Character(String playername) {
        this.playername = playername;
        blockedterrains();
        blockedterrainsattack();

        animationen = new HashMap<>();
    }

    public void playAnimation(String name) {

        if (animationen.containsKey(name)) {
            curAnimation.stop();
            curAnimation = animationen.get(name);
            curAnimation.start();
        }

    }

    public void playMoveAnimation(int xGoal, int yGoal) {

        int deltaX = (xGoal - xPosition) * 64;
        int deltaY = (yGoal - yPosition) * 64;

        displacementX -= deltaX;
        displacementY -= deltaY;

        if (deltaX < 0) {
            flipped = true;
        }

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {

                int n = (int) (Math.round(Math.hypot(deltaX, deltaY) / 5));
                playAnimation("walk");

                for (int i = 0; i < n; i++) {

                    displacementX += deltaX / n;
                    displacementY += deltaY / n;

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                displacementX = 0;
                displacementY = 0;
                playAnimation("idle");
                flipped = false;
            }
        };
        Timer t = new Timer();
        t.schedule(tt, 0);

    }

    public boolean isCanmove() {
        return canmove;
    }

    public void setCanmove(boolean canmove) {
        this.canmove = canmove;
    }

    //Festlegen der geblockten Terrains für Charaktere
    public abstract void blockedterrains();

    public abstract void blockedterrainsattack();

    // Kampf
    public void fight(Character char1, Character char2) {
        if (char1.playername.equals(char2.playername)) {
            return; // Fehlercode ausgeben
        }

        if (char1 instanceof Fighter && char2 instanceof Fighter) {
            Fighter figh1 = (Fighter) (char1);
            Fighter figh2 = (Fighter) (char2);

            if (figh1.canattack && figh1.attackrange <= (int) MiscUtils.distance(figh1, figh2)) {
                figh2.healthpoints = figh2.healthpoints - figh1.attackrating * figh1.motivation;
            }

            if (figh2.healthpoints <= 0) {
                figh2.alive = false;
            }

            if (figh2.canattack && figh2.attackrange <= (int) MiscUtils.distance(figh1, figh2)) {
                figh1.healthpoints = figh1.healthpoints - figh2.attackrating * figh2.motivation;
            }

            if (figh1.healthpoints <= 0) {
                figh1.alive = false;
            }
        }

        if (char1 instanceof Fighter && char2 instanceof Builder) {
            Fighter figh1 = (Fighter) (char1);
            if (figh1.canattack && figh1.attackrange <= (int) MiscUtils.distance(figh1, char2)) {
                char2.healthpoints = char2.healthpoints - figh1.attackrating * figh1.motivation;
            }

            if (char2.healthpoints <= 0) {
                char2.alive = false;
            }
        }

        if (char1 instanceof Builder && char2 instanceof Fighter) {
            Fighter figh2 = (Fighter) (char2);
            if (figh2.canattack && figh2.attackrange <= (int) MiscUtils.distance(char1, figh2)) {
                char1.healthpoints = char1.healthpoints - figh2.attackrating * figh2.motivation;
            }

            if (char1.healthpoints <= 0) {
                char1.alive = false;
            }
        }

    }

    public String getPlayername() {
        return playername;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    // public void killed() {
    //
    // }
    // noch zu testen
    // Map nicht uebergeben, sondern public zugreifen koennen
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void movementrange(int xposition, int yposition, Map map) {
        movementrange.clear();

        //Aufrufen der movementrange
        map.getFeld(xposition, yposition).setMovement(movement);
        
        movementrange = movementrange2(this.getXPosition(), this.getYPosition(), map, movement, movementrange);
       // movementrange = movementrangel2(this.getXPosition(), this.getYPosition(), map, movement, movementrange);

        //Zuruecksetzen aller moeglicherweise gecheckter Felder auf checked=false
        for (int i = this.getXPosition() - movement; i < this.getXPosition() + movement + 1; i++) {
            if (i >= 0 && i <= map.getWidth() - 1) {
                for (int j = this.getYPosition() - movement; j < this.getYPosition() + movement + 1; j++) {
                    if (j >= 0 && j <= map.getHeight() - 1) {
                        map.getFeld(i, j).setMovement(0);
                    }
                }
            }
        }
    }

    public ArrayList<int[]> movementrange2(int xposition, int yposition, Map map, int movement, ArrayList<int[]> movementr) {

        if (movement == 0) {
            return movementr;
        }
        
        //Abrufen des tatsächlichen Bewegungsradius von diesem Feld ausgehend
        if(map.getFeld(xposition, yposition).getMovement()>movement) movement=map.getFeld(xposition, yposition).getMovement();
        
        //aktuelles Feld des Charakters ist keine Zugoption, betrachtetes Feld darf nicht außerhalb map sein
        if (!(xPosition == xposition + 1 && yPosition == yposition) && xposition + 1 < map.getWidth()) {
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion

            //Ueberpruefung, ob Charakter auf Terrain des betrachteten Feldes darf und ob Feld frei ist
            if (!map.getFeld(xposition + 1, yposition).isOccupied()) {
//                !movementr.contains(visited) && 
                if (map.getFeld(xposition + 1, yposition).getMovement()<movement && !blockedterrains.contains(map.getFeld(xposition + 1, yposition).getTerrainName())) {
                    if (1 + Math.abs(map.getFeld(xposition + 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
                        
                        movementr.add(new int[]{xposition + 1, yposition});

                        //Aktualisieren des möglichen Bewegungsradius an diesem Feld
                        if(map.getFeld(xposition, yposition).getMovement()<movement) map.getFeld(xposition, yposition).setMovement(movement);
                         
                        movementrange2(xposition + 1, yposition, map, movement - (1 + Math.abs(map.getFeld(xposition + 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight())), movementr);
                    }
                }
            }
        }
        
        if(map.getFeld(xposition, yposition).getMovement()>movement) movement=map.getFeld(xposition, yposition).getMovement();
        
        if (!(xPosition == xposition - 1 && yPosition == yposition) && xposition - 1 >= 0) {
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            if (!map.getFeld(xposition - 1, yposition).isOccupied()) {
                if (map.getFeld(xposition - 1, yposition).getMovement()<movement && !blockedterrains.contains(map.getFeld(xposition - 1, yposition).getTerrainName())) {
                    
                    if (1 + Math.abs(map.getFeld(xposition - 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
                        movementr.add(new int[]{xposition - 1, yposition});
                        
                        if(map.getFeld(xposition, yposition).getMovement()<movement) map.getFeld(xposition, yposition).setMovement(movement);
                        
                        movementrange2(xposition - 1, yposition, map, movement - (1 + Math.abs(map.getFeld(xposition - 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight())), movementr);

                    }
                }
            }
        }

        if(map.getFeld(xposition, yposition).getMovement()>movement) movement=map.getFeld(xposition, yposition).getMovement();
        
        if (!(xPosition == xposition && yPosition == yposition + 1) && yposition + 1 < map.getHeight()) {
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion

            if (!map.getFeld(xposition, yposition + 1).isOccupied()) {
                if (map.getFeld(xposition, yposition+1).getMovement()<movement && !blockedterrains.contains(map.getFeld(xposition, yposition + 1).getTerrainName())) {
                    if (1 + Math.abs(map.getFeld(xposition, yposition + 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
                        movementr.add(new int[]{xposition, yposition + 1});
                        
                        if(map.getFeld(xposition, yposition).getMovement()<movement) map.getFeld(xposition, yposition).setMovement(movement);
                        
                        movementrange2(xposition, yposition + 1, map, movement - (1 + Math.abs(map.getFeld(xposition, yposition + 1).getHeight() - map.getFeld(xposition, yposition).getHeight())), movementr);

                    }
                }

            }
        }
        
        if(map.getFeld(xposition, yposition).getMovement()>movement) movement=map.getFeld(xposition, yposition).getMovement();
        
        if (!(xPosition == xposition && yPosition == yposition - 1) && yposition - 1 >= 0) {
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion

            if (!map.getFeld(xposition, yposition - 1).isOccupied()) {
                if (map.getFeld(xposition, yposition-1).getMovement()<movement && !blockedterrains.contains(map.getFeld(xposition, yposition - 1).getTerrainName())) {
                    if (1 + Math.abs(map.getFeld(xposition, yposition - 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
                        movementr.add(new int[]{xposition, yposition - 1});
                        
                        if(map.getFeld(xposition, yposition).getMovement()<movement) map.getFeld(xposition, yposition).setMovement(movement);

                        movementrange2(xposition, yposition - 1, map, movement - (1 + Math.abs(map.getFeld(xposition, yposition - 1).getHeight() - map.getFeld(xposition, yposition).getHeight())), movementr);
                    }
                }
            }
        }
        // Rueckgabe ist Arraylist aus Arrays, Laenge 2, in der alle Koordinatenduos der
        // belaufbaren Felder gespeichert sind, können doppelt vorkommen
        return movementr;
    }
    
    public void attackrange(int xposition, int yposition, Map map) {
        attackrangel.clear();

        //Aufrufen der attackrange
        map.getFeld(xposition, yposition).setMovement(attackrange);
        attackrangel = attackrange2(this.getXPosition(), this.getYPosition(), map, attackrange, attackrangel);

        //Zuruecksetzen aller moeglicherweise gecheckter Felder auf checked=false
        for (int i = this.getXPosition() - attackrange; i < this.getXPosition() + attackrange+1; i++) {
            if (i >= 0 && i <= map.getWidth() - 1) {
                for (int j = this.getYPosition() - attackrange; j < this.getYPosition() + attackrange+1; j++) {
                    if (j >= 0 && j <= map.getHeight() - 1) {
                        map.getFeld(i, j).setMovement(0);
                    }
                }
            }
        }
    }

    public ArrayList<int[]> attackrange2(int xposition, int yposition, Map map, int attackrange, ArrayList<int[]> attackr) {

        if (attackrange == 0) {
            return attackr;
        }

        //Abrufen des tatsächlichen Bewegungsradius von diesem Feld ausgehend
        if(map.getFeld(xposition, yposition).getMovement()>attackrange) attackrange=map.getFeld(xposition, yposition).getMovement();
        
        //aktuelles Feld des Charakters ist keine Zugoption, betrachtetes Feld darf nicht außerhalb map sein
        if (!(xPosition == xposition + 1 && yPosition == yposition) && xposition + 1 < map.getWidth()) {

            //Ueberpruefung, ob Charakter auf Terrain des betrachteten Feldes darf und ob Feld frei ist
            if (map.getFeld(xposition+1, yposition).getMovement()<attackrange && !blockedterrainsattack.contains(map.getFeld(xposition + 1, yposition).getTerrainName())) {
                if (1 + Math.abs(map.getFeld(xposition + 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= attackrange) {

                    //Aktualisieren des möglichen Bewegungsradius an diesem Feld
                    if(map.getFeld(xposition, yposition).getMovement()<attackrange) map.getFeld(xposition, yposition).setMovement(attackrange);
                       
                    //Hinzufuegen des Feldes wenn gegnerischer Spieler auf Feld steht
                    if (map.getFeld(xposition + 1, yposition).isOccupied()) {
                        if (!map.getFeld(xposition + 1, yposition).getOccupiedby().equals(this.playername)) {
                            attackr.add(new int[]{xposition + 1, yposition});
                        }
                    }

                    attackrange2(xposition + 1, yposition, map, attackrange - (1 + Math.abs(map.getFeld(xposition + 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight())), attackr);
                }
            }

        }

        if(map.getFeld(xposition, yposition).getMovement()>attackrange) attackrange=map.getFeld(xposition, yposition).getMovement();
        
        if (!(xPosition == xposition - 1 && yPosition == yposition) && xposition - 1 >= 0) {

            if (map.getFeld(xposition-1, yposition).getMovement()<attackrange && !blockedterrainsattack.contains(map.getFeld(xposition - 1, yposition).getTerrainName())) {
                if (1 + Math.abs(map.getFeld(xposition - 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= attackrange) {

                    //Aktualisieren des möglichen Bewegungsradius an diesem Feld
                    if(map.getFeld(xposition, yposition).getMovement()<attackrange) map.getFeld(xposition, yposition).setMovement(attackrange);
                        
                    //Hinzufuegen des Feldes wenn gegnerischer Spieler auf Feld steht
                    if (map.getFeld(xposition - 1, yposition).isOccupied()) {
                        if (!map.getFeld(xposition - 1, yposition).getOccupiedby().equals(this.playername)) {
                            attackr.add(new int[]{xposition - 1, yposition});
                        }
                    }

                    attackrange2(xposition - 1, yposition, map, attackrange - (1 + Math.abs(map.getFeld(xposition - 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight())), attackr);
                }
            }
        }

        if(map.getFeld(xposition, yposition).getMovement()>attackrange) attackrange=map.getFeld(xposition, yposition).getMovement();
        
        if (!(xPosition == xposition && yPosition == yposition + 1) && yposition + 1 < map.getHeight()) {

            if (map.getFeld(xposition, yposition+1).getMovement()<attackrange && !blockedterrainsattack.contains(map.getFeld(xposition, yposition + 1).getTerrainName())) {
                if (1 + Math.abs(map.getFeld(xposition, yposition + 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= attackrange) {

                    //Aktualisieren des möglichen Bewegungsradius an diesem Feld
                    if(map.getFeld(xposition, yposition).getMovement()<attackrange) map.getFeld(xposition, yposition).setMovement(attackrange);
                    
                    //Hinzufuegen des Feldes wenn gegnerischer Spieler auf Feld steht
                    if (map.getFeld(xposition, yposition + 1).isOccupied()) {
                        if (!map.getFeld(xposition, yposition + 1).getOccupiedby().equals(this.playername)) {
                            attackr.add(new int[]{xposition, yposition + 1});
                        }
                    }

                    attackrange2(xposition, yposition + 1, map, attackrange - (1 + Math.abs(map.getFeld(xposition, yposition + 1).getHeight() - map.getFeld(xposition, yposition).getHeight())), attackr);
                }
            }

        }

        if(map.getFeld(xposition, yposition).getMovement()>attackrange) attackrange=map.getFeld(xposition, yposition).getMovement();
        
        if (!(xPosition == xposition && yPosition == yposition - 1) && yposition - 1 >= 0) {

            if (map.getFeld(xposition, yposition-1).getMovement()<attackrange && !blockedterrainsattack.contains(map.getFeld(xposition, yposition - 1).getTerrainName())) {
                if (1 + Math.abs(map.getFeld(xposition, yposition - 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= attackrange) {

                    //Aktualisieren des möglichen Bewegungsradius an diesem Feld
                    if(map.getFeld(xposition, yposition).getMovement()<attackrange) map.getFeld(xposition, yposition).setMovement(attackrange);
                        
                    //Hinzufuegen des Feldes wenn gegnerischer Spieler auf Feld steht
                    if (map.getFeld(xposition, yposition - 1).isOccupied()) {
                        if (!map.getFeld(xposition, yposition - 1).getOccupiedby().equals(this.playername)) {
                            attackr.add(new int[]{xposition, yposition - 1});
                        }
                    }

                    attackrange2(xposition, yposition - 1, map, attackrange - (1 + Math.abs(map.getFeld(xposition, yposition - 1).getHeight() - map.getFeld(xposition, yposition).getHeight())), attackr);
                }
            }

        }
        // Rueckgabe ist Arraylist aus Arrays, Laenge 2, in der alle Koordinatenduos der
        // belaufbaren Felder gespeichert sind, können doppelt vorkommen
        return attackr;
    }

    public boolean getalive() {
        return alive;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public List<int[]> getMovementrange() {

        return movementrange;
    }

    public ArrayList<String> getBlockedterrains() {
        return blockedterrains;
    }

    public ArrayList<int[]> getAttackrange() {
        return attackrangel;
    }

    public Image getPicture() {
        return curAnimation.getCurImg();
    }

    public boolean isFlipped() {
        return flipped;
    }

    public abstract void move();

    public int getDisplacementX() {
        return displacementX;
    }

    public int getDisplacementY() {
        return displacementY;
    }

}
