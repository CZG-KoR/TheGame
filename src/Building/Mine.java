/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Building;

/**
 *
 * @author guest-ayeskk
 */
public class Mine extends Building{
    
    public Mine(int xPosition, int yPosition) {
    this.xPosition=xPosition;
    this.yPosition=yPosition;
    this.buildtime=2;
    this.healthpoints=2;
    this.buildingrange=2;
    }
    
    public void mine(){
        //resources+=10;
        // in der Nähe von Bergen o.ä. mehr ressourcen bekommen
} 
}
