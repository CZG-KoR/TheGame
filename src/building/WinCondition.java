/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package building;

import java.awt.Image;
import map.Map;
import map.Player;
import tools.MiscUtils;

/**
 *
 * @author guest-qhhgb1
 */
public class WinCondition extends Building{
        //Upgrade level des Towers
    private int level;
    //maxlevel erhöht sich mit Rathauslevel
    
    //automatisches angreifen mit bestimmten schaden
    private int attackingstrength;
    
    static Image picture1 = MiscUtils.loadImages("src/gui/res/building")[5];
    
    private static int einnehmen = 5;
    
    private static String EinnehmenderPlayer;

    public WinCondition(String playername, int xPosition, int yPosition) {
        super(playername); 
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        picture = MiscUtils.loadImages("src/gui/res/building")[5];
    }
   
    public static Image getPicture1(){
        return picture1;
    }

    public static int getEinnehmen() {
        return einnehmen;
    }

    public static void setEinnehmen(int einnehmen1) {
        einnehmen = einnehmen1;
    }
    
    public static void EinnehmenStart(String playername){
        EinnehmenderPlayer = playername;
    }
    
    public static void EinnehmenProzess(){
        if(Map.getFeld(25, 25).getOccupiedby().equals(EinnehmenderPlayer)){
            einnehmen--;
            if(einnehmen == 0){
                System.exit(0);
            }
        }
        else{
            einnehmen = 5;
            if(Map.getFeld(25, 25).getOccupiedby() != null){
                EinnehmenStart(Map.getFeld(25, 25).getOccupiedby());
            }
        }
    }
    
    
}
