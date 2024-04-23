package character;

import gui.Animation;
import tools.MiscUtils;

public class Archer extends Fighter {

    public Archer(String playername, int x, int y) {
        super(playername);
        attackrange = 7;
        attackrating = 1;
        canattack = true;
        maxHealth = 2;
        healthpoints = maxHealth;
        movement = 3;
        
        animationen.put("idle", new Animation(MiscUtils.loadImages("src/gui/res/warrior1/idle"), 300));
        animationen.put("dead", new Animation(MiscUtils.loadImages("src/gui/res/warrior1/dead"), 1000));
        animationen.put("walk", new Animation(MiscUtils.loadImages("src/gui/res/warrior1/walk"), 300));
        
        // curAnimation setzen und starten
        curAnimation = animationen.get("idle");
        playAnimation("idle");

        super.xPosition = x;
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
    public void blockedterrains() {
        this.getBlockedterrains().add("water");
    }
    
    @Override
    public void blockedterrainsattack() {
        throw new UnsupportedOperationException();
    }

}
