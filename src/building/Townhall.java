package building;

import java.awt.Image;
import map.Player;
import tools.MiscUtils;

public class Townhall extends Building {

    // durch das Verbessern deines Rathauses schaltest du neue Gebäude (z.B.
    // Verteidigungen, Fallen) und vieles mehr frei.
    // progress gibt den "technischen Fortschritt" an
    int progress = 0;
    // maximales Level/Progress des Townhalls
    private int maxlevel = 2;
    
    
    String info = "Das hier ist das Herz deines Dorfs. Das Verbessern schaltet neue Gebäude frei."
            + " Man sollte das Rathaus mit Verteidigungsgebäuden umgeben, denn der Gegner kann dein Rathaus einnehmen!";

    public Townhall(String playername, int xPosition, int yPosition) {
        super(playername); 
        buildtime = 0;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        buildingrange = 2;
        healthpoints = 100;     
        picture = MiscUtils.loadImages("src/gui/res/building")[3];

    }

    public void upgrade(Player player) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();
        progress += 1;
        // Je nachdem welchen Progress der Townhall schon hat, größere Preise für
        // weitere Verbesserung
        if (this.progress < this.maxlevel) {
            //// Verbesserung 1
            if (progress == 1) {
                if (wood >= 1 && stone >= 1) {
                    // Kosten des Upgrades: 1 wood, 1 Stone
                    player.setWood(wood - 1);
                    player.setStone(stone - 1);
                    // 2 Runden Bauzeit des Upgrades, mehr Lebenspunkte durch Verbesserung
                    buildtime = 2;
                    healthpoints = 200;
                } else {
                    progress -= 1;
                    // Fehlermeldung, unzureichende Ressourcen für das Upgrade
                }
                //// Verbesserung 2
            } else if (progress == 2) {
                if (wood >= 2 && stone >= 2) {
                    // Kosten des Upgrades: 2 wood, 2 Stone
                    player.setWood(wood - 2);
                    player.setStone(stone - 2);
                    // 2 Runden Bauzeit des Upgrades, mehr Lebenspunkte durch Verbesserung
                    buildtime = 2;
                    healthpoints = 200;
                } else {
                    progress -= 1;
                    // Fehlermeldung, unzureichende Ressourcen für das Upgrade
                }
            }
        }
    }

    public void generateFigther() {
        // new Fighter();
        // Ressourcen-=10;
    }

    public void generateBuilder() {
        // new Builder();
        // Ressourcen-=10;
    }

    public void generateFigther2() {
        if (progress >= 2) {
            // new Aller echte Maincharacter Figther();
            // Ressourcen-=10;
        } else {
            // Fehlermeldung, "noch nicht freigeschaltet"
        }
    }
}
