/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author guest-mlyj9e
 */
public class Wheatfield extends Building{
    //Feld gibt Motivationsboost durch "Nahrung"
    double motivationboost;
    //Abklingzeit für harvest, vlt. Änderung im Erscheinungsbild wenn der Weizen wächst
    int cooldown;
    //maximale cooldown-Zeit
    private int maxcooldowntime=4;
    
    public Wheatfield(int xPosition, int yPosition, int TeamId) {
    this.xPosition=xPosition;
    this.yPosition=yPosition;
    this.buildtime=2;
    this.healthpoints=2;
    this.buildingrange=2;
    this.motivationboost=1.0;
    this.info="Ein endloses Weizenfeld erstreckt sich vor dir, goldene Ähren wiegen sich im Wind. Zwischen den Reihen verlaufen Pfade, auf denen Bauern die reiche Ernte einbringen. Die Arbeit hier ist nicht nur körperlich, sondern stärkt auch den Zusammenhalt. Die erfolgreiche Ernte gibt den Truppen einen spürbaren Motivationsboost, da sie nicht nur Nahrung, sondern auch ein Gefühl von Gemeinschaft und Kampfgeist gewinnen.";
    this.TeamId=TeamId;
    }
    
    public void harvest(){
        //nur möglich, wenn der cooldown abgelaufen ist - Erntezeit
        if (cooldown==0) {
            //motivationsboost
            this.motivationboost=this.motivationboost*1.01;
            this.cooldown=maxcooldowntime;
        }
        else{
            //Ausgabe, dass der Cooldown noch nicht abgelaufen ist.
        }
} 
    
}
