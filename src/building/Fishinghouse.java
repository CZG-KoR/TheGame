package building;

import map.Player;
import tools.MiscUtils;

public class Fishinghouse extends Building {
    // wie lumberjack
    private int fishingspeed;

    public Fishinghouse(int xPosition, int yPosition) {
        super();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 4;
        this.healthpoints = 2;
        this.buildingrange = 3;
        this.buildcost[0] = 5;
        this.fishingspeed = 2;
        
        picture = MiscUtils.loadImages("src/gui/res/building")[0];
    }

    public static boolean buildable(Player player) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();
        
        // Kosten des Bauens: 1 wood, 1 Stone
        // genug ressourcen, deswegen buildable true
        // wenn neben Wasser
        // bei false, soll das Gebäude nicht gebaut werden
        return wood >= 1 && stone >= 1;
    }

    // Anfang jede Runde fischen
    // alle zwei Runden drei runden ueberfischt
    int zael = 0;
    
    public void fish(Player player) {
        if (zael<2) {
            player.setFood(player.getFood() + fishingspeed*3);
        } else {
            player.setFood(player.getFood() + fishingspeed);
        }
        if (zael==4) {
            zael=0;
        }
        
    }
    
    @Override
    public void buildableterrains() {
        buildableterrains.add("light_mountain");
        buildableterrains.add("peak_mountain");
        buildableterrains.add("dark_mountain");
        buildableterrains.add("desert");
        buildableterrains.add("forest");
        buildableterrains.add("grass");
    }
}
