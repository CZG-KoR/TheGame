package character;

import map.Player;

public abstract class MainCharacter extends Hero {

    private int debt = 0;

    protected MainCharacter(String playername) {
        super(playername);
        this.info = "ApoRed (das heißt Red!) ist der Opa\n" +
                "        Hasen machen nicht immer dieselben stepper sie usen auch andere stepper um ihren Horizont zu weiten, denn ein Hase ohne wissen ist nur ein Go.\n"
                +
                "        // Vorbilder zu sneaken kommt immer nice, aber selbst zu seinem eigenen Vorbild zu werden ist das Ziel eines Hasen";
    }

    // ability (Burge-Geld) genereiert Ressourcen
    public void ability(Player player) {
        // cooldown adden vlt. für alle Heros
        player.setFood(player.getFood() + 5);
        player.setStone(player.getStone() + 5);
        player.setWood(player.getWood() + 5);
        debt = debt + 5;
    }

    // ultimate (Insi-Modus) kehrt deine Schulden in Ressourcen um und outplayed
    // Vater Staat. Es zährt allerdings an den Kräften des MainCharacter, weswegen
    // es Nahrung benötigt
    public void ultimate(Player player) {
        if (debt > 50 && player.getFood() >= 10) {
            player.setWood(player.getWood() + debt);
            player.setWood(player.getWood() + debt);
            player.setFood(player.getFood() - 10);
            debt = 0;
        }
    }

}
