package character;

import gui.Animation;
import java.awt.Image;
import map.Map;
import tools.MiscUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static launcher.Start.players;

public abstract class Character implements Killable {

    // Anzahl an Leben für ein Character bis dieser "entfernt" wird
    protected int healthpoints;
    // Bewegungsreichweite
    protected int movement;
    // Wurde die Figur schon bewegt?
    protected boolean canmove;
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
    
    //Movementrange, die bei jedem Zug neu berechnet werden muss
    protected ArrayList<int[]> movementrange = new ArrayList();

    // Spieler zu dem Character gehoert
    private String playername;

    protected boolean alive = true;

    protected Character(String playername) {
        this.playername = playername;
        animationen = new HashMap<>();
    }

    // Bewegung muss für die einzelnen Charaktere definiert werden
    public abstract void move();

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

    // public void killed() {
    //
    // }

    // noch zu testen
    // Map nicht uebergeben, sondern public zugreifen koennen
    public void movementrange(int xposition, int yposition, Map map) {
//        List<int[]> movementr = new ArrayList<>();
        // movementr.add(new int[]{2,3});
        movementrange.clear();
        movementrange = movementrange2(this.getXPosition(), this.getYPosition(), map, movement, movementrange);
        
//        return movementr;
    }

    public ArrayList<int[]> movementrange2(int xposition, int yposition, Map map, int movement, ArrayList<int[]> movementr) {
        System.out.println("movement"+movement);
//        if (map.getFeld(xPosition, yPosition).getHeight() > movement) {
//            return movementr;
//        }
        if(movement==0) return movementr;
//
//Test, ob betrachtetes Feld bereits betrachtet wurde
        int[] lol = new int[2];
        lol[0]=0;
        lol[1]=0;
        movementr.add(lol);
       if(movementr.contains(lol)) System.out.println("hello");
       
        if(xposition+1<map.getWidth() && !movementr.contains(new int[]{xposition + 1, yposition})){
        if (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight())<= movement) {
//            System.out.println("xpos "+(xPosition+1));
            movementr.add(new int[] { xposition + 1, yposition });
            System.out.println("1i  "+movement);
            movementrange2(xposition + 1, yposition, map, movement - (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr);
        }
        }
        
        if(xposition-1>=0 && !movementr.contains(new int[]{xposition - 1, yposition})){
        if (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
            movementr.add(new int[] { xposition - 1, yposition });
            System.out.println("3i  "+movement);
            movementrange2(xposition - 1, yposition, map, movement - (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr);
        }
        }

        if(yposition+1<map.getHeight() && !movementr.contains(new int[]{xposition, yposition+1})){
        if (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
            movementr.add(new int[] { xposition, yposition + 1 });
            System.out.println("2i  "+movement);
            movementrange2(xposition, yposition + 1, map, movement - (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr);
        }
        }

        if(yposition-1>=0 && !movementr.contains(new int[]{xposition, yposition-1})){
        if (1+Math.abs(map.getFeld(xposition + 1, yposition - 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
            movementr.add(new int[] { xposition, yposition - 1 });
            System.out.println("4i  "+movement);
            movementrange2(xposition, yposition - 1, map, movement - (1+Math.abs(map.getFeld(xposition, yposition - 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr);
        }
        }
        // Rueckgabe ist Arraylist aus Arrays, Laenge 2, in der alle Koordinatenduos der
        // belaufbaren Felder gespeichert sind, können doppelt vorkommen
        return movementr;
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

    public abstract Image getPicture();
    
    

}
