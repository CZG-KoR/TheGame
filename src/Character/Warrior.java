package Character;

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

}
