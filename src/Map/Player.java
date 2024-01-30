package Map;

import Building.Building;
import Building.Townhall;
import Character.Builder;
import Character.Character;

import java.util.ArrayList;

public class Player {
    // Name eines Spielers
    private String playername;

    // Anzahl der Credits zum Bauen und Erstellen von Charakteren, die pro Runde
    // dazukommen
    private int credits;

    // Credits, die ein Spieler aufgrund der Gebaeude und Gebiete pro Runde
    // dazubekommt
    private int creditsperround;

    // Farbe des Spielers fuer Animation
    private String colour;

    // Holz des Spielers
    private int wood = 0;

    // Stein des Spielers
    private int stone = 0;

    // Nahrung des Spielers
    private int food = 0;

    // Kills eines Spielers (wie viele Truppen er getötet hat)
    private int kills = 0;

    // Charaktere und Gebaeude eines Spielers
    private ArrayList<Character> characters = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();

    public Player(String playername, String colour) {
        this.playername = playername;
        this.colour = colour;

        // Werte zu Beginn des Spiels muessen noch genauer festgelegt werden
        credits = 5;
        creditsperround = 2;

        // Beginn des Spiels mit Rathaus und Builder
        //! x: 0, y:0 für kein Error beim Constructor, sollte vermutlich anders gesetzt werden
        characters.add(new Builder(0, 0, this.playername));

        System.out.println("Position fuer Rathaus muss noch erstellt werden");
        buildings.add(new Townhall(0, 0));
    }
    
    public Character getCharacter(int x, int y){
        
        for (Character c : characters) {
            
            if (c.getXPosition() == x && c.getYPosition() == y){
                return c;
            }
            
        }
        
        return null;
    }



    public int getCredits() {
        return credits;
    }

    public int getCreditsPerRound() {
        return creditsperround;
    }

    public String getColour() {
        return colour;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getFood() {
        return food;
    }

    public int getKills() {
        return kills;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getBuilderAmount(int x, int y) {
        // Gibt die Anzahl der Builder an einem Feld an
        int sum = 0;
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i) instanceof Builder) {
                Builder b = (Builder) (characters.get(i));
                if (b.getXPosition() == x && b.getYPosition() == y) {
                    sum++;
                }
            }
        }
        return sum;
    }

    // Am Ende des Zuges schauen, ob noch alles "lebt" und damit angezeigt werden
    // muss
    public static void checkElements(Player p) {
        // check ob noch alle Gebäude "leben"
        for (int i = 0; i < p.characters.size(); i++) {
            if (!p.characters.get(i).getalive()) {
                p.characters.remove(i);
            }
        }

        // check ob noch alle Gebäude "leben"
        // for (int i = 0; i < p.Buildings.size(); i++) {
        // if(!p.Buildings.get(i).getalive()){
        // p.Buildings.remove(i);
        // }
        // }
    }

}
