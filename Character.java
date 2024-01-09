/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author guest-7gls9j
 */
public abstract class Character {
    // Anzahl an Leben für ein Character bis dieser "entfernt" wird
    int healthpoints;
    // Bewegungsreichweite
    int movement;
    // Wurde die Figur schon bewegt?
    boolean canmove;
    // Motivation
    int motivation;
    
    
    // Position
    int xPosition;
    int yPosition;
    
    
    
    
    
    
    // Bewegung muss für die einzelnen Charaktere definiert werden
    public abstract void move();
}
