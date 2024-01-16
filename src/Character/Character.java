package Character;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guest-7gls9j
 */
public abstract class Character {
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
    
    
    
    
    
    
    // Bewegung muss für die einzelnen Charaktere definiert werden
    public abstract void move();
    
    // Kampf
    public void fight(Character char1, Character char2){
        
        if (char1 instanceof Fighter && char2 instanceof Fighter) {
            Fighter figh1 = (Fighter) (char1);
            Fighter figh2 = (Fighter) (char2);
            if (figh1.canattack) {
                if (figh1.attackrange <= (int) (Math.sqrt((figh1.xPosition-figh2.xPosition)*(figh1.xPosition-figh2.xPosition) + (figh1.yPosition -figh2.yPosition)*(figh1.yPosition -figh2.yPosition)))) {
                     figh2.healthpoints = figh2.healthpoints-figh1.attackrating*figh1.motivation;
                }
            }
            if (figh2.canattack) {
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
        }
        
        if (char1 instanceof Builder && char2 instanceof Fighter) {
            Fighter figh2 = (Fighter) (char2);
            if (figh2.canattack) {
                if (figh2.attackrange <= (int) (Math.sqrt((char1.xPosition-figh2.xPosition)*(char1.xPosition-figh2.xPosition) + (char1.yPosition -figh2.yPosition)*(char1.yPosition -figh2.yPosition)))) {
                     char1.healthpoints = char1.healthpoints-figh2.attackrating*figh2.motivation;
                }
                
            }
        }
    }
}
