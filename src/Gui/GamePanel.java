/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import fruitninja.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author yarae
 */
public class GamePanel extends JPanel implements ActionListener, MouseMotionListener {

    ArrayList<Gameobject> Obj = new ArrayList();
    ArrayList<cross> cross = new ArrayList();
    Gamecontroller gcontrol;
    GameStrategy GStrategy;
    music mt;
    Timer t = new Timer(100, this);
    int time = 0;
    ImageIcon wall = null;
    ImageIcon blackcross = null;
    int x;
    int y;
    int cx = 900;

    public GamePanel(GameStrategy s) {
        this.GStrategy = s;
        mt=music.getInstance();
        gcontrol = Gamecontroller.getInstance();
        Obj = gcontrol.fillArray(GStrategy, Obj);
        addMouseMotionListener(this);
        t.start();
        wall = new ImageIcon("Fruit-Ninja-wallpapers-028.jpg");
        blackcross = new ImageIcon("blackcross.png");
        Dimension size = new Dimension(wall.getIconWidth(), wall.getIconHeight());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public GameStrategy getcurrentstrategy() {
        return GStrategy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        for (Gameobject Obj1 : Obj) {
            gcontrol.updateObjectsLocations(Obj1, GStrategy);
            if (!(GStrategy instanceof Level2)) {
                cross = gcontrol.fillArraycross(Obj);
            }
        }
        if (time % 50 == 0) {
            Obj = gcontrol.fillArray(GStrategy, Obj);
        }

        if (gcontrol.isEndofgame() == true) {
            if (Player.getInstance().getScore() > GStrategy.getHighscore()) {
                gcontrol.saveGame(GStrategy, Player.getInstance().getScore());
                t.stop();
                gcontrol.setisendofgame(false);
                GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
                
                 
                try {
                   mt.playsound("ta Da.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                YouWon.getInstance().setVisible(true);
            } else {
                t.stop();
                gcontrol.setisendofgame(false);
                GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();    
                try {
                    mt.playsound("Sad_Trombone.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                Endgame.getInstance().setVisible(true);
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(wall.getImage(), 0, 0, null);
        if (!(GStrategy instanceof Level2)) {
            g.drawImage(blackcross.getImage(), 900, 100, this);
            g.drawImage(blackcross.getImage(), 1000, 100, this);
            g.drawImage(blackcross.getImage(), 1100, 100, this);
        }
        Obj.stream().forEach((Obj1) -> {
            ImageIcon img = new ImageIcon(Obj1.getBufferedImages()[0]);
            ImageIcon imgslice = new ImageIcon(Obj1.getBufferedImages()[1]);
            if (!Obj1.isSliced()) {
                g.drawImage(img.getImage(), Obj1.getXlocation(), Obj1.getYlocation(), this);
            } else {
                if (Obj1 instanceof Specialbomb) {
                    g.drawImage(imgslice.getImage(), 150, 100, this);
                } else {
                    g.drawImage(imgslice.getImage(), Obj1.getXlocation(), Obj1.getYlocation(), this);
                }
            }
        });
        if (!(GStrategy instanceof Level2)) {
            cross.stream().forEach((cros) -> {
                ImageIcon crossimg = new ImageIcon(cros.getBufferedImages());
                g.drawImage(crossimg.getImage(), cros.getXpos(), cros.getYpos(), this);
            });
}

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        gcontrol.sliceObjects(x, y, Obj);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
