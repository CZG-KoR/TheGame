package character;

import java.util.ArrayList;

public class Warrior extends Fighter {

    public Warrior(String playername, int x, int y) {
        super(playername);

        // Festlegen der Werte des Warriors
        healthpoints = 10;
        movement = 2;
        motivation = 1;
        attackrating = 2;
        attackrange = 1;

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
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        this.getBlockedterrains().add("water");
    }

}
