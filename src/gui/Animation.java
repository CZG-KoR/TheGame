package gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guest-rlwnhj
 */
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
        t = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                curFrame += 1;
                
                if (curFrame >= images.length && !playOnce){
                    curFrame = 0;
                }
                
                if (curFrame >= images.length && playOnce){
                    curFrame = images.length - 1;
                    playOnce = false;
                    stop();
                }
                
                
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
    
    public Image getCurImg(){
        
        //curFrame = (curTime - (int)(startTime*speed))%images.length;
        
        return images[curFrame];
        
        
    }

    public boolean isIsPlaying() {
        return isPlaying;
    }
    
}
