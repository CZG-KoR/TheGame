/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Building;
import Map.player;
import Building.Townhall;
/**
 *
 * @author guest-djdvz9
 */
public class Tower extends Building {
    //Upgrade level des Towers
    int level;
    //maxlevel erhöht sich mit Rathauslevel
    int maxlevel;
    //automatisches angreifen mit bestimmten schaden
    int attackingstrength;

    public Tower(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 4;
        this.healthpoints = 5;
        this.buildingrange = 10;
        //  this.info="große Range";
        this.level = 0;
        this.attackingstrength = 1;
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

    public void Upgrade(player player, Townhall Townhall) {
        // maxlevel soll progress des townhalls mal 1 sein
        maxlevel = Townhall.progress;
        if (this.level < this.maxlevel) {
            if (this.level == 0) {
                level++;
                this.attackingstrength++;
                this.healthpoints = this.healthpoints + 2;
                this.buildingrange = this.buildingrange + 2;
                buildtime = 1;
            }
            if (this.level == 1) {
                level++;
                this.attackingstrength = this.attackingstrength + 2;
                this.healthpoints = this.healthpoints + 4;
                this.buildingrange = this.buildingrange + 2;
                buildtime = 1;
            }
        } else {
            //Fehler Rathaus nicht hoch genug
        }
    }
}
