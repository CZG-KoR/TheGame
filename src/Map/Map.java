package Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

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

    static ArrayList<ArrayList<Feld>> Felder;

    //Dimensionen des Feldes
    static int height;
    static int width;

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
        
        this.setT(3, 3, "water");
        
        Random r = new Random();
        
        
        Noise n = new Noise(r, (float) 2, width, height);
        n.initialise();
        for(int x = 0; x<width; x++){
            for(int y = 0; y < height; y++){
                if(n.getNoiseAt(x, y) > (float) 0.3) this.setT(x, y, "water");
            }
        }
    }
    
    public int getSorroundingTerrain(int x, int y, String name){
        int counter = 0;
        if(x != 0 && this.getTerrainName(x-1, y).equals(name)) counter++;
        if(y != 0 && this.getTerrainName(x, y-1).equals(name)) counter++;
        if(x != 0 && y != 0 && this.getTerrainName(x-1, y-1).equals(name)) counter++;
        if(x < width-1 && this.getTerrainName(x+1, y).equals(name)) counter++;
        if(y < height-1 && this.getTerrainName(x, y+1).equals(name)) counter++;
        if(x < width-1 && y != 0 && this.getTerrainName(x+1, y-1).equals(name)) counter++;
        if(y < height-1 && x != 0 && this.getTerrainName(x-1, y+1).equals(name)) counter++;
        if(y < height-1 && x < width-1 && this.getTerrainName(x+1, y+1).equals(name)) counter++;
        
        return counter;
    }
    
    public void addSource(int a, int xsource, int ysource) {
        for(int x = 0; x<xsource; x++){
            for(int y = 0; x<ysource; y++) {
                if(a+xsource >= getWidth() || a+ysource >= getHeight()) continue;
                setT(xsource, ysource, "water");
            }
        }
    }
    
    public void setT(int xcoord, int ycoord, String TerrainName){
        getFeld(xcoord, ycoord).setT(TerrainName);
    }
    
    public Image getTerrainPicture(int xcoord, int ycoord){
        return getFeld(xcoord, ycoord).getT().getPicture();
    }
}
