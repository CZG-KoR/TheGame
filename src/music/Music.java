/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author guest-7esanj
 */
public class Music implements LineListener{
    boolean playCompleted;
    Clip clip;
    int counter = 0;
    AudioInputStream audioInputStream;

    public void playSound() {
        ArrayList<String> list = new ArrayList();
        list.add("src/music/res/vibrating-thud-39536.wav");
        list.add("src/music/res/medieval-abbey-182197.wav");
        list.add("src/music/res/medieval-epic-adventure-action-heroic-powerful-opener-intro-117935.wav");
        list.add("src/music/res/nimue-the-lady-of-the-lake-medieval-love-ballad-5638.wav");
        list.add("src/music/res/paths-of-the-forefathers-184266.wav");
        list.add("src/music/res/the-virgin-medieval-music-4238.wav");
        list.add("src/music/res/wonderland-124601.wav");

        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File((String) list.get(0)).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!playCompleted) {
                        try {
                            Thread.sleep(clip.getMicrosecondLength()/1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            audioInputStream = AudioSystem.getAudioInputStream(new File((String) list.get(counter)).getAbsoluteFile());
                            clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                        } catch (Exception ex) {
                            System.out.println("Error with playing sound.");
                            ex.printStackTrace();
                        }
                        clip.start();
                        counter = (counter + 1) % list.size();
                    }
                }
            }
        };

        Thread th = new Thread(r);
        th.start();

    }

    @Override
    public void update(LineEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
