package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.Image;
import java.awt.Toolkit;

//ImageLoader jetzt in GUI, weil auch Character gezeichnet werden müssen
public class ImageLoader {

    public static Image[] imageA = new Image[4];

    public ImageLoader() {
        for (int i = 0; i < imageA.length; i++) {
            imageA[i] = switch (i) {
                case 0 ->
                    Toolkit.getDefaultToolkit().getImage("src/GUI/res/grass.png");
                case 1 ->
                    Toolkit.getDefaultToolkit().getImage("src/GUI/res/forest.png");
                case 2 ->
                    Toolkit.getDefaultToolkit().getImage("src/GUI/res/desert.png");
                case 3 ->
                    Toolkit.getDefaultToolkit().getImage("src/GUI/res/ocean.png");
                default ->
                    Toolkit.getDefaultToolkit().getImage("src/GUI/res/grass.png");
            };
        }

    }

}
