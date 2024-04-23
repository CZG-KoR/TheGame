package building;

import map.Player;
import map.Map;

public class Lumberjack extends Building {

    // Abbaugeschwindigkeit des Holzes in holz pro runde
    private int chopspeed;

    private static final String FOREST = "forest";

    public Lumberjack(int xPosition, int yPosition) {
        super(); 
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 3;
        this.healthpoints = 3;
        this.buildingrange = 4;
        this.buildcost[0] = 5;
        this.chopspeed = 1;
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

    // anfang jeder runde Holz faellen 
    //Wälder nach 6 Runden abgebaut
    //braucht Map um ausgeführt zu werden
    int zaehl = 0;
    public void woodchop(Player player) {
        int[] forests = forests(xPosition, yPosition);
        if (forests.length != 0) {
            if (zaehl == 0 &&  (Map.getFeld(forests[0], forests[1]).getTerrainName().equals(FOREST))) {
                this.chopspeed = this.chopspeed * 3;
            }

            if (zaehl == 6 && Map.getFeld(forests[0], forests[1]).getTerrainName().equals(FOREST)) {
                if (forests(xPosition, yPosition).length != 0) {
                    //wenn rundrum auch wald, dann zaehl = 0 und das als neues bearbeitetes Feld betrachten
                    zaehl = 0;
                } else {
                    this.chopspeed = this.chopspeed / 3;
                }
            }
                zaehl++;
        }
        player.setWood(player.getWood() + chopspeed);
    }

    private int[] forests(int xPosition, int yPosition) {
        int[] forests = new int[2];
        for (int i = xPosition - 1; i < xPosition + 2; i++) {
            for (int j = yPosition - 1; j < yPosition + 2; j++) {
                if (Map.getFeld(i, j).getTerrainName().equals(FOREST)) {
                    forests[0] = i;
                    forests[1] = j;
                    return forests;
                }
            }

        }
        return new int[0];
    }
    
    @Override
    public void buildableterrains() {
        buildableterrains.add("water");
    }
}
