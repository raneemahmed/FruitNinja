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
public interface Command {
    public  void  execute(Gameobject Obj, GameStrategy GStrategy);
}
