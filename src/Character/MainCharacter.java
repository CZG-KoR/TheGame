/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Character;
import Map.player;
/**
 *
 * @author guest-o5esoa
 */
public abstract class MainCharacter extends Hero{
    
    public MainCharacter(String playername) {
        super(playername);
        this.info="ApoRed (das heißt Red!) ist der Opa\n" +
"        Hasen machen nicht immer dieselben stepper sie usen auch andere stepper um ihren Horizont zu weiten, denn ein Hase ohne wissen ist nur ein Go.\n" +
"        // Vorbilder zu sneaken kommt immer nice, aber selbst zu seinem eigenen Vorbild zu werden ist das Ziel eines Hasen";
    }
    // ability (Burge-Geld) genereiert Ressourcen
    public void ability(player player){
        //cooldown adden vlt. für alle Heros
        player.setFood(player.getFood()+5);
        player.setStone(player.getStone()+5);
        player.setWood(player.getWood()+5);
    }
}
