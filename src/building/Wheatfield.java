package building;

import map.Player;
import tools.MiscUtils;

public class Wheatfield extends Building {

    //Feld gibt Motivationsboost durch "Nahrung"
    double motivationboost;
    //Abklingzeit für harvest, vlt. Änderung im Erscheinungsbild wenn der Weizen wächst
    int cooldown=1;
    //maximale cooldown-Zeit
    private int maxcooldowntime = 4;

    public Wheatfield(int xPosition, int yPosition) {
        super(); 
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 2;
        this.healthpoints = 2;
        this.buildingrange = 2;
        this.motivationboost = 1.0;
        
        
        picture = MiscUtils.loadImages("src/gui/res/building/")[2];
    }

    public boolean buildable(Player player) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();

        // Kosten des Bauens: 1 wood, 1 Stone
        // genug ressourcen, deswegen buildable true
        // bei false, soll das Gebäude nicht gebaut werden
        return wood >= 1 && stone >= 1;
    }

    public void harvest(Player player) {
        // nur möglich, wenn der cooldown abgelaufen ist - Erntezeit
        cooldown--;
        if (cooldown <= 0) {
            // motivationsboost
            player.setFood(player.getFood()+16);
            this.cooldown = maxcooldowntime;
        } else {
            // Ausgabe, dass der Cooldown noch nicht abgelaufen ist.
        }
    }
    
    @Override
    public void buildableterrains() {
        buildableterrains.add("water");
    }
}
