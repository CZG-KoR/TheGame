/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

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
    
    
    
}
