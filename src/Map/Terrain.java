package Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Building.Building;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * verschiedene Terrains erstellen
 *
 * @author guest-7gls9j
 */
public class Terrain {

    //Name
    String terrainname;

    //Eigenschaften eines Terrains
    int rangemultiplier;               //maximale Laufweite auf einem Terrain (muss noch gemacht werden, wenn Truppen bekannt)

    //Gebaude, die auf Terrain gebaut werden koennen
    ArrayList<Building> buildablebuildings = new ArrayList();  //(muss noch gemacht werden, wenn Gebäude bekannt)

    //Bild für das Terrain
    Image terrainpicture;

    public Terrain(String terrainname) {
        this.terrainname = terrainname;
        switch (terrainname) {
            case "grass":
                this.rangemultiplier = 1;
                this.terrainpicture = ImageLoader.imageA[0];
                break;
                
            case "forest":
                this.rangemultiplier = 1;
                this.terrainpicture = ImageLoader.imageA[1];
                break;
                
            case "desert":
                this.rangemultiplier = 1;
                this.terrainpicture = ImageLoader.imageA[2];
                break;
                
            case "water":
                this.rangemultiplier = 1;
                this.terrainpicture = ImageLoader.imageA[3];
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

}
