/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import java.util.Random;

/**
 *
 * @author yarae
 */
public class Level2 implements GameStrategy{
 Factory f =new Factory();
 private int highscore=0;
    @Override
    public int addedvelocity() {
        return 40;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public Gameobject createGameObject() {
        Random r = new Random();
        int m = r.nextInt(8);
        if (m == 0) 
            return f.getGameobject("apple");
        if (m == 1) 
            return f.getGameobject("orange");
        if(m == 2)
            return f.getGameobject("watermelon");
        if(m == 3)
            return f.getGameobject("lemon");
        if (m == 4)
            return f.getGameobject("coconut");
        if (m==5) {
            return f.getGameobject("specialbomb");
        }
        if (m==6) {
            return f.getGameobject("specialfruit1");
        }
        if (m==7) {
            return f.getGameobject("specialfruit2");
        }
        else
            return f.getGameobject("bomb");
              
    }

    @Override
    public int timer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
