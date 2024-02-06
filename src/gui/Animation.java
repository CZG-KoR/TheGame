package gui;

import java.awt.Image;
import java.util.ArrayList;

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
    int curFrame = 0;
    Image[] images;
    float speed = 0.0001f;
    int startTime = 0;
    
    public Animation(Image[] images){
        this.images = images;
    }
    
    
    public void start(int curTime){
        if (!isPlaying){
            startTime = curTime;
        }
        
        isPlaying = true;
    }
    
    public void stop(){
        isPlaying = false;
    }
    
    public Image getCurImg(int curTime){
        
        curFrame = (curTime - (int)(startTime*speed))%images.length;
        
        return images[curFrame];
        
        
    }
    
}
