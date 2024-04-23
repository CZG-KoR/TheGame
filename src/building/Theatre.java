package building;

import map.Player;
import tools.MiscUtils;

public class Theatre extends Building {
    // Das Gebäude dient der Unterhaltung des Volkes

    // gibt Motivationsboost durch Unterhaltung
    protected double motivationboost;

    public Theatre(int xPosition, int yPosition) {
        super(); 
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 2;
        this.healthpoints = 2;
        this.buildingrange = 2;
        this.motivationboost = 1.1;
        picture = MiscUtils.loadImages("src/gui/res/building")[7];
    }

    public static boolean buildable(Player player) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();

        // Kosten des Bauens: 1 wood, 1 Stone
        // genug ressourcen, deswegen buildable true
        // bei false, soll das Gebäude nicht gebaut werden
        return wood >= 1 && stone >= 1;
    }

    //alle 3 oder so, Runden kann für ein Holz und ein Essen Theater gespielt werden -> Motivationsboost
    int zaehl = 0;

    public void theatreplay(Player player) {
        if (zaehl == 0 &&  (player.getWood() >= 1 && player.getFood() >= 1)) {
            // Kosten des theaters: 1 wood, 1 food
            player.setWood(player.getWood() - 1);
            player.setStone(player.getStone() - 1);
            player.setMotivation(player.getMotivation() * this.motivationboost);
            
            //Erhoehen der Motivation der Charaktere des Spielers
            for (int i = 0; i < player.getCharacterAmount(); i++) {
                player.getCharacter(i).setMotivation(player.getMotivation());
            }
        }

        zaehl++;
        if (zaehl == 3) {
            zaehl = 0;
        }
    }
    
    @Override
    public void buildableterrains() {
        buildableterrains.add("water");
    }

}
