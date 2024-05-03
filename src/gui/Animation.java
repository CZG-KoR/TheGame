package gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Animation {
    
    boolean isPlaying = false;
    boolean playOnce = false;
    int curFrame = 0;
    Image[] images;
    int delay;
    
    Timer t;
    
    public Animation(Image[] images, int delay){
        this.images = images;
        this.delay = delay;
        
        // Timer
        t = new Timer(delay, (ActionEvent e) -> {
            curFrame += 1;
            
            if (curFrame >= images.length && !playOnce){
                curFrame = 0;
            }
            
            if (curFrame >= images.length && playOnce){
                curFrame = images.length - 1;
                playOnce = false;
                stop();
            }
        });
    }
    
    
    public void start(){
        
        
        if (!isPlaying){
            t.start();
            curFrame = 0;
        }
        
        isPlaying = true;
    }
    
    public void stop(){
        isPlaying = false;
        t.stop();
    }
    
    public void playOnce(){
        playOnce = true;
        t.stop();
        t.start();

        
    }
    
    public Image getCurImg() {
        return images[curFrame];
    }

    public boolean isIsPlaying() {
        return isPlaying;
    }
    
}
