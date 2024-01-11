/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 *  verschiedene Terrains erstellen
 * @author guest-7gls9j
 */
class Terrain {
    //Name
    String terrainname;
    
    //Eigenschaften eines Terrains
    int rangemultiplier;               //maximale Laufweite auf einem Terrain (muss noch gemacht werden, wenn Truppen bekannt)
    
    //Gebaude, die auf Terrain gebaut werden koennen
    ArrayList<Building> buildablebuildings = new ArrayList();  //(muss noch gemacht werden, wenn Gebäude bekannt)
    
    //Bild für das Terrain
    BufferedImage terrainpicture;
    
    //ArrayList mit den Eigenschaften aller Terrains
    private final ArrayList<ArrayList> terrains = new ArrayList<ArrayList>() {
        {
            add(new ArrayList(){{add("grass"); add(1); add(new ArrayList<Building>()); add(ImageIO(new File("")));}});
            add(new ArrayList(){{add("water"); add(1); add(new ArrayList<Building>());}});
            add(new ArrayList(){{add("sand"); add(1); add(new ArrayList<Building>());}});
            
        }
    
        
    };
    
    public Terrain(String name) {
        //Suchen des richtigen Terrains in der Liste der Terrains
        for (int i = 0; i < terrains.size(); i++) {
            
            //Zuordnen der Eigenschaften zum gewuenschten Terrain
            if(name.equals((String) terrains.get(i).get(0))){
                terrainname= (String)  terrains.get(i).get(0);
                rangemultiplier= (Integer) terrains.get(i).get(1);
                buildablebuildings = (ArrayList<Building>) terrains.get(i).get(2);
                terrainpicture = (BufferedImage) terrains.get(i).get(3);
            }
            break;
        }
    }
    
    public String getName() {
        return terrainname;
    }
    
    public BufferedImage getPicture() {
        return terrainpicture;
    }
    
}
