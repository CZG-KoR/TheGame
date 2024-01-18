/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Building;
import Map.player;

/**
 *
 * @author guest-ayeskk
 */
public class Mine extends Building{
    
    public Mine(int xPosition, int yPosition) {
    this.xPosition=xPosition;
    this.yPosition=yPosition;
    this.buildtime=2;
    this.healthpoints=2;
    this.buildingrange=2;
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
    
    public void mine(player player){
        // stone um 1 erhöhen
        player.setStone(player.getStone()+1);
        // in der Nähe von Bergen o.ä. mehr ressourcen bekommen
    } 
}
