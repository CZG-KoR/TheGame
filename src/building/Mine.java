package building;

import map.Map;
import map.Player;
import tools.MiscUtils;

public class Mine extends Building {
    private int miningspeed;

    //benoetigt map
    public Mine(String playername, int xPosition, int yPosition, Map m) {
        super(playername);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 2;
        this.healthpoints = 2;
        this.buildingrange = 2;
        this.miningspeed = 2;
        if (mountains(xPosition, yPosition, m)) {
            this.miningspeed = this.miningspeed *2;
        }
        picture = MiscUtils.loadImages("src/gui/res/building")[4];
    }

    public static boolean buildable(Player player) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();

        if (wood >= 1 && stone >= 1) {
            // Kosten des Bauens: 1 wood, 1 Stone
//            player.setWood(wood - 1);
//            player.setStone(stone - 1);
            // genug ressourcen, deswegen buildable true
            return true;
        }
        // bei false, soll das Gebäude nicht gebaut werden
        return false;
    }
        // stone erhoehen
        //Berge erhoehen effizienzn(in builder enthalten)
    public void mine(Player player) {
        player.setStone(player.getStone() + miningspeed);
    }
    private boolean mountains(int xPosition, int yPosition, Map m) {
        for (int i = xPosition - 1; i < xPosition +2; i++) {
            for (int j = yPosition - 1; j < yPosition+2; j++) {
                if (m.getFeld(i, j).getTerrainName().equals("mountain")) {
                    return true;
                }
            }
        }
        return false;
    }
}
