package Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guest-7gls9j
 */
public class Feld {
    // Terrain, Beschaffen des Feldes
    Terrain t;
    
    // Höhe des Feldes, (Flat, Hilly, Mountainous)
    int height;
   
    // Besetzt?   
    boolean occupied;
    
    // möglich da hin zu bewegen, nachdem Figur angeklickt wurde
    boolean highlighted;
    
    // Position
    int xPosition;
    int yPosition;

    public Feld(Terrain t, int height, int xPosition, int yPosition) {
        this.t = t;
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        occupied = false;
    }

    public Terrain getT() {
        return t;
    }
    
    public String getTerrainName() {
        return t.getName();
    }

    public int getHeight() {
        return height;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
    
    public void setT(String TerrainName) {
        this.t = new Terrain(TerrainName);
    }
    
}
