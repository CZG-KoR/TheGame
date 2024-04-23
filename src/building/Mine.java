package building;

import map.Map;
import map.Player;
import tools.MiscUtils;

public class Mine extends Building {
    private int miningspeed;

    //benoetigt map
    public Mine(int xPosition, int yPosition) {
        super();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 2;
        this.healthpoints = 2;
        this.buildingrange = 2;
        this.miningspeed = 2;
        if (mountains(xPosition, yPosition)) {
            this.miningspeed = this.miningspeed *2;
        }
        picture = MiscUtils.loadImages("src/gui/res/building")[4];
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
        // stone erhoehen
        //Berge erhoehen effizienzn(in builder enthalten)
    public void mine(Player player) {
        player.setStone(player.getStone() + miningspeed);
    }
    private boolean mountains(int xPosition, int yPosition) {
        for (int i = xPosition - 1; i < xPosition +2; i++) {
            for (int j = yPosition - 1; j < yPosition+2; j++) {
                if (Map.getFeld(i, j).getTerrainName().equals("mountain")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public void buildableterrains() {
        buildableterrains.add("water");
        buildableterrains.add("desert");
        buildableterrains.add("forest");
        buildableterrains.add("grass");
    }
}
