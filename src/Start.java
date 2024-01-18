
import GUI.ImageLoader;
import GUI.MainWindow;
import Map.Map;
import javax.swing.SwingUtilities;

/**
 *
 * Programm startet hier Kann, soll und darf nicht importiert werden
 */
public class Start {

    public static void main(String[] args) {
        //Reihenfolge wichtig!
        new ImageLoader();
        
        // neuer Thread, wenn alles geladen ist
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow(new Map(50, 50));
            }
        });
    }

}
