package character;

public class Horsemen extends Fighter{

    public Horsemen(String playername, int x, int y) {
        super(playername);
        super.healthpoints=3;
        super.movement = 5;
        super.attackrating =3;
        super.canattack = true;
        super.attackrange = 1;
        
        super.xPosition= x;
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
