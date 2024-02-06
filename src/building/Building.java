package building;

import map.Player;

public abstract class Building {
    // Dauer wie Lange gebaut wird
    protected int buildtime;

    // Funktionsweise der Gebäude überdenken
    protected int healthpoints;

    // Reichweitenvergrößerung des Landes
    protected int buildingrange;

    // Credits fuer Gebaeude zum Bauen und spaeter pro Runde
    // int buildcredits;
    // int creditsperround;

    // braucht man eigentlich nicht
    int[] buildcost = new int[2];

    public void build(Player player, int x, int y) {
        buildtime -= player.getBuilderAmount(x, y);
        if (buildtime < 0) {
            buildtime = 0;
        }
    }

    // Position
    int xPosition;
    int yPosition;

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
    
    

}
