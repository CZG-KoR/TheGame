package map;

import building.Building;
import building.Townhall;
import character.Builder;
import character.Character;
import java.awt.Color;
import java.awt.Image;

import java.util.ArrayList;
import launcher.Start;

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
    private Color colour;

    // Holz des Spielers
    private int wood = 0;

    // Stein des Spielers
    private int stone = 0;

    // Nahrung des Spielers
    private int food = 0;

    // Kills eines Spielers (wie viele Truppen er getötet hat)
    private int kills = 0;
    
    // Ist der Spieler am Zug
    private boolean atTurn = false;
    
    // motivation, hat effekt auf kampfwerte etc (vllt bauspeed)
    private double motivation =1;
    
    //Gebiet eines Spielers
    ArrayList<int[]> territory = new ArrayList();


    // Charaktere und Gebaeude eines Spielers
    private ArrayList<Character> characters = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();

    public Player(String playername, Color colour) {
        this.playername = playername;
        this.colour = colour;

        // Werte zu Beginn des Spiels muessen noch genauer festgelegt werden
        credits = 5;
        creditsperround = 2;

        // Beginn des Spiels mit Rathaus und Builder
        //! x: 0, y:0 für kein Error beim Constructor, sollte vermutlich anders gesetzt werden
        characters.add(new Builder(0, 0, this.playername));


        System.out.println("Position fuer Rathaus muss noch erstellt werden");

        buildings.add(new Townhall(playername, 25, 25));
        
    }
    
    public void updateterritory(Map map){
        //Gebiet des Spielers mit neu hinzugefuegtem Building aktualisieren
        for (int i = buildings.get(buildings.size()-1).getxPosition()-buildings.get(buildings.size()-1).getBuildingrange(); i < buildings.get(buildings.size()-1).getxPosition()+buildings.get(buildings.size()-1).getBuildingrange()+1; i++) {
            for (int j = buildings.get(buildings.size()-1).getyPosition()-buildings.get(buildings.size()-1).getBuildingrange(); j < buildings.get(buildings.size()-1).getyPosition()+buildings.get(buildings.size()-1).getBuildingrange()+1; j++) {
                //wenn berechnetes Feld in Map existiert, gehört es zum Territorium
                if(i>=0 && i<map.getWidth() && j>=0 && j<map.getHeight()){
                    if(map.getFeld(i, j).getTerritoryplayer()==null){
                        //Hinzufuegen des Feldes
                        territory.add(new int[]{i,j});
                        map.getFeld(i, j).setTerritoryplayer(playername);
                    }
                }
            }
        }
        
        //Testausgabe des Territoriums eines Spielers
//        for (int i = 0; i < territory.size(); i++) {
//            System.out.println(territory.get(i)[0]+"  "+territory.get(i)[1]);
//        }
    }

    public ArrayList<int[]> getTerritory() {
        return territory;
    }
    
    public Character getCharacter(int x, int y){
        
        for (Character c : characters) {
            
            if (c.getXPosition() == x && c.getYPosition() == y){
                return c;
            }
            
        }
        
        return null;
    }
    
    public Building getBuilding(int x, int y){
        
        for (Building b : buildings) {
            
            if (b.getxPosition() == x && b.getyPosition() == y){
                return b;
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

    public Color getColour() {
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

    public String getPlayername() {
        return playername;
    }

    public double getMotivation() {
        return motivation;
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
            if (!p.characters.get(i).getalive() && !p.characters.get(i).isCurAnimationPlaying()) {
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
    
    
    public boolean isAtTurn() {
        return atTurn;
    }

    public void setAtTurn(boolean atTurn) {
        this.atTurn = atTurn;
    }
    // Bild des Charakters
    public Image getCharacterPicture(int i){
        return characters.get(i).getPicture();
    }
    // Anzahl Charaktere/Gebäude + Rückgabe character an einer Stelle im Array
    public int getCharacterAmount(){
        return characters.size();
    }
    
    public int getBuildingAmount(){
        return buildings.size();
    }
    
    public Character getCharacter(int i){
        return characters.get(i);
    }
    
    public String getFightercharacter(Character charac) {
    if(characters.contains(charac)) {
        return playername;
    }else{
        return null;
    }
}
    
    public void setCharacter(Character c){
        characters.add(c);
    }
    
    public void setBuilding(Building b){
        buildings.add(b);
    }


    public void setCharacterMovementAllowed(int i){
        characters.get(i).setCanmove(true);
    }

    public void setMotivation(double motivation) {
        this.motivation = motivation;
    }
    
    public Building getBuilding(int i){
        return buildings.get(i);
    }
    
    public Image getBuildingPicture(int i){
        return buildings.get(i).getPicture();
    }

    public static Player getAtTurn(){
        for (int i = 0; i < Start.getPlayers().length; i++) {
            if(Start.getPlayers()[i].isAtTurn() == true){
                return Start.getPlayers()[i];
            }
        }
        return null;
    }

}
