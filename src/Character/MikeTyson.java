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
public abstract class MikeTyson extends Hero{

    public MikeTyson(String playername) {
        super(playername);
        info="";
    }
    
    public void ability(player player){
        //erhöht attackrange und attackrating um z.B. 1 // Fighter.attackrating++;
    }
    
    public void ultimate(player player){
        if (player.getKills()>25) {
            //erzeugen von zehn standardfightern
        }
    }
    
}
