/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package building;

import map.Player;
import character.Archer;
import character.Catapult;
import character.Horsemen;
import character.Warrior;
import tools.MiscUtils;
/**
 *
 * @author guest-ayeskk
 */
public class Barracks extends Building{
    public Barracks(String playername, int xPosition, int yPosition) {
    super(playername);
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

        if (wood >= 2 && stone >= 2) {
            // Kosten des Bauens: 1 wood, 1 Stone
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            // genug ressourcen, deswegen buildable true
            return true;
        }
        // bei false, soll das Gebäude nicht gebaut werden
        return false;
    }
    
    public void GenerateArcher(Player player, int x, int y) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            Archer archer = new Archer(player.getPlayername(), x ,y);
        } else {
            //Fehlermeldung
        }
    }
    
    public void GenerateCatapult(Player player, int x, int y) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            Catapult catapult = new Catapult(player.getPlayername(), x ,y);
        } else {
            //Fehlermeldung
        }
    }
    
    public void GenerateHorsemen(Player player, int x, int y) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            Horsemen horsemen = new Horsemen(player.getPlayername(), x ,y);
        } else {
            //Fehlermeldung
        }
    }
    
    public void GenerateWarrior(Player player, int x, int y) {
        // Wood und Stone und Food vom player
        int wood = player.getWood();
        int stone = player.getStone();
        int food = player.getFood();
        
        if (wood >= 1 && stone >= 1 && food >= 2) {
            // Kosten des Fighter: 1 wood, 1 Stone, 2 Food
            player.setWood(wood - 1);
            player.setStone(stone - 1);
            player.setStone(food - 2);
            Warrior warrior = new Warrior(player.getPlayername(), x ,y);
        } else {
            //Fehlermeldung
        }
    }
    
    public void GenerateFigther2(Player player) {
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
            //new Fighter2();
        } else {
            //Fehlermeldung
        }
    }
}