package character;

import java.awt.Image;

public class Builder extends Character {

    // der Builder muss nur auf einem nicht fertigem Gebäude stehen
    // dann wird die buildtime automatisch gesenkt am ende der Runde
    public Builder(int x, int y, String playername) {
        super(playername);
        this.xPosition = x;
        this.yPosition = y;
        maxHealth = 1;
        healthpoints = maxHealth;
        canmove = true;
        motivation = 1.0;
        moverange = 3;
        attackrange=0;
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
        return picture;
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
