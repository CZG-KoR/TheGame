/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

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

        Terrain t = new Terrain("grass");
        this.Felder = new ArrayList<ArrayList<Feld>>();
        for(int i = 0; i<heigth; i++){
            ArrayList<Feld> Zeile = new ArrayList<Feld>();
            for(int j = 0; j<width; j++){
                Feld f = new Feld(t, 0, i, j);
                Zeile.add(f);
            }
            this.Felder.add(Zeile);
        }
    }

    //GET-Function
    public Feld get(int xcoord, int ycoord){
        Feld feld = this.Felder.get(xcoord).get(ycoord);
        return feld;
    }

    public String getTerrainName(int xcoord, int ycoord){
        return get(xcoord, ycoord).getTerrainName();
    }
}
