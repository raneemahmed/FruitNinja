/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import java.util.ArrayList;

/**
 *
 * @author yarae
 */
public interface GameAction {
/*
* update moving objects locations
*/
public void updateObjectsLocations(Gameobject obj,GameStrategy GStrategy);
/*
* it is used to slice fruits located in your swiping region
This method can take your swiping region as parameters (they
depend on how you calculate it).
*/
public void sliceObjects(int x,int y,ArrayList<Gameobject>obj);
/*
*saves the current state of the game
*/
public void saveGame(GameStrategy gameStrategy,int score);
/*
*loads the last saved state of the game
*/
public void loadGame(GameStrategy s);
/*
*resets the game to its initial state
*/
public void ResetGame();
public ArrayList<Gameobject> fillArray(GameStrategy GStrategy,ArrayList<Gameobject> Obj);
}
