/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author yarae
 */
public class cross {
    private int xpos, ypos;

    public cross() {
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
    
    public BufferedImage getBufferedImages() {
         BufferedImage img = null ;
     
        try {
            img = ImageIO.read(new File("cross.png"));         
        }
       catch (IOException ex) {
            Logger.getLogger(Watermelon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
}
