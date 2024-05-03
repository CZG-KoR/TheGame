package character;

public abstract class Hero extends Character{

    protected String info;
   
    //int cooldown; ????
    
    protected Hero(String playername) {
        super(playername);
    }
    
    //jeder Held hat eine Fähigkeit
    public abstract void ability();
    //jeder Held hat eine Ult die er (einmal oder so) benutzen kann unter bestimmten Bedingungen
    public abstract void ultimate();
    
}
