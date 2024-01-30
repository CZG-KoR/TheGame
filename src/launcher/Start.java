package launcher;

import gui.MainWindow;
import map.Map;
import map.Player;

import javax.swing.SwingUtilities;

/**
 *
 * Programm startet hier Kann, soll und darf nicht importiert werden
 *
 */
public class Start {

    // Spielerarray
    private static Player[] players = new Player[2];
    // Spiel vorbei
    private static boolean gameend;
    // Spieler am Zug ( 0 == Spieler1, 1 == Spieler2)
    private static String turn = "Spieler1";

    public static void main(String[] args) {
        //Reihenfolge wichtig!

        //Init der Spieler + des Spielerarrays
        Player spieler1 = new Player("Spieler1", "blue");
        Player spieler2 = new Player("Spieler2", "blue");
        players[0] = spieler1;
        players[1] = spieler2;

       // Runden Button Turn End noch init
       // Das in ActionPerformed vom ButtonTurnEnd übernehmen
//            if (ButtonTurnEndKlicked) {
//                if (turn.equals("Spieler1")) {
//                    Thread.sleep(100); 
//                    turn = "Spieler2";
//                    // jeden Zug das machen; schauen, ob etwas nicht mehr existiert
//                    for (int i = 0; i < players.length; i++) {
//                        Player.checkElements(players[i]);
//                    }
//                } else if (turn.equals("Spieler2")) {
//                    Thread.sleep(100); 
//                    turn = "Spieler1";
//                    // jeden Zug das machen; schauen, ob etwas nicht mehr existiert
//                    for (int i = 0; i < players.length; i++) {
//                        Player.checkElements(players[i]);
//                    }
//                }
//            }
        

        // neuer Thread, wenn alles geladen ist
        SwingUtilities.invokeLater(() -> new MainWindow(new Map(50, 50)));
    }

}
