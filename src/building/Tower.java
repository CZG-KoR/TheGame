package building;

import java.awt.Image;
import map.Player;
import tools.MiscUtils;

public class Tower extends Building {
    //Upgrade level des Towers
    private int level;
    //maxlevel erhöht sich mit Rathauslevel
    
    //automatisches angreifen mit bestimmten schaden
    private int attackingstrength;
    
    static Image picture1 = MiscUtils.loadImages("src/gui/res/building")[5];
    
    private int einnehmen = 5;

    public Tower(String playername, int xPosition, int yPosition) {
        super(playername); 
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.buildtime = 4;
        this.healthpoints = 5;
        this.buildingrange = 10;
        //  this.info="große Range";
        this.level = 0;
        this.attackingstrength = 1;
        picture = MiscUtils.loadImages("src/gui/res/building")[5];
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
    
    public static Image getPicture1(){
        return picture1;
    }

    public int getEinnehmen() {
        return einnehmen;
    }

    public void setEinnehmen(int einnehmen) {
        this.einnehmen = einnehmen;
    }
    
    public void EinnehmenStart(Player player){
        
    }
    
    public void EinnehmenProzess(int einnehmen, Player player){
        
    }
    
    
}
