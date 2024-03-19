package building;

import launcher.Start;
import map.Map;
import map.Player;
import tools.MiscUtils;

public class Fishinghouse extends Building {
    // wie lumberjack
    private int fishingspeed;

    public Fishinghouse(String playername, int xPosition, int yPosition) {
        super(playername);
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
        
        if (wood >= 1 && stone >= 1) {
            // Kosten des Bauens: 1 wood, 1 Stone
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            // genug ressourcen, deswegen buildable true
            //wenn neben Wasser
        }
        // bei false, soll das Gebäude nicht gebaut werden
        return false;
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
    private boolean waters(int xPosition, int yPosition, Map m) {
        for (int i = xPosition - 1; i < xPosition +2; i++) {
            for (int j = yPosition - 1; j < yPosition+2; j++) {
                if (m.getFeld(i, j).getTerrainName().equals("water")) {
                    return true;
                }
            }
        }
        return false;
    }
}
