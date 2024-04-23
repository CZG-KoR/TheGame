package character;

import java.awt.Image;

public class Horsemen extends Fighter{

    public Horsemen(String playername, int x, int y) {
        super(playername);
        maxHealth = 3;
        healthpoints = maxHealth;
        super.movement = 5;
        super.attackrating =3;
        super.canattack = true;
        super.attackrange = 1;
        
        super.xPosition= x;
        super.yPosition = y;
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
        this.getBlockedterrains().add("water");
    }
    
}
