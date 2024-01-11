/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author guest-23sps0
 */
public class Townhall extends Building{
    
    // durch das Verbessern deines Rathauses schaltest du neue Gebäude (z.B. Verteidigungen, Fallen) und vieles mehr frei.
    // progress gibt den "technischen Fortschritt" an
    int progress = 0;
    
    String info = "Das hier ist das Herz deines Dorfs. Das Verbessern schaltet neue Gebäude frei."
            + " Man sollte das Rathaus mit Verteidigungsgebäuden umgeben, denn der Gegner kann dein Rathaus einnehmen!";
    
    public Townhall(int xPosition, int yPosition) {
        buildtime = 0;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        buildingrange = 2;
        healthpoints = 100;
    }

    public void Upgrade1() {
        buildtime = 2;
        healthpoints = 200;
        progress+=1;
        //Ressourcen-=10;
    }
    
    public void Upgrade2() {
        buildtime = 2;
        healthpoints = 400;
        progress+=1;
        //Ressourcen-=10;
    }
    
    public void GenerateFigther() {
        //new Fighter();
        //Ressourcen-=10;
    }
    
    public void GenerateBuilder() {
        //new Builder();
        //Ressourcen-=10;
    }
    
    public void GenerateFigther2() {
        if (progress>=2) {
            //new Aller echte Maincharacter Figther();
            //Ressourcen-=10;
        }else{
            // Fehlermeldung, "noch nicht freigeschaltet"
        }
    }
}
