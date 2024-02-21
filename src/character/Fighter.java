package character;

public abstract class Fighter extends Character {
    // Angriffswert
    protected int attackrating;

    
    // Hat die Figur schon angegriffen?
    protected boolean canattack = false;

    protected Fighter(String playername) {
        super(playername);
    }

}
