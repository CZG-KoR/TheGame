package Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Character.Builder;
import Character.Character;
import Building.Townhall;
import Building.Building;
import java.util.ArrayList;

/**
 *
 * @author guest-ho4q2h
 */
public class player {
    //Name eines Spielers
    String playername;
    
    //Anzahl der Credits zum Bauen und Erstellen von Charakteren, die pro Runde dazukommen
    int credits;
    
    //Credits, die ein Spieler aufgrund der Gebaeude und Gebiete pro Runde dazubekommt
    int creditsperround;
    
    //Farbe des Spielers fuer Animation
    String colour;
    
    //Holz des Spielers
    int wood=0;
    
    //Stein des Spielers
    int stone=0;
    
    //Nahrung des Spielers
    int food=0;
    
    //Kills eines Spielers (wie viele Truppen er getötet hat)
    int kills=0;
    
    
    //Charaktere und Gebaeude eines Spielers
    ArrayList<Character> Characters= new ArrayList();
    ArrayList<Building> Buildings = new ArrayList();

    public player(String playername, String colour) {
        this.playername = playername;
        this.colour = colour;
        
        //Werte zu Beginn des Spiels muessen noch genauer festgelegt werden
        credits = 5;
        creditsperround = 2;
        
        //Beginn des Spiels mit Rathaus und Builder
        Characters.add(new Builder(this.playername));
        
        System.out.println("Position fuer Rathaus muss noch erstellt werden");
        Buildings.add(new Townhall(0,0));
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
    
    
    

    public int getBuilderAmount(int x, int y){
        // Gibt die Anzahl der Builder an einem Feld an
        int sum=0;
        for (int i = 0; i < Characters.size(); i++) {
            if (Characters.get(i) instanceof Builder) {
                Builder b = (Builder) (Characters.get(i));
                if (b.getxPosition() == x && b.getyPosition() == y) {
                    sum++;
                }
            }
        }
        return sum;
    }

    // Am Ende des Zuges schauen, ob noch alles "lebt" und damit angezeigt werden muss
    public static void checkElements(player p){
        // check ob noch alle Gebäude "leben"
        for (int i = 0; i < p.Characters.size(); i++) {
            if(!p.Characters.get(i).getalive()){
                p.Characters.remove(i);
            }
        }
        
        //check ob noch alle Gebäude "leben"
//        for (int i = 0; i < p.Buildings.size(); i++) {
//            if(!p.Buildings.get(i).getalive()){
//                p.Buildings.remove(i);
//            }
//        }
    }
    
    

    
}
