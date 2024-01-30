package character;

public abstract class Fighter extends Character {
    // Angriffswert
    protected int attackrating;

    // Angriffsreichweite
    protected int attackrange;
    // Hat die Figur schon angegriffen?
    protected boolean canattack = false;

    protected Fighter(String playername) {
        super(playername);
    }

}
