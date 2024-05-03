package building;

import map.Player;
import character.Archer;
import character.Catapult;
import character.Horsemen;
import character.Warrior;
import tools.MiscUtils;

public class Barracks extends Building {
    public Barracks(int xPosition, int yPosition) {
        super();
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.buildtime=2;
        this.healthpoints=2;
        this.buildingrange=2;
        picture = MiscUtils.loadImages("src/gui/res/building")[3];
    }
    
    public static boolean buildable(Player player) {
        // Wood und Stone vom player
        int wood = player.getWood();
        int stone = player.getStone();

        // Kosten des Bauens: 1 wood, 1 Stone
        // genug ressourcen, deswegen buildable true
        // bei false, soll das Gebäude nicht gebaut werden
        return wood >= 1 && stone >= 1;
    }
    
    public Archer generateArcher(Player player, int x, int y) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            return new Archer(player.getPlayername(), x ,y);
        } else {
            //Fehlermeldung
            return null;
        }
    }
    
    public Catapult generateCatapult(Player player, int x, int y) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            return new Catapult(player.getPlayername(), x ,y);
        } else {
            //Fehlermeldung
            return null;
        }
    }
    
    public Horsemen generateHorsemen(Player player, int x, int y) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            return new Horsemen(player.getPlayername(), x ,y);
        } else {
            //Fehlermeldung
            return null;
        }
    }
    
    public Warrior generateWarrior(Player player, int x, int y) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            return new Warrior(player.getPlayername(), x ,y);
        } else {
            //Fehlermeldung
            return null;
        }
    }
    
    public void generateFigther2(Player player) {
        // ApoRed (das heißt Red!) ist der Opa
        // Hasen machen nicht immer dieselben stepper sie usen auch andere stepper um ihren Horizont zu weiten, denn ein Hase ohne wissen ist nur ein Go.
        // Vorbilder zu sneaken kommt immer nice, aber selbst zu seinem eigenen Vorbild zu werden ist das Ziel eines Hasen
        
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 2 && stone >= 2 && food >= 4) {
            // Kosten des Fighter: 2 wood, 2 Stone, 4 Food
            player.setWood(wood - 2);
            player.setStone(stone - 2);
            player.setStone(food - 4);
        } else {
            //Fehlermeldung
        }
    }

    @Override
    public void buildableterrains() {
        this.getBuildableterrains().add("water");
    }
}