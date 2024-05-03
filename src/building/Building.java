package building;

import map.Player;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public abstract class Building {
    // Dauer wie Lange gebaut wird
    protected int buildtime;

    // Funktionsweise der Gebäude überdenken
    protected int healthpoints;

    // Reichweitenvergrößerung des Landes
    protected int buildingrange;
    
    //Gebiete, auf die Gebäude nicht gebaut werden kann
    protected ArrayList<String> buildableterrains = new ArrayList<>();
    
    Image picture;

    
    // Credits fuer Gebaeude zum Bauen und spaeter pro Runde -> buildCredits, creditsPerRound

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
    
    public abstract void buildableterrains();

    public int getyPosition() {
        return yPosition;
    }
    
    public int getbuilding(Building build) {
        if (build instanceof Lumberjack) {
            return 1;
        }
        if (build instanceof Fishinghouse) {
            return 2;
        }
        if (build instanceof Wheatfield) {
            return 3;
        }
        if (build instanceof Mine) {
            return 4;
        }
        return 0;
    }
    

    protected Building() {
        buildableterrains();
    }

    public Image getPicture(){
        return picture;
    }

    public int getBuildingrange() {
        return buildingrange;
    }

    public List<String> getBuildableterrains() {
        return buildableterrains;
    }
}
