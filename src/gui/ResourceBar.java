/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Image;
import tools.MiscUtils;

/**
 *
 * @author guest-m2f7ja
 */
public class ResourceBar {
    private static final Image[] imageA = MiscUtils.loadImages("src/gui/res/resources");
    private Image resourceBarLeft;
    private Image resourceBarMid;
    private Image resourceBarRight;

    public ResourceBar() {
        this.resourceBarLeft = imageA[0];
        this.resourceBarMid = imageA[1];
        this.resourceBarRight = imageA[2];
    }

    public static Image[] getImageA() {
        return imageA;
    }
}
