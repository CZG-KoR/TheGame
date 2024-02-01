/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music;

import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Timer;

/**
 *
 * @author guest-7esanj
 */
public class Music {

    public void playSound() {
        ArrayList<String> list = new ArrayList();
        list.add("src/music/res/vibrating-thud-39536.wav");
        list.add("src/music/res/background-music-for-trailer-amp-shorts-184413.wav");
        list.add("src/music/res/LANDS.wav");

        int i = 0;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File((String) list.get(0)).getAbsoluteFile());

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
            clip.loop(1);

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}