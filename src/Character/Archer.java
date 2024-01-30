package Character;

public class Archer extends Fighter {

    public Archer(String playername, int x, int y) {
        super(playername);
        super.attackrange = 7;
        super.attackrating = 1;
        super.canattack = true;
        super.healthpoints = 2;
        super.movement = 3;

        super.xPosition = x;
        super.yPosition = y;
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void killed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
