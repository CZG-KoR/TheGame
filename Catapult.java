package character;

public class Catapult extends Fighter {

    public Catapult(String playername, int x, int y) {
        super(playername);

        // Festlegen der Werte des Warriors
        healthpoints = 20;
        movement = 1;
        motivation = 1;
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
    public void blockedterrains() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        this.getBlockedterrains().add("water");
    }
}
