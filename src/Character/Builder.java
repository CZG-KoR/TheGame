package Character;
import Map.player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guest-7gls9j
 */
public class Builder extends Character{
    // Builder Eigenschaften überdenken, wie soll es funktionieren, Cooldown/Speed/Charges
    int buildcooldown;
    // Der Builder soll an einem Gebäude arbeiten welches neu gebaut wird oder geupgradet wird
    // Jedes Gebäude hat eine bestimmten Build progress(Integer), jeder Builder kann den Build progress jede Runde 
    // um 1 senken, Wenn der Build progress 0 ist, dann ist das Gebäude fertig. Außerdem sollte es möglich sein
    // mit mehreren Buildern an einem Gebäude zu arbeiten um die Bauzeit zu halbieren oder noch weiter zu senken.
    // Die Builder müssen dabei IM Gebäude stehen um an diesem Gebäude zu arbeiten. Das heißt das Character im allgemeinen durch
    // Gebäude laufen können und die Gebäude sind keine Hindernisse, die den Weg versperren. Außerdem können mehrere Builder auf dem gleichen
    // Feld stehen. Das heißt man kann sie "stacken". Das Gebäude verkleinert sein Build progress automatisch am Ende jeder Runde
    // je nachdem wieviele Builder auf der Baustelle sind. Die Builder schaffen schon die Augaben zu verteilen, ansonsten gilt: Wer gar nichts kann, macht Farbe dran.
    public Builder(String playername) {
        super(playername);
    }
    

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
