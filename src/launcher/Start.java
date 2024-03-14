package launcher;

import character.Fighter;
import character.Warrior;
import gui.MainWindow;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import map.Map;
import map.Player;
import music.Music;
import building.Building;
import building.Townhall;

import javax.swing.SwingUtilities;

/**
 *
 * Programm startet hier
 * Kann, soll und darf nicht importiert werden
 * 
 */
public class Start {

    // Spielerarray
    public static Player[] players = new Player[2];
    
    // Spiel vorbei
    private static boolean gameend;

    
    public static void main(String[] args) {
        //Reihenfolge wichtig!

        //Init der Spieler + des Spielerarrays
        Player spieler1 = new Player("Spieler1", Color.BLUE);
        Player spieler2 = new Player("Spieler2", Color.RED);
        players[0] = spieler1;
        players[1] = spieler2;
        
        // Erster Spieler fängt an
        spieler1.setAtTurn(true);
        
        Music m = new Music();
        m.playSound();
                
        // neuer Thread, wenn alles geladen ist
        Map map = new Map(50, 50);
        SwingUtilities.invokeLater(() -> new MainWindow(map));
        System.out.println(checkWaters(map,0)[0]+", "+checkWaters(map,0)[1]);
        
        //Test der movement-Methode
        Warrior w = new Warrior("Spieler1",25,25);
        map.getFeld(25, 25).setOccupied(true);
        map.getFeld(25, 25).setOccupiedby("Spieler1");
        players[0].setCharacter(w);
        Warrior v = new Warrior("Spieler2",25,27);
        map.getFeld(25, 27).setOccupiedby("Spieler2");
        map.getFeld(25, 27).setOccupied(true);
        players[1].setCharacter(v);
        Townhall th1 = new Townhall("Spieler1",checkWaters(map,0)[1],checkWaters(map,0)[0]);
        map.getFeld(checkWaters(map,0)[1], checkWaters(map,0)[0]).setOccupied(true);
        map.getFeld(checkWaters(map,0)[1], checkWaters(map,0)[0]).setOccupiedby("Spieler1");
        players[0].setBuilding(th1);
        Townhall th2 = new Townhall("Spieler2",checkWaters(map,1)[1],checkWaters(map,1)[0]);
        map.getFeld(checkWaters(map,1)[1],checkWaters(map,1)[0]).setOccupied(true);
        map.getFeld(checkWaters(map,1)[1],checkWaters(map,1)[0]).setOccupiedby("Spieler2");
        players[1].setBuilding(th2);
        
        w.movementrange(w.getXPosition(), w.getYPosition(), map);
        w.attackrange(w.getXPosition(), w.getYPosition(), map);
        System.out.println(w.getAttackrange().size());
        for (int i = 0; i < w.getAttackrange().size(); i++) {
            System.out.println(w.getAttackrange().get(i)[0]+"  "+w.getAttackrange().get(i)[1]);
        }
        

        
    }
    
    public static Player[] getPlayers(){
        return players;
    }
    
    public static int[] checkWaters(Map Map, int startposition){        // startposition = 0, oben links, startposition = 1, unten links
        int w = Map.getWidth();
        if(startposition==0){
        for (int i = 0; i < w; i++) {
            
            for (int j = 0; j < w; j++) {
                String a = Map.getFeld(j, i).getTerrainName();
                if(!a.equals("water")){
                    int[] koch = {i,j};
                    return koch;
                }
        
            }
        }
        return null;
        }else if(startposition==1){
            for (int i = w-1; i > 0; i--) {
            for (int j = w-1; j > 0; j--) {
                String a = Map.getFeld(j, i).getTerrainName();
                if(!a.equals("water")){
                    int[] koch = {i,j};
                    return koch;
                }
        
            }
        }
        return null;
        }
        return null;
    }
}
