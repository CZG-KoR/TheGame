package map;

import building.Building;
import tools.MiscUtils;

import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

public class Terrain {

    private static final Image[] imageA = MiscUtils.loadImages("src/gui/res/terrain");
    // Name
    private String terrainname;

    // Eigenschaften eines Terrains
    private int rangemultiplier; // maximale Laufweite auf einem Terrain (muss noch gemacht werden, wenn Truppen bekannt)    

    // Gebaude, die auf Terrain gebaut werden koennen
    private ArrayList<Building> buildablebuildings = new ArrayList<>(); // (muss noch gemacht werden, wenn Gebäude bekannt)    

    // Bild für das Terrain
    private Image terrainpicture;

    public Terrain(String terrainname) {
        this.terrainname = terrainname;
        switch (terrainname) {
            case "desert":
                this.rangemultiplier = 1;
                this.terrainpicture = imageA[0];
                break;

            case "forest":
                this.rangemultiplier = 1;
                this.terrainpicture = imageA[1];
                break;

            case "grass":
                this.rangemultiplier = 1;
                this.terrainpicture = imageA[2];
                break;

            case "water":
                this.rangemultiplier = 1;
                this.terrainpicture = imageA[3];
                break;

            default:
                throw new AssertionError();
        }

    }

    public String getName() {
        return terrainname;
    }

    public Image getPicture() {
        return terrainpicture;
    }

    public int getRangemultiplier() {
        return rangemultiplier;
    }

    public List<Building> getBuildablebuildings() {
        return buildablebuildings;
    }
   
    
}
