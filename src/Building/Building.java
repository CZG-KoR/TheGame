package Building;
import Map.player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guest-7gls9j
 */
public abstract class Building {
    // Dauer wie Lange gebaut wird
    int buildtime;
    
    // Funktionsweise der Gebäude überdenken
    int healthpoints;
    
    // Reichweitenvergrößerung des Landes
    int buildingrange;
    
    //Credits fuer Gebaeude zum Bauen und spaeter pro Runde
    //int buildcredits;
    //int creditsperround;
    

    int[] buildcost = new int[2];

    
    // Position
    int xPosition;
    int yPosition;

    
    
}
