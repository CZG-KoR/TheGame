package Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Ambrosius, Jannik
 */
public class Map {
    /*
    * 2D-Arraylist
    * beginnt bei x = 0 und y = 0, links oben
    * geht bis x = width-1, und bis y = height-1
    * Jede Arraylist<Feld> steht für eine Spalte (gleiche x-Koordinate)
    * */

    ArrayList<ArrayList<Feld>> Felder;

    //Dimensionen des Feldes
    int height;
    int width;

    public Map(int heigth, int width) {
        this.height = heigth;
        this.width = width;

        generateMap();
    }

    //GET-Function
    public Feld getFeld(int xcoord, int ycoord){
        Feld feld = this.Felder.get(xcoord).get(ycoord);
        return feld;
    }

    public String getTerrainName(int xcoord, int ycoord){
        return getFeld(xcoord, ycoord).getTerrainName();
    }
    
    public int getHeight(){
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
    
    public void generateMap(){
        this.Felder = new ArrayList<ArrayList<Feld>>();
        Terrain t = new Terrain("grass");
        
        
        //Generieren einer neuen mit Grass gefüllten Map / Grundlage der Map Erstellung
        for(int i = 0; i<this.height; i++){
            ArrayList<Feld> Zeile = new ArrayList<Feld>();
            
            //x-Koordinate
            for(int j = 0; j<this.width; j++){
                Feld f = new Feld(t, 0, i, j);
                Zeile.add(f);
            }
            
            //fügt neue Zeile hinzu
            this.Felder.add(Zeile);
        }
    }
    
    public void setT(int xcoord, int ycoord, String TerrainName){
        getFeld(xcoord, ycoord).setT(TerrainName);
    }
    
    public Image getTerrainPicture(int xcoord, int ycoord){
        return getFeld(xcoord, ycoord).getT().getPicture();
    }
}
