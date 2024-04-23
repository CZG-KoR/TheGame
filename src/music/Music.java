package music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music implements LineListener {
    boolean playCompleted;
    Clip clip;
    int counter = 0;
    AudioInputStream audioInputStream;

    public void playSound() {
        ArrayList<String> list = new ArrayList<>();
        list.add("src/music/res/vibrating-thud-39536.wav");
        list.add("src/music/res/medieval-abbey-182197.wav");
        list.add("src/music/res/medieval-epic-adventure-action-heroic-powerful-opener-intro-117935.wav");
        list.add("src/music/res/nimue-the-lady-of-the-lake-medieval-love-ballad-5638.wav");
        list.add("src/music/res/paths-of-the-forefathers-184266.wav");
        list.add("src/music/res/the-virgin-medieval-music-4238.wav");
        list.add("src/music/res/wonderland-124601.wav");

        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(list.get(0)).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.INFO, "Error with playing sound.");
            ex.printStackTrace();
        }

        Runnable r = () -> {
            while (true) {
                if (!playCompleted) {
                    try {
                        Thread.sleep(clip.getMicrosecondLength() / 1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    try {
                        audioInputStream = AudioSystem
                                .getAudioInputStream(new File(list.get(counter)).getAbsoluteFile());
                        clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(Music.class.getName()).log(Level.INFO, "Error with playing sound.");
                        ex.printStackTrace();
                    }
                    clip.start();
                    counter = (counter + 1) % list.size();
                }
            }
        };

        Thread th = new Thread(r);
        th.start();
    }

    @Override
    public void update(LineEvent event) {
        throw new UnsupportedOperationException();
    }

}