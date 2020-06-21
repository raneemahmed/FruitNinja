/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

/**
 *
 * @author yarae
 */
public interface GameStrategy {
    public int addedvelocity();
    public Gameobject createGameObject();
    public int timer();  
    public int getHighscore();
    public void setHighscore(int highscore);
    
}
