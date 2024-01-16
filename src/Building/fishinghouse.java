/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Building;

/**
 *
 * @author guest-a9khel
 */
public class fishinghouse extends Building{
    //wie lumberjack
    double fishingspeed;
    
    public fishinghouse(int xPosition, int yPosition) {
    this.xPosition=xPosition;
    this.yPosition=yPosition;
    this.buildtime=4;
    this.healthpoints=2;
    this.buildingrange=3;
    this.buildcosts[0]=5;
    this.fishingspeed = 2;
    }
    
    //Anfang jede Runde fischen
    public void fish() {
        //player.food = player.food + fishingspeed;
    }
}
