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
public class specialfruit2 implements Gameobject {
     private int xpos, ypos;
    boolean sliced=false;
    boolean movingup = true;
    boolean movingdown=false;
    boolean moveoffscreen=false;
    @Override
    public Enum getObjectType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getXlocation() {
        return xpos;
    }

    @Override
    public int getYlocation() {
        return ypos;
    }

    @Override
    public int getMaxHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getInitialVelocity() {
        return 30;
    }

    @Override
    public int getFallingVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isSliced() {
        return sliced;
    }

     @Override
     public void sethasMovedOffScreen(boolean mv){
         this.moveoffscreen=mv;
     }
     @Override
    public boolean hasMovedOffScreen() {
       
        return moveoffscreen;
                
    }

    @Override
    public void slice() {
       sliced=true;
    }

    @Override
    public void move(double time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BufferedImage[] getBufferedImages() {
         BufferedImage img[] = new BufferedImage[2];;
     
        try {
            img[0] = ImageIO.read(new File("specialfruit2.png"));
            img[1]= ImageIO.read(new File("specialfruit2slice.png"));
        }
       catch (IOException ex) {
            Logger.getLogger(Watermelon.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return img;
    }

    @Override
    public void setXlocation(int x) {
        this.xpos = x;
    }

    @Override
    public void setYlocation(int y) {
        this. ypos=y;
    }
     @Override
    public boolean isMovingUp() {
        return movingup;
    }

    @Override
    public void setMovingup(boolean movingUp) {
       this.movingup = movingUp;
    }
    @Override
    public boolean isMovingdown() {
        return movingdown;
    }

    @Override
    public void setMovingdown(boolean movingdown) {
       this.movingdown = movingdown;
    }
    @Override
    public int scoreofobject() {
        return 50;
    }
}
