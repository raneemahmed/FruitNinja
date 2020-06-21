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
public class Factory {

    public Gameobject getGameobject(String name) {
        if (name.equals("apple")) {
            return new Apple();
        }
        if (name.equals("coconut")) {
            return new Coconut();
        }
        if (name.equals("lemon")) {
            return new Lemon();
        }
        if (name.equals("orange")) {
            return new Orange();
        }
        if (name.equals("watermelon")) {
            return new Watermelon();
        }
        if (name.equals("bomb")) {
            return new Bomb();
        }
        if (name.equals("specialbomb")) {
            return new Specialbomb();
        }
        if (name.equals("specialfruit1")) {
            return new specialfruit();   
        }
        if (name.equals("specialfruit2")) {
            return new specialfruit2();
        }
        return null;
    }
}
