package building;

import map.Player;
import map.Map;

public class Lumberjack extends Building {

    // Abbaugeschwindigkeit des Holzes in holz pro runde
    private int chopspeed;

    public Lumberjack(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 3;
        this.healthpoints = 3;
        this.buildingrange = 4;
        this.buildcost[0] = 5;
        this.chopspeed = 1;
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

    // anfang jeder runde Holz faellen 
    //Wälder nach 6 Runden abgebaut
    //braucht Map um ausgeführt zu werden
    int zaehl = 0;
    public void woodchop(Player player, Map m) {
        int[] forests = forests(xPosition, yPosition, m);
        if (forests != null) {
            if (zaehl == 0) {
                if (m.getFeld(forests[0], forests[1]).getTerrainName().equals("forest")) {
                    this.chopspeed = this.chopspeed * 3;
                }
            }
            if (zaehl == 6 & m.getFeld(forests[0], forests[1]).getTerrainName().equals("forest")) {
                //m.setT(forests[0], forests[1], "grass");
                if (forests(xPosition, yPosition, m) != null) {
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

    private int[] forests(int xPosition, int yPosition, Map m) {
        int[] forests = new int[2];
        for (int i = xPosition - 1; i < xPosition + 2; i++) {
            for (int j = yPosition - 1; j < yPosition + 2; j++) {
                if (m.getFeld(i, j).getTerrainName().equals("forest")) {
                    forests[0] = i;
                    forests[1] = j;
                    return forests;
                }
            }

        }
        return null;
    }
}
