package building;

import map.Map;
import map.Player;

public class Fishinghouse extends Building {
    // wie lumberjack
    private int fishingspeed;

    public Fishinghouse(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 4;
        this.healthpoints = 2;
        this.buildingrange = 3;
        this.buildcost[0] = 5;
        this.fishingspeed = 2;
    }

    public boolean buildable(Player player, Map m) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();
        
        if (wood >= 1 && stone >= 1) {
            // Kosten des Bauens: 1 wood, 1 Stone
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            // genug ressourcen, deswegen buildable true
            //wenn neben Wasser
            if (waters(xPosition, yPosition, m)) {
                return true;
            }
        }
        // bei false, soll das Gebäude nicht gebaut werden
        return false;
    }

    // Anfang jede Runde fischen
    public void fish(Player player) {
        player.setFood(player.getFood() + fishingspeed);
    }
    private boolean waters(int xPosition, int yPosition, Map m) {
        for (int i = xPosition - 1; i < 3; i++) {
            for (int j = yPosition - 1; j < 3; j++) {
                if (m.getFeld(j, j).getTerrainName().equals("water")) {
                    return true;
                }
            }
        }
        return false;
    }
}
