/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Character;

/**
 *
 * @author guest-o5esoa
 */
public abstract class Hero extends Character{

    String info;
   
    //int cooldown; ????
    
    public Hero(String playername) {
        super(playername);
        this.info=info;
    }
    
    //jeder Held hat eine Fähigkeit
    public abstract void ability();
    
}
