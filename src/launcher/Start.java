package launcher;

import building.Tower;
import building.Townhall;
import character.Warrior;
import gui.MainWindow;
import map.Map;
import map.Player;
import music.Music;

import java.awt.Color;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

/**
 *
 * Programm startet hier
 * Kann, soll und darf nicht importiert werden
 * 
 */
public class Start {

    // Spielerarray
    private static final Player[] players = new Player[2];

    private static final String PLAYER_NAME_1 = "Spieler1";
    private static final String PLAYER_NAME_2 = "Spieler2";

    public static void main(String[] args) {
        //Reihenfolge wichtig!

        int width=50;
        int heigth=50;

        
        //Init der Spieler + des Spielerarrays
        Player spieler1 = new Player(PLAYER_NAME_1, Color.BLUE, Color.CYAN);
        Player spieler2 = new Player(PLAYER_NAME_2, Color.RED, Color.MAGENTA);
        players[0] = spieler1;
        players[1] = spieler2;
        
        // Erster Spieler fängt an
        spieler1.setAtTurn(true);
        
        Music m = new Music();
        m.playSound();
                
        // neuer Thread, wenn alles geladen ist
        Map map = new Map(width, heigth);

        
        SwingUtilities.invokeLater(() -> MainWindow.init(map, players));
        Logger.getLogger(Start.class.getName()).log(Level.INFO, () -> checkWaters(map,0)[0] + ", " + checkWaters(map,0)[1]);
        
        
        // Platzieren des Einnehmbaren Gebäudes in der Mitte (win-condition)
        Tower win = new Tower(25, 25);
        
        
        //Test der movement-Methode
        Warrior w = new Warrior(PLAYER_NAME_1,checkWaters(map,2)[1],checkWaters(map,2)[0]);
        Map.getFeld(checkWaters(map,2)[1],checkWaters(map,2)[0]).setOccupied(true);
        Map.getFeld(checkWaters(map,2)[1],checkWaters(map,2)[0]).setOccupiedby(PLAYER_NAME_1);
        players[0].setCharacter(w);
        Warrior v = new Warrior(PLAYER_NAME_2,checkWaters(map,3)[1],checkWaters(map,3)[0]);
        Map.getFeld(checkWaters(map,3)[1],checkWaters(map,3)[0]).setOccupiedby(PLAYER_NAME_2);
        Map.getFeld(checkWaters(map,3)[1],checkWaters(map,3)[0]).setOccupied(true);
        players[1].setCharacter(v);
        Townhall th1 = new Townhall(checkWaters(map,0)[1],checkWaters(map,0)[0]);
        Map.getFeld(checkWaters(map,0)[1], checkWaters(map,0)[0]).setOccupied(true);
        Map.getFeld(checkWaters(map,0)[1], checkWaters(map,0)[0]).setOccupiedby(PLAYER_NAME_1);
        players[0].setBuilding(th1);
        Townhall th2 = new Townhall(checkWaters(map,1)[1],checkWaters(map,1)[0]);
        Map.getFeld(checkWaters(map,1)[1],checkWaters(map,1)[0]).setOccupied(true);
        Map.getFeld(checkWaters(map,1)[1],checkWaters(map,1)[0]).setOccupiedby(PLAYER_NAME_2);
        players[1].setBuilding(th2);
        
        w.movementrange(w.getXPosition(), w.getYPosition(), map);
        w.attackrange(w.getXPosition(), w.getYPosition(), map);
        Logger.getLogger(Start.class.getName()).log(Level.INFO, () -> String.valueOf(w.getAttackrange().size()));

        for (int i = 0; i < w.getAttackrange().size(); i++) {
            String msg = w.getAttackrange().get(i)[0] + "  " + w.getAttackrange().get(i)[1];
            Logger.getLogger(Start.class.getName()).log(Level.INFO, msg);
        }
    }
    
    public static Player[] getPlayers(){
        return players;
    }
    
    public static int[] checkWaters(Map map, int startposition){        // startposition = 0, oben links, startposition = 1, unten links, 2 oder 3 = startposition warrior neben townhall
        int w = map.getWidth();
        final String WATER = "water";

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < w; j++) {
                int xCoord = startposition % 2 == 0 ? j : 49 - j;
                int yCoord = startposition % 2 == 0 ? i : 49 - i;
                String a = Map.getFeld(xCoord, yCoord).getTerrainName();
                if (!a.equals(WATER)) {
                    int[] koch = {yCoord, xCoord};

                    if (startposition < 2)
                        return koch;
                    
                    if (!Arrays.equals(koch, checkWaters(map, startposition - 2)))
                        return koch;
                }
            }
        }
        return new int[0];
    }
}