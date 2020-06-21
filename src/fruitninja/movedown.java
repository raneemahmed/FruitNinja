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
public class movedown implements Command {
    Gamecontroller f;

    public movedown(Gamecontroller g) {
        this.f=g;
    }
    
    @Override
    public void execute(Gameobject Obj, GameStrategy GStrategy) {
   int y = Obj.getYlocation();

            y += GStrategy.addedvelocity();
            y += Obj.getInitialVelocity();

            Obj.setYlocation(y);
            if (y < 800 && y > 750) {
                Obj.setMovingup(false);
                Obj.setMovingdown(false);
            } 
    }
    
}
