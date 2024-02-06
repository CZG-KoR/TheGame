package launcher;

import gui.MainWindow;
import java.awt.Color;
import map.Map;
import map.Player;
import music.Music;

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
        Player spieler1 = new Player("Spieler1", Color.cyan);
        Player spieler2 = new Player("Spieler2", Color.RED);
        players[0] = spieler1;
        players[1] = spieler2;
        
        // Erster Spieler fängt an
        spieler1.setAtTurn(true);
        
        Music m = new Music();
        m.playSound();
                
        // neuer Thread, wenn alles geladen ist
        SwingUtilities.invokeLater(() -> new MainWindow(new Map(50, 50)));
    }
    
    public static Player[] getPlayers(){
        return players;
    }
}
