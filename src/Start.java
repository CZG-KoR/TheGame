
import GUI.ImageLoader;
import GUI.MainWindow;
import Map.Map;
import Map.player;
import javax.swing.SwingUtilities;

/**
 *
 * Programm startet hier Kann, soll und darf nicht importiert werden
 */
public class Start {

    // Spielerarray
    public static player[] players = new player[2];
    
    public static void main(String[] args) {
        //Reihenfolge wichtig!
        new ImageLoader();
        //Init der Spieler + des Spielerarrays
        player Spieler1 = new player("Spieler1", "blue");
        player Spieler2 = new player("Spieler2", "blue");
        players[0] = Spieler1;
        players[1] = Spieler2;
        
        // jeden Zug das machen; schauen, ob etwas nicht mehr existiert
        for (int i = 0; i < players.length; i++) {
            player.checkElements(players[i]);
        }
        
        
        // neuer Thread, wenn alles geladen ist
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow(new Map(50, 50));
            }
        });
    }

}
