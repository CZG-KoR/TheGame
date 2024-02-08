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
    
    protected ArrayList<String> blockedterrains = new ArrayList();
    
    //Movementrange, die bei jedem Zug neu berechnet werden muss
    protected ArrayList<int[]> movementrange = new ArrayList();

    // Spieler zu dem Character gehoert
    private String playername;

    protected boolean alive = true;

    protected Character(String playername) {
        this.playername = playername;
        blockedterrains();
    }

    // Bewegung muss für die einzelnen Charaktere definiert werden
    public abstract void move();
    
    public abstract void blockedterrains();

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
        //Erstellen einer neuen movementrange
        movementrange.clear();
        
        //zum Überprüfen, ob prinzipielle Rekursion bei Wegsuche funktioniert, wurden zwischenzeitig alle Höhen auf 0 gesetzt
//        for (int i = 0; i < map.getWidth(); i++) {
//            for (int j = 0; j < map.getWidth(); j++) {
//                map.getFeld(i, j).setHeight(0);
//            }
//        }
        
        //Ermitteln aller möglicher Felder mit doppeltem Vorkommen von Feldern
        movementrange = movementrange2(this.getXPosition(), this.getYPosition(), map, movement, movementrange);
        
        //Aussortieren doppelter Felder
        for (int i = 0; i < movementrange.size(); i++) {
            for (int j = i+1; j < movementrange.size(); j++) {
                if(movementrange.get(i)[0]==movementrange.get(j)[0] && movementrange.get(i)[1]==movementrange.get(j)[1]){
                    movementrange.remove(j);
                    j--;
                }
            }
        }
    }

    public ArrayList<int[]> movementrange2(int xposition, int yposition, Map map, int movement, ArrayList<int[]> movementr) {
        System.out.println("movement"+movement);
        if(movement==0) return movementr;
//
//Test, ob betrachtetes Feld bereits betrachtet wurde
        int[] visited = new int[2];
       
        //akteulles Feld des Charakters ist keine Zugoption, betrachtetes Feld darf nicht außerhalb map sein
        if(!(xPosition==xposition+1 && yPosition==yposition) && xposition+1<map.getWidth()){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition+1;
            visited[1]=yposition;
            
            //Ueberpruefung, ob Charakter auf Terrain des betrachteten Feldes darf
            if(!movementr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition+1, yposition).getTerrainName())){
                if (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight())<= movement) {
                    movementr.add(new int[] { xposition + 1, yposition });
//                  System.out.println("1i  "+movement);
                    movementrange2(xposition + 1, yposition, map, movement - (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr);
                    System.out.println(movement);
                }
            }
        }
        
        if(!(xPosition==xposition-1 && yPosition==yposition) && xposition-1>=0){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition-1;
            visited[1]=yposition;
            if(!movementr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition-1, yposition).getTerrainName())){
                if (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
                    movementr.add(new int[] { xposition - 1, yposition });
//                  System.out.println("3i  "+movement);
                    movementrange2(xposition - 1, yposition, map, movement - (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr);
                }
            }
        }

        if(!(xPosition==xposition && yPosition==yposition+1) && yposition+1<map.getHeight()){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition;
            visited[1]=yposition+1;
            if(!movementr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition, yposition+1).getTerrainName())){
                if (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
                    movementr.add(new int[] { xposition, yposition + 1 });
//                  System.out.println("2i  "+movement);
                    movementrange2(xposition, yposition + 1, map, movement - (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr);
                }
            }
        }

        if(!(xPosition==xposition && yPosition==yposition-1) && yposition-1>=0){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition;
            visited[1]=yposition-1;
            if(!movementr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition, yposition-1).getTerrainName())){
                if (1+Math.abs(map.getFeld(xposition, yposition - 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
                    movementr.add(new int[] { xposition, yposition - 1 });
//                  System.out.println("4i  "+movement);
                    movementrange2(xposition, yposition - 1, map, movement - (1+Math.abs(map.getFeld(xposition, yposition - 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr);
                }
            }
        }
        // Rueckgabe ist Arraylist aus Arrays, Laenge 2, in der alle Koordinatenduos der
        // belaufbaren Felder gespeichert sind, können doppelt vorkommen
        //hoehendifferenzen zwischen den feldern werden beachtet, es muss mindestens ein movement gemacht werden, um auf benachbartes feld zu kommen. für jede höhendifferenz ist weiteres moevemente nötig
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

    public ArrayList<String> getBlockedterrains() {
        return blockedterrains;
    }
    
    
    
    

}
