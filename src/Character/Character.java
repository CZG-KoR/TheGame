package character;

import map.Map;
import tools.MiscUtils;

import java.util.ArrayList;
import java.util.List;

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

    // Spieler zu dem Character gehoert
    private String playername;

    protected boolean alive = true;

    protected Character(String playername) {
        this.playername = playername;
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

    // public void killed() {
    //
    // }

    // noch zu testen
    // Map nicht uebergeben, sondern public zugreifen koennen
    public void movementrange(int xposition, int yposition, Map map) {
        List<int[]> movementr = new ArrayList<>();
        // movementr.add(new int[]{2,3});
        movementr = movementrange2(xposition, yposition, map, movementr);
    }

    public List<int[]> movementrange2(int xposition, int yposition, Map map, List<int[]> movementr) {
        if (map.getFeld(xPosition, yPosition).getHeight() > movement) {
            return movementr;
        }

        if (map.getFeld(xPosition + 1, yPosition).getHeight() <= movement) {
            movement = movement - map.getFeld(xPosition + 1, yPosition).getHeight();
            movementr.add(new int[] { xPosition + 1, yPosition });
            movementrange2(xposition + 1, yposition, map, movementr);
        }

        if (map.getFeld(xPosition - 1, yPosition).getHeight() <= movement) {
            movement = movement - map.getFeld(xPosition - 1, yPosition).getHeight();
            movementr.add(new int[] { xPosition - 1, yPosition });
            movementrange2(xposition - 1, yposition, map, movementr);
        }

        if (map.getFeld(xPosition, yPosition + 1).getHeight() <= movement) {
            movement = movement - map.getFeld(xPosition, yPosition + 1).getHeight();
            movementr.add(new int[] { xPosition, yPosition + 1 });
            movementrange2(xposition, yposition + 1, map, movementr);
        }

        if (map.getFeld(xPosition + 1, yPosition - 1).getHeight() < movement) {
            movement = movement - map.getFeld(xPosition, yPosition - 1).getHeight();
            movementr.add(new int[] { xPosition, yPosition - 1 });
            movementrange2(xposition, yposition - 1, map, movementr);
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

}
