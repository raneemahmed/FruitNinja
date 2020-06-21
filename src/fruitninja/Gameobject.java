/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import java.awt.image.BufferedImage;

/**
 *
 * @author yarae
 */
public interface Gameobject {
/**
*@return the type of game object
*/
public Enum getObjectType();
public int getXlocation();
public int getYlocation();
/*
*@return max Y location that the object can reach on the screen
*/
public int getMaxHeight();
public int getInitialVelocity();
/*
*@return failing velocity of game object
*/
public int getFallingVelocity();
public Boolean isSliced();
public void slice();
/*
*it is used to move the object on the screen
@param deltaTime: time elapsed since the object is thrown
it is used calculate the new position of
fruit object.
*/
public void move(double time);
public BufferedImage [] getBufferedImages();
public void setXlocation(int x);
public void setYlocation(int y);
public boolean isMovingUp();
public void setMovingup(boolean movingUp);
public boolean isMovingdown();
public void setMovingdown(boolean movingdown);
public boolean hasMovedOffScreen();
public void sethasMovedOffScreen(boolean mv);
public int scoreofobject();
}

