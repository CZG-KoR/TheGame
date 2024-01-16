/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Building;

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

    public Tower(int xPosition, int yPosition, int TeamId) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 4;
        this.healthpoints = 5;
        this.buildingrange = 10;
        //  this.info="große Range";
        //  this.TeamId=TeamId;
        this.level = 1;
        this.attackingstrength = 1;
        //soll progress des townhalls mal 1 sein oder so
        this.maxlevel = 1;
    }

    public void Upgrade() {
        if (this.level < this.maxlevel) {
            if (this.level == 1) {
                level++;
                this.attackingstrength++;
                this.healthpoints = this.healthpoints + 2;
                this.buildingrange = this.buildingrange + 3;
                //Bauzeit adden
            }
            if (this.level == 2) {
                level++;
                this.attackingstrength = this.attackingstrength + 2;
                this.healthpoints = this.healthpoints + 4;
                this.buildingrange = this.buildingrange + 6;
                //Bauzeit adden
            }
        } else {
            //Fehler Rathaus nicht hoch genug
        }
    }
}
