/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author guest-7gls9j
 */
public abstract class Building {
    // Dauer wie Lange gebaut wird
    int buildtime;
    
    //gibt eine kurze Info über die Eigenschaften des Gebäudes
    String info;
    
    // Funktionsweise der Gebäude überdenken
    int healthpoints;
    
    // Reichweitenvergrößerung des Landes
    int buildingrange;
    
    // Position
    int xPosition;
    int yPosition;
    
    //Id, die angibt zu welchem Team das Gebäude gehört
    //ist nicht schlimm wenn man das später nicht nutzt
    int TeamId;
}
