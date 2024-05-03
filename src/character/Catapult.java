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
        throw new UnsupportedOperationException();
    }

    @Override
    public void killed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Image getPicture() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void blockedterrains() {
        this.getBlockedterrains().add("water");
    }
    
    @Override
    public void blockedterrainsattack() {
        throw new UnsupportedOperationException();
    }

}
