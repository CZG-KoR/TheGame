package building;

import java.awt.Image;
import map.Player;

public abstract class Building {
    // Dauer wie Lange gebaut wird
    protected int buildtime;

    // Funktionsweise der Gebäude überdenken
    protected int healthpoints;

    // Reichweitenvergrößerung des Landes
    protected int buildingrange;
    
    private String playername;
    
    Image picture;

    // Bild
    Image[] buildingimg;
    // welches Bild aus gui.res.building
    int ImageID;

    
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
    

    protected Building(String playername) {
        this.playername = playername;
    }

    public Image getPicture(){
        return picture;
    }

    public int getImageID(){
        return ImageID;
    }
    
    public Image getImage(int ID){
        if (buildingimg.length == 0) {
            System.out.println("Gebäudebilder nicht geladen!");
            return null;
        }
        return buildingimg[ImageID];
    }

}
