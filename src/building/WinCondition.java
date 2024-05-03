package building;

import map.Map;
import tools.MiscUtils;

import java.awt.Image;

public class WinCondition extends Building {
    
    // Variable für Upgrade Level des Towers - zB level
    // maxlevel erhöht sich mit Rathauslevel
    
    // Variable für automatisches Angreifen mit bestimmten Schaden - zB attackingStrength
    
    static Image picture1 = MiscUtils.loadImages("src/gui/res/building")[5];
    
    private static int einnehmen = 5;
    
    private static String einnehmenderPlayer;

    public WinCondition(int xPosition, int yPosition) {
        super(); 
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
    
    public static void einnehmenStart(String playername){
        einnehmenderPlayer = playername;
    }
    
    public static void einnehmenProzess(){
        if(Map.getFeld(25, 25).getOccupiedby().equals(einnehmenderPlayer)){
            einnehmen--;
            if(einnehmen == 0){
                System.exit(0);
            }
        }
        else{
            einnehmen = 5;
            if(Map.getFeld(25, 25).getOccupiedby() != null){
                einnehmenStart(Map.getFeld(25, 25).getOccupiedby());
            }
        }
    }

    @Override
    public void buildableterrains() {
        buildableterrains.add("water");
        //! keine Ahnung ob noch andere Terrains hier hin sollten
    }
}