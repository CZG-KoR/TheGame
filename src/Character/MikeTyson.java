package character;

import map.Player;

public abstract class MikeTyson extends Hero {

    protected MikeTyson(String playername) {
        super(playername);
        info = "";
    }

    public void ability(Player player) {
        // erhöht attackrange und attackrating um z.B. 1 // Fighter.attackrating++;
    }

    public void ultimate(Player player) {
        if (player.getKills() > 25) {
            // erzeugen von zehn standardfightern
        }
    }

}
