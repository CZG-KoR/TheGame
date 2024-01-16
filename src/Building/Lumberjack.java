/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Building;

/**
 *
 * @author guest-a9khel
 */
public class Lumberjack extends Building {
    //Abbaugeschwindigkeit des Holzes in holz pro runde
    double chopspeed;

    public Lumberjack(int xPosition, int yPosition) {
    this.xPosition=xPosition;
    this.yPosition=yPosition;
    this.buildtime=3;
    this.healthpoints=3;
    this.buildingrange=4;
    this.buildcost[0]=5;
    this.chopspeed = 1.0;
    }
 
    //anfang jeder runde Holz faellen
  public void chopchop() {
      //player.wood = player.wood + chopspeed;
  }
}
