/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Building;

import Map.player;

/**
 *
 * @author guest-a9khel
 */
public class Fishinghouse extends Building{
    //wie lumberjack
    double fishingspeed;
    
    public Fishinghouse(int xPosition, int yPosition) {
    this.xPosition=xPosition;
    this.yPosition=yPosition;
    this.buildtime=4;
    this.healthpoints=2;
    this.buildingrange=3;
    this.buildcost[0]=5;
    this.fishingspeed = 2;
    }
    
    public boolean buildable(player player) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();

        if (wood >= 1 && stone >= 1) {
            // Kosten des Bauens: 1 wood, 1 Stone
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            // genug ressourcen, deswegen buildable true
            return true;
        }
        // bei false, soll das Gebäude nicht gebaut werden
        return false;
    }
    
    //Anfang jede Runde fischen
    public void fish() {
        //player.food = player.food + fishingspeed;
    }
}
