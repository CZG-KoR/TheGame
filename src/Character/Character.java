package Character;

import Map.Map;
import java.util.ArrayList;
import Map.player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guest-7gls9j
 */
public abstract class Character implements killable{
    // Anzahl an Leben für ein Character bis dieser "entfernt" wird
    int healthpoints;
    // Bewegungsreichweite
    int movement;
    // Wurde die Figur schon bewegt?
    boolean canmove;
    // Motivation
    int motivation;
    
    
    // Position
    int xPosition;
    int yPosition;
    
    //Spieler zu dem Character gehoert
    String playername;
    
    public Character(String playername){
        this.playername=playername;
    }
    
    
    // Bewegung muss für die einzelnen Charaktere definiert werden
    public abstract void move();
    
    // Kampf
    public void fight(Character char1, Character char2){
        if(char1.playername.equals(char2.playername)) return;   //Fehlercode ausgeben
        
        if (char1 instanceof Fighter && char2 instanceof Fighter) {
            Fighter figh1 = (Fighter) (char1);
            Fighter figh2 = (Fighter) (char2);
            if (figh1.canattack) {
                if (figh1.attackrange <= (int) (Math.sqrt((figh1.xPosition-figh2.xPosition)*(figh1.xPosition-figh2.xPosition) + (figh1.yPosition -figh2.yPosition)*(figh1.yPosition -figh2.yPosition)))) {
                     figh2.healthpoints = figh2.healthpoints-figh1.attackrating*figh1.motivation;
                }
            }
            
            if(figh2.healthpoints<=0){
                figh2.killed();
            }
            
            if (figh2.canattack && figh2.healthpoints>0) {
                if (figh2.attackrange <= (int) (Math.sqrt((figh1.xPosition-figh2.xPosition)*(figh1.xPosition-figh2.xPosition) + (figh1.yPosition -figh2.yPosition)*(figh1.yPosition -figh2.yPosition)))) {
                     figh1.healthpoints = figh1.healthpoints-figh2.attackrating*figh2.motivation;
                }
                
            }
        }
        
        if (char1 instanceof Fighter && char2 instanceof Builder) {
            Fighter figh1 = (Fighter) (char1);
            if (figh1.canattack) {
                if (figh1.attackrange <= (int) (Math.sqrt((figh1.xPosition-char2.xPosition)*(figh1.xPosition-char2.xPosition) + (figh1.yPosition -char2.yPosition)*(figh1.yPosition -char2.yPosition)))) {
                     char2.healthpoints = char2.healthpoints-figh1.attackrating*figh1.motivation;
                }
            }
            
            if(char2.healthpoints<=0){
                char2.killed();
            }
        }
        
        if (char1 instanceof Builder && char2 instanceof Fighter) {
            Fighter figh2 = (Fighter) (char2);
            if (figh2.canattack) {
                if (figh2.attackrange <= (int) (Math.sqrt((char1.xPosition-figh2.xPosition)*(char1.xPosition-figh2.xPosition) + (char1.yPosition -figh2.yPosition)*(char1.yPosition -figh2.yPosition)))) {
                     char1.healthpoints = char1.healthpoints-figh2.attackrating*figh2.motivation;
                }
                
            }
            
            if(char1.healthpoints<=0){
                char1.killed();
            }
        }
        
    }

    @Override
    public void killed() {
     //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
     //Entfernen aus Liste des Spielers
//        for (int i = 0; i < players.size(); i++) {
//            if(players.get(i).playername.equals(this.playername)){
//                players.get(i).Characters.remove(this);
//            }
//        }
     
     //Tötungsanimation
     
    }
    //noch nicht fertig(jonte):
      public void movementrange(int xposition, int yposition, Character chara, Map map) {
      ArrayList<int[]> movementr = new ArrayList<>();
      movementr.add(new int[]{2,3});
     // movementr = movementrange2(xposition, yposition, map, chara, movementr);
  }
      
      public ArrayList<ArrayList<Integer>> movementrange2(int xposition, int yposition,Map map, Character chara, ArrayList<ArrayList<Integer>> movementr) {
          if (map.get(xPosition, yPosition).getHeight()>chara.movement) {
              return movementr;
          }
          if (map.get(xPosition+1, yPosition).getHeight()<chara.movement) {
              chara.movement = chara.movement - map.get(xPosition+1, yPosition).getHeight();
              
             //movementr.add()
              movementrange2(xposition+1, yposition, map, chara, movementr);
          }
          if (map.get(xPosition-1, yPosition).getHeight()<chara.movement) {
              chara.movement = chara.movement - map.get(xPosition+1, yPosition).getHeight();
              movementrange2(xposition-1, yposition, map, chara, movementr);
          }
          if (map.get(xPosition, yPosition+1).getHeight()<chara.movement) {
              chara.movement = chara.movement - map.get(xPosition+1, yPosition).getHeight();
              movementrange2(xposition, yposition+1, map, chara, movementr);
          }
          if (map.get(xPosition+1, yPosition-1).getHeight()<chara.movement) {
              chara.movement = chara.movement - map.get(xPosition+1, yPosition).getHeight();
              movementrange2(xposition, yposition-1, map, chara, movementr);
          }
          return movementr;
      }
}
