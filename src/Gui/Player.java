/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.util.ArrayList;

/**
 *
 * @author OWNER 2
 */
public class Player {

    private static Player p = null;
    private int Score;
    ArrayList<Observer> observers = new ArrayList<>();

    public int getScore() {
        return Score;
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void notifyAllObservers() {
        
        for (Observer observer : observers) {
            observer.Update();
        }
    }

    public void setScore(int Score) {
        
        this.Score =this.Score+Score;
        notifyAllObservers();
    }

    private Player() {
        
    }

    public static Player getInstance() {
        if (p == null) {
            p= new Player();
        }
        return p;
    }

}
