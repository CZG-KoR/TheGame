/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Character;

/**
 *
 * @author guest-mfscil
 */
public class Archer extends Fighter {

    public Archer(String playername, int x, int y) {
        super(playername);
        super.attackrange=7;
        super.attackrating=1;
        super.canattack = true;
        super.healthpoints = 2;
        super.movement = 3;
        
        super.xPosition = x;
        super.yPosition = y;
    }

    
    
    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
