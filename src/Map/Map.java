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
                
        Random r = new Random();
        
        Noise n = new Noise(r, (float) 10, width, height);
        n.initialise();
        for(int x = 0; x<width; x++){
            for(int y = 0; y < height; y++){
                if(n.getNoiseAt(x, y) < (float) 0.8) this.setT(x, y, "desert");
                if(n.getNoiseAt(x, y) < (float) 0.48) this.setT(x, y, "water");
                if(n.getNoiseAt(x, y) > (float) 3.5) this.setT(x, y, "forest");
                
            }
        }
        
        //water-pruning
        /*
        löscht Wasserfelder, wenn limit Anzahl an anderen darum ist 
        Sodass kleine "Pfützen" wegfallen
        */
        waterpruning(2);
        
    }
    
    public void waterpruning(int limit){
        ArrayList<ArrayList<Integer>> tempList = new ArrayList<ArrayList<Integer>>();
        for(int x = 0; x<width; x++){
            for(int y = 0; y < height; y++){
                ArrayList<Integer> localList = new ArrayList<Integer>();
                if(this.getTerrainName(x, y).equals("water") && getSorroundingTerrain(x, y, "grass")+getSorroundingTerrain(x, y, "desert") > limit){
                    localList.add(x);
                    localList.add(y);
                    tempList.add(localList);
                }
            }
        }
        this.replace(tempList, "grass");
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
    
    public void replace(ArrayList<ArrayList<Integer>> coordinates,String TerrainNametoPlace){
        for(ArrayList<Integer> v : coordinates){
            this.setT(v.get(0), v.get(1), TerrainNametoPlace);
        }
    }
    
    public void setT(int xcoord, int ycoord, String TerrainName){
        getFeld(xcoord, ycoord).setT(TerrainName);
    }
    
    public Image getTerrainPicture(int xcoord, int ycoord){
        return getFeld(xcoord, ycoord).getT().getPicture();
    }
}
