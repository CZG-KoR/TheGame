/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Character;

/**
 *
 * @author Cedric
 */
public class Warrior extends Fighter{

    public Warrior(String playername, int x, int y) {
        super(playername);
        
        //Festlegen der Werte des Warriors
        healthpoints=10;
        movement=2;
        motivation=1;
        attackrating=2;
        attackrange=1;
        
        xPosition=x;
        yPosition=y;
    }

    @Override
    public void move() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //Anzeigen der movementrange fuer den Charakter
        //movementrange(xPosition, yPosition, );
    }
    
}
