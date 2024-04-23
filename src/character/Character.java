package character;

import gui.Animation;
import gui.Tilemap;
import map.Map;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Character implements Killable {

    // ursprüngliche Anzahl an Leben
    protected int maxHealth;
    // Anzahl an Leben für ein Character bis dieser "entfernt" wird
    protected int healthpoints;
    // Bewegungsreichweite (niemals hoeher als 28)
    protected int movement;

    // Angriffsreichweite
    protected int attackrange;

    // Wurde die Figur schon bewegt?
    protected boolean canmove = true;
    // Motivation
    protected double motivation;
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

    protected List<String> blockedterrains = new ArrayList<>();
    protected List<String> blockedterrainsattack = new ArrayList<>();

    //Movementrange, die bei jedem Zug neu berechnet werden muss
    protected List<int[]> movementrange = new ArrayList<>();
    protected List<int[]> attackrangel = new ArrayList<>();

    // Spieler zu dem Character gehoert
    private String playername;

    protected boolean alive = true;

    protected Character(String playername) {
        motivation=1.0;
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
    
    public void playAnimationOnce(String name){
        
        if (animationen.containsKey(name)) {
            curAnimation.stop();
            curAnimation = animationen.get(name);
            curAnimation.playOnce();
        }

        
    }

    public void playMoveAnimation(int xGoal, int yGoal) {

        int deltaX = (xGoal - xPosition) * Tilemap.getN();
        int deltaY = (yGoal - yPosition) * Tilemap.getN();

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
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
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

            figh2.healthpoints = (int) (figh2.healthpoints - figh1.attackrating * figh1.motivation);

            if (figh2.healthpoints <= 0) {
                figh2.alive = false;
                Map.getFeld(figh2.getXPosition(), figh2.getYPosition()).setOccupied(false);
                Map.getFeld(figh2.getXPosition(), figh2.getYPosition()).setOccupiedby(null);
                figh2.playAnimationOnce("dead");
            } else {
                figh1.healthpoints = (int) (figh1.healthpoints - figh2.attackrating * figh2.motivation);

                if (figh1.healthpoints <= 0) {
                    figh1.alive = false;
                    Map.getFeld(figh1.getXPosition(), figh1.getYPosition()).setOccupied(false);
                    Map.getFeld(figh1.getXPosition(), figh1.getYPosition()).setOccupiedby(null);
                    figh1.playAnimationOnce("dead");
                }
            }
        }

        if (char1 instanceof Fighter && char2 instanceof Builder) {
            Fighter figh1 = (Fighter) (char1);
            char2.healthpoints = (int) (char2.healthpoints - figh1.attackrating * figh1.motivation);

            if (char2.healthpoints <= 0) {
                char2.alive = false;
                Map.getFeld(char2.getXPosition(), char2.getYPosition()).setOccupied(false);
                Map.getFeld(char2.getXPosition(), char2.getYPosition()).setOccupiedby(null);
                char2.playAnimationOnce("dead");
            }
        }

        if (char1 instanceof Builder && char2 instanceof Fighter) {
            Fighter figh2 = (Fighter) (char2);
            char1.healthpoints = (int) (char1.healthpoints - figh2.attackrating * figh2.motivation);

            if (char1.healthpoints <= 0) {
                char1.alive = false;
                Map.getFeld(char1.getXPosition(), char1.getYPosition()).setOccupied(false);
                Map.getFeld(char1.getXPosition(), char1.getYPosition()).setOccupiedby(null);
                char1.playAnimationOnce("dead");
            }
        }
    }

    public String getPlayername() {
        return playername;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    // noch zu testen
    // Map nicht uebergeben, sondern public zugreifen koennen
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void movementrange(int xposition, int yposition, Map map) {
        movementrange.clear();

        //Aufrufen der movementrange
        Map.getFeld(xposition, yposition).setMovement(movement);
        
        movementrange = movementrange2(this.getXPosition(), this.getYPosition(), map, movement, movementrange);

        //Zuruecksetzen aller moeglicherweise gecheckter Felder auf checked=false
        for (int i = this.getXPosition() - movement; i < this.getXPosition() + movement + 1; i++) {
            if (i >= 0 && i <= map.getWidth() - 1) {
                for (int j = this.getYPosition() - movement; j < this.getYPosition() + movement + 1; j++) {
                    if (j >= 0 && j <= map.getHeight() - 1) {
                        Map.getFeld(i, j).setMovement(0);
                    }
                }
            }
        }
    }

    public List<int[]> movementrange2(int xposition, int yposition, Map map, int movement, List<int[]> movementr) {

        if (movement == 0) {
            return movementr;
        }
        
        //Abrufen des tatsächlichen Bewegungsradius von diesem Feld ausgehend
        if (Map.getFeld(xposition, yposition).getMovement() > movement)
            movement = Map.getFeld(xposition, yposition).getMovement();
        
        movementrange2Helper(xposition, yposition, 1, 0, movement, movementr, map);
        
        if(Map.getFeld(xposition, yposition).getMovement() > movement)
            movement = Map.getFeld(xposition, yposition).getMovement();
        
        movementrange2Helper(xposition, yposition, -1, 0, movement, movementr, map);

        if (Map.getFeld(xposition, yposition).getMovement() > movement)
            movement = Map.getFeld(xposition, yposition).getMovement();
        
        movementrange2Helper(xposition, yposition, 0, 1, movement, movementr, map);
        
        if(Map.getFeld(xposition, yposition).getMovement() > movement)
            movement = Map.getFeld(xposition, yposition).getMovement();
        
        movementrange2Helper(xposition, yposition, 0, -1, movement, movementr, map);
        // Rueckgabe ist Arraylist aus Arrays, Laenge 2, in der alle Koordinatenduos der
        // belaufbaren Felder gespeichert sind, können doppelt vorkommen
        return movementr;
    }

    public void movementrange2Helper(int xposition, int yposition, int xOffset, int yOffset, int movement, List<int[]> movementr, Map map) {
        if (
            isInBounds(xposition, xOffset, yposition, yOffset, map)
            && !Map.getFeld(xposition + xOffset, yposition + yOffset).isOccupied()
            && validMovement(xposition, xOffset, yposition, yOffset, movement, blockedterrains)
            && validHeightDifference(xposition, xOffset, yposition, yOffset, movement)
        ) {
            movementr.add(new int[]{xposition + xOffset, yposition + yOffset});
            
            if (Map.getFeld(xposition, yposition).getMovement() < movement)
                Map.getFeld(xposition, yposition).setMovement(movement);
            
            movementrange2(xposition + xOffset, yposition + yOffset, map, movement - (1 + Math.abs(Map.getFeld(xposition + xOffset, yposition + yOffset).getHeight() - Map.getFeld(xposition, yposition).getHeight())), movementr);
        }
    }

    public void attackrange2Helper(int xposition, int yposition, int xOffset, int yOffset, int attackrange, List<int[]> attackr, Map map) {
        if (isInBounds(xposition, xOffset, yposition, yOffset, map)
            && validMovement(xposition, xOffset, yposition, yOffset, attackrange, blockedterrainsattack)
            && validHeightDifference(xposition, xOffset, yposition, yOffset, attackrange)
        ) {
            if (Map.getFeld(xposition, yposition).getMovement() < attackrange)
                Map.getFeld(xposition, yposition).setMovement(attackrange);
                
            if (Map.getFeld(xposition + xOffset, yposition + yOffset).isOccupied()
                && !Map.getFeld(xposition + xOffset, yposition + yOffset).getOccupiedby().equals(this.playername)
            ) attackr.add(new int[]{xposition + 1, yposition});

            attackrange2(xposition + 1, yposition, map, attackrange - (1 + Math.abs(Map.getFeld(xposition + 1, yposition).getHeight() - Map.getFeld(xposition, yposition).getHeight())), attackr);
        }
    }

    public boolean isInBounds(int xposition, int xOffset, int yposition, int yOffset, Map map) {
        return !(xPosition == xposition + xOffset && yPosition == yposition + yOffset) && xposition + xOffset >= 0 && xposition + xOffset < map.getWidth() && yposition + yOffset >= 0 && yposition + yOffset < map.getHeight();
    }

    public boolean validMovement(int xposition, int xOffset, int yposition, int yOffset, int movement, List<String> blocked) {
        return Map.getFeld(xposition + xOffset, yposition + yOffset).getMovement() < movement && !blocked.contains(Map.getFeld(xposition + xOffset, yposition + yOffset).getTerrainName());
    }
    
    public boolean validHeightDifference(int xposition, int xOffset, int yposition, int yOffset, int movement) {
        return 1 + Math.abs(Map.getFeld(xposition + xOffset, yposition + yOffset).getHeight() - Map.getFeld(xposition, yposition).getHeight()) <= movement;
    }

    public void attackrange(int xposition, int yposition, Map map) {
        attackrangel.clear();

        //Aufrufen der attackrange
        Map.getFeld(xposition, yposition).setMovement(attackrange);
        attackrangel = attackrange2(this.getXPosition(), this.getYPosition(), map, attackrange, attackrangel);

        //Zuruecksetzen aller moeglicherweise gecheckter Felder auf checked=false
        for (int i = this.getXPosition() - attackrange; i < this.getXPosition() + attackrange+1; i++) {
            if (i >= 0 && i <= map.getWidth() - 1) {
                for (int j = this.getYPosition() - attackrange; j < this.getYPosition() + attackrange+1; j++) {
                    if (j >= 0 && j <= map.getHeight() - 1) {
                        Map.getFeld(i, j).setMovement(0);
                    }
                }
            }
        }
    }

    public List<int[]> attackrange2(int xposition, int yposition, Map map, int attackrange, List<int[]> attackr) {

        if (attackrange == 0) {
            return attackr;
        }

        //Abrufen des tatsächlichen Bewegungsradius von diesem Feld ausgehend
        if(Map.getFeld(xposition, yposition).getMovement() > attackrange)
            attackrange = Map.getFeld(xposition, yposition).getMovement();
        
        //aktuelles Feld des Charakters ist keine Zugoption, betrachtetes Feld darf nicht außerhalb map sein
        attackrange2Helper(xposition, yposition, 1, 0, attackrange, attackr, map);

        if(Map.getFeld(xposition, yposition).getMovement() > attackrange)
            attackrange = Map.getFeld(xposition, yposition).getMovement();
        
        attackrange2Helper(xposition, yposition, -1, 0, attackrange, attackr, map);

        if(Map.getFeld(xposition, yposition).getMovement() > attackrange)
            attackrange = Map.getFeld(xposition, yposition).getMovement();
        
        attackrange2Helper(xposition, yposition, 0, 1, attackrange, attackr, map);

        if(Map.getFeld(xposition, yposition).getMovement() > attackrange)
            attackrange = Map.getFeld(xposition, yposition).getMovement();
        
        attackrange2Helper(xposition, yposition, 0, -1, attackrange, attackr, map);
        // Rueckgabe ist Arraylist aus Arrays, Laenge 2, in der alle Koordinatenduos der
        // belaufbaren Felder gespeichert sind, können doppelt vorkommen
        return attackr;
    }

    public boolean getalive() {
        return alive;
    }

    public int getHealthpoints() {
        return healthpoints;
    }

    public void setHealthpoints(int healthpoints) {
        this.healthpoints = healthpoints;
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

    public List<String> getBlockedterrains() {
        return blockedterrains;
    }

    public List<int[]> getAttackrange() {
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
    
    public boolean isCurAnimationPlaying(){
        return curAnimation.isIsPlaying();
    }

    public void setMotivation(double motivation) {
        this.motivation = motivation;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}