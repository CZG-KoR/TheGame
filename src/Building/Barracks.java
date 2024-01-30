package Building;

import Map.Player;

public class Barracks extends Building {

    public Barracks(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 2;
        this.healthpoints = 2;
        this.buildingrange = 2;
    }

    public boolean buildable(Player player) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();

        if (wood >= 1 && stone >= 1) {
            // Kosten des Bauens: 1 wood, 1 Stone
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            // genug ressourcen, deswegen buildable true
            return true;
        }
        // bei false, soll das Gebäude nicht gebaut werden
        return false;
    }

    public void generateFigther(Player player) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();

        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            //! nicht schon wieder bitte
            // new Fighter();
        } else {
            // Fehlermeldung
        }
    }

    //! das ist basically diesselbe Methode wie oben, das könnte (und sollte) man
    // vermutlich vereinigen
    public void generateFigther2(Player player) {
        // ApoRed (das heißt Red!) ist der Opa
        // Hasen machen nicht immer dieselben stepper sie usen auch andere stepper um
        // ihren Horizont zu weiten, denn ein Hase ohne wissen ist nur ein Go.
        // Vorbilder zu sneaken kommt immer nice, aber selbst zu seinem eigenen Vorbild
        // zu werden ist das Ziel eines Hasen

        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();

        if (wood >= 2 && stone >= 2 && food >= 4) {
            // Kosten des Fighter: 2 wood, 2 Stone, 4 Food
            player.setWood(wood - 2);
            player.setStone(stone - 2);
            player.setStone(food - 4);
            //! nicht schon wieder bitte
            // new Fighter2();
        } else {
            // Fehlermeldung
        }
    }
}
