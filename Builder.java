package character;

public class Builder extends Character {

    // der Builder muss nur auf einem nicht fertigem Gebäude stehen
    // dann wird die buildtime automatisch gesenkt am ende der Runde
    public Builder(int x, int y, String playername) {
        super(playername);
        this.xPosition = x;
        this.yPosition = y;
        healthpoints = 1;
        canmove = true;
        motivation = 1;
        moverange = 3;
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
