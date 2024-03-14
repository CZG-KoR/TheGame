package character;

import java.awt.Image;

public class Catapult extends Fighter {

    public Catapult(String playername, int x, int y) {
        super(playername);

        // Festlegen der Werte des Warriors
        maxHealth = 20;
        healthpoints = maxHealth;
        movement = 1;
        attackrating = 8;
        attackrange = 6;

        xPosition = x;
        yPosition = y;
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void killed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Image getPicture() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void blockedterrains() {
        this.getBlockedterrains().add("water");
    }
    
    @Override
    public void blockedterrainsattack() {
    }

}
