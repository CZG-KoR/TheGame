package character;

import gui.Animation;
import java.awt.Image;
import tools.MiscUtils;

public class Warrior extends Fighter {

    
    public Warrior(String playername, int x, int y) {
        super(playername);

        // Festlegen der Werte des Warriors
        healthpoints = 10;
        movement = 2;
        motivation = 1;
        attackrating = 2;
        attackrange = 1;
        
        // animationen hinzufügen
        animationen.put("idle", new Animation(MiscUtils.loadImages("src/gui/res/kaempfer1/idle"), 300));
        animationen.put("dead", new Animation(MiscUtils.loadImages("src/gui/res/kaempfer1/dead"), 1000));
        animationen.put("walk", new Animation(MiscUtils.loadImages("src/gui/res/kaempfer1/walk"), 300));
        
        // curAnimation setzen und starten
        curAnimation = animationen.get("idle");
        playAnimation("idle");
        
        

        xPosition = x;
        yPosition = y;
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
        // Anzeigen der movementrange fuer den Charakter
        // movementrange(xPosition, yPosition, );
    }

    @Override
    public void killed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    

    @Override
    public void blockedterrains() {
        this.getBlockedterrains().add("water");
    }

}
