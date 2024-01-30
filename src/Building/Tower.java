package building;

import map.Player;

public class Tower extends Building {
    //Upgrade level des Towers
    private int level;
    //maxlevel erhöht sich mit Rathauslevel
    
    //automatisches angreifen mit bestimmten schaden
    private int attackingstrength;

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
    
    public boolean buildable(Player player) {
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

    //! player wird nicht in der Methode verwendet
    public void upgrade(Player player, Townhall townhall) {
        int maxlevel;
        // maxlevel soll progress des townhalls mal 1 sein
        maxlevel = townhall.progress;
        if (this.level < maxlevel) {
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
