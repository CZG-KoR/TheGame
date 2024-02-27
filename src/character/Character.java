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
    // Bewegungsreichweite (niemals hoeher als 8)
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
    
    protected ArrayList<String> blockedterrains = new ArrayList();
    
    //Movementrange, die bei jedem Zug neu berechnet werden muss
    protected ArrayList<int[]> movementrange = new ArrayList();
    protected ArrayList<int[]> attackrangel = new ArrayList();

    // Spieler zu dem Character gehoert
    private String playername;

    protected boolean alive = true;

    protected Character(String playername) {
        this.playername = playername;
        blockedterrains();
        animationen = new HashMap<>();
    }

    public boolean isCanmove() {
        return canmove;
    }

    public void setCanmove(boolean canmove) {
        this.canmove = canmove;
    }


    //Festlegen der geblockten Terrains für Charaktere
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
        //        List<int[]> movementr = new ArrayList<>();
        // movementr.add(new int[]{2,3});
        int[] visited = new int[2];
        
        movementrange.clear();
        movementrange = movementrange2(this.getXPosition(), this.getYPosition(), map, movement, movementrange, visited);
        
//        return movementr;
    }


    public ArrayList<int[]> movementrange2(int xposition, int yposition, Map map, int movement, ArrayList<int[]> movementr, int[] visited) {
        
//        if (map.getFeld(xPosition, yPosition).getHeight() > movement) {
//            return movementr;
//        }
        if(movement==0) return movementr;
        System.out.println("hi");
//
//Test, ob betrachtetes Feld bereits betrachtet wurde
        
/*
  Merge conflict - da ich nicht wusste, was die aktuelle Version ist, habe ich die untenstehende ausskommentiert.
*/
      
      
//   public ArrayList<int[]> movementrange2(int xposition, int yposition, Map map, int movement, ArrayList<int[]> movementr, int[] visited) {
//        System.out.println("movement"+movement);
        
//       if(movement==0) return movementr;

       
        //Test, ob betrachtetes Feld bereits betrachtet wurde
//        int[] visited = new int[2];
       
        //akteulles Feld des Charakters ist keine Zugoption, betrachtetes Feld darf nicht außerhalb map sein
        if(!(xPosition==xposition+1 && yPosition==yposition) && xposition+1<map.getWidth()){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition+1;
            visited[1]=yposition;
            
            //Ueberpruefung, ob Charakter auf Terrain des betrachteten Feldes darf und ob Feld frei ist
            if(!map.getFeld(xposition+1, yposition).isOccupied()){
            if(!movementr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition+1, yposition).getTerrainName())){
        if (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight())<= movement) {
//            System.out.println("xpos "+(xPosition+1));
            movementr.add(new int[] { xposition + 1, yposition });
            
            movementrange2(xposition + 1, yposition, map, movement - (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr, visited);
//            System.out.println("1i  "+movement);
            //movementrange2(xposition + 1, yposition, map, movement - (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr, visited);
        }
            }
        }
        }
        
        
        if(!(xPosition==xposition-1 && yPosition==yposition) && xposition-1>=0){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition-1;
            visited[1]=yposition;
            
            if(!map.getFeld(xposition-1, yposition).isOccupied()){
            if(!movementr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition-1, yposition).getTerrainName())){
        if (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
            movementr.add(new int[] { xposition - 1, yposition });
            
            movementrange2(xposition - 1, yposition, map, movement - (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr, visited);

//            System.out.println("3i  "+movement);
           // movementrange2(xposition - 1, yposition, map, movement - (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr, visited);

        }
        }
            }
        }

        if(!(xPosition==xposition && yPosition==yposition+1) && yposition+1<map.getHeight()){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition;
            visited[1]=yposition+1;
            
            if(!map.getFeld(xposition, yposition+1).isOccupied()){
            if(!movementr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition, yposition+1).getTerrainName())){
        if (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
            movementr.add(new int[] { xposition, yposition + 1 });
          
            movementrange2(xposition, yposition + 1, map, movement - (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr, visited);

//            System.out.println("2i  "+movement);
            //movementrange2(xposition, yposition + 1, map, movement - (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr, visited);
        }
            }

        }
        }

        
         if(!(xPosition==xposition && yPosition==yposition-1) && yposition-1>=0){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition;
            visited[1]=yposition-1;
            
            if(!map.getFeld(xposition, yposition-1).isOccupied()){
            if(!movementr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition, yposition-1).getTerrainName())){
        if (1+Math.abs(map.getFeld(xposition + 1, yposition - 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= movement) {
            movementr.add(new int[] { xposition, yposition - 1 });

            
            movementrange2(xposition, yposition - 1, map, movement - (1+Math.abs(map.getFeld(xposition, yposition - 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr, visited);

//            System.out.println("4i  "+movement);
            //movementrange2(xposition, yposition - 1, map, movement - (1+Math.abs(map.getFeld(xposition, yposition - 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),movementr, visited);

        }
            }
        }
         }
        // Rueckgabe ist Arraylist aus Arrays, Laenge 2, in der alle Koordinatenduos der
        // belaufbaren Felder gespeichert sind, können doppelt vorkommen
        return movementr;
    }
    
     public void attackrange(int xposition, int yposition, Map map) {
        int[] visited = new int[2];
        
        attackrangel.clear();
        attackrangel = attackrange2(this.getXPosition(), this.getYPosition(), map, attackrange, attackrangel, visited);
    }

    public ArrayList<int[]> attackrange2(int xposition, int yposition, Map map, int attackrange, ArrayList<int[]> attackr, int[] visited) {
//        System.out.println("movement"+movement);
        
        if(attackrange==0) return attackr;
       
        //Test, ob betrachtetes Feld bereits betrachtet wurde
//        int[] visited = new int[2];
       
        //akteulles Feld des Charakters ist keine Zugoption, betrachtetes Feld darf nicht außerhalb map sein
        if(!(xPosition==xposition+1 && yPosition==yposition) && xposition+1<map.getWidth()){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition+1;
            visited[1]=yposition;
            
            //Ueberpruefung, ob Charakter auf Terrain des betrachteten Feldes darf und ob Feld frei ist
            if(map.getFeld(xposition+1, yposition).isOccupied() ){
            if(!attackr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition+1, yposition).getTerrainName())){
        if (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight())<= attackrange) {
//            System.out.println("xpos "+(xPosition+1));
            attackr.add(new int[] { xposition + 1, yposition });
//            System.out.println("1i  "+movement);
            movementrange2(xposition + 1, yposition, map, attackrange - (1+Math.abs(map.getFeld(xposition + 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),attackr, visited);
        }
            }
        }
        }
        
        
        if(!(xPosition==xposition-1 && yPosition==yposition) && xposition-1>=0){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition-1;
            visited[1]=yposition;
            
            if(map.getFeld(xposition-1, yposition).isOccupied() ){
            if(!attackr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition-1, yposition).getTerrainName())){
        if (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= attackrange) {
            attackr.add(new int[] { xposition - 1, yposition });
//            System.out.println("3i  "+movement);
            movementrange2(xposition - 1, yposition, map, attackrange - (1+Math.abs(map.getFeld(xposition - 1, yposition).getHeight()-map.getFeld(xposition, yposition).getHeight())),attackr, visited);
        }
        }
            }
        }

        if(!(xPosition==xposition && yPosition==yposition+1) && yposition+1<map.getHeight()){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition;
            visited[1]=yposition+1;
            
            if(map.getFeld(xposition, yposition+1).isOccupied() ){
            if(!attackr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition, yposition+1).getTerrainName())){
        if (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= attackrange) {
            attackr.add(new int[] { xposition, yposition + 1 });
//            System.out.println("2i  "+movement);
            movementrange2(xposition, yposition + 1, map, attackrange - (1+Math.abs(map.getFeld(xposition, yposition + 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),attackr, visited);
        }
            }
        }
        }

        
         if(!(xPosition==xposition && yPosition==yposition-1) && yposition-1>=0){
            //Pruefen, ob Feld nicht bereits betrachtet wurde, Beschleunigung Rekursion
            visited[0]=xposition;
            visited[1]=yposition-1;
            
            if(map.getFeld(xposition, yposition-1).isOccupied() ){
            if(!attackr.contains(visited) && !blockedterrains.contains(map.getFeld(xposition, yposition-1).getTerrainName())){
        if (1+Math.abs(map.getFeld(xposition + 1, yposition - 1).getHeight() - map.getFeld(xposition, yposition).getHeight()) <= attackrange) {
            attackr.add(new int[] { xposition, yposition - 1 });
//            System.out.println("4i  "+movement);
            movementrange2(xposition, yposition - 1, map, attackrange - (1+Math.abs(map.getFeld(xposition, yposition - 1).getHeight()-map.getFeld(xposition, yposition).getHeight())),attackr, visited);
        }
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

    public abstract Image getPicture();
    
    public abstract void move();

}
