/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitninja;

import Gui.Player;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author yarae
 */
public class Gamecontroller implements GameAction {

    boolean Endofgame =false;
    Player Player;
    ArrayList<cross> cross = new ArrayList<>();
    movedown d=new movedown(this);
    moveup u =new moveup(this);
private static Gamecontroller Instance;
    private Gamecontroller() {
    }
    public static Gamecontroller getInstance(){
        if (Instance==null) {
            Instance=new Gamecontroller();
        }
        return Instance;
    }
    public boolean isEndofgame() {

        return Endofgame;
    }
    public void setisendofgame(boolean b){
        this.Endofgame=b;
    }
    @Override
    public void updateObjectsLocations(Gameobject Obj, GameStrategy GStrategy) {
        if (Obj.isMovingUp() == true && Obj.isMovingdown() == false) {
            u.execute(Obj, GStrategy);
   
        }
        if (Obj.isMovingUp() == false && Obj.isMovingdown() == true) {
            d.execute(Obj, GStrategy);
        }
        if (Obj.isMovingUp() == false && Obj.isMovingdown() == false) {
            Obj.sethasMovedOffScreen(true);
        }

    }

    @Override
    public void sliceObjects(int x, int y, ArrayList<Gameobject> obj) {
        for (int i = 0; i < obj.size(); i++) {
            Gameobject gc = obj.get(i);
            ImageIcon img = new ImageIcon(obj.get(i).getBufferedImages()[0]);
            int xg = gc.getXlocation();
            int yg = gc.getYlocation();
            if (x >= xg && x <= xg + img.getIconWidth()&&!gc.isSliced()) {
                if (y >= yg && y <= yg + img.getIconHeight()) {
                    gc.slice();
                    if (obj.get(i) instanceof Specialbomb) {
                        AudioInputStream audioInputStream = null;    
                        try {
                            String soundName = "Explosion 2.wav";
                            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            Endofgame = true;
                        } catch (UnsupportedAudioFileException ex) {
                            Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                audioInputStream.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                            if (obj.get(i) instanceof Bomb) {
                                AudioInputStream audioInputStream = null;
                                try {
                                    String soundName = "Bomb Explosion 1.wav";
                                    audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(audioInputStream);
                                    clip.start();
                                } catch (UnsupportedAudioFileException ex) {
                                    Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                                } finally {
                                    try {
                                        audioInputStream.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            else{
                                AudioInputStream audioInputStream = null;
                                try {
                                    String soundName = "Cutting-Sound.wav";
                                    audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(audioInputStream);
                                    clip.start();
                                } catch (UnsupportedAudioFileException ex) {
                                    Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                                } finally {
                                    try {
                                        audioInputStream.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Gamecontroller.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                           
                            }
                             Player.getInstance().setScore(obj.get(i).scoreofobject());
                             break;
                        
                        }
                    }

                }
            }

        
    }

    @Override
    public void saveGame(GameStrategy gameStrategy,int score) {
         try {
         
             if (gameStrategy instanceof Level1) {
                    DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();
            
            Element root = doc.createElement("FruitNinja");
            doc.appendChild(root);
            Element level1 = doc.createElement("level1");
            root.appendChild(level1);
             Element maxscore = doc.createElement("score");
            String x = Integer.toString(score);
                 maxscore.appendChild(doc.createTextNode(x));
                 level1.appendChild(maxscore);
                
            
                       TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("FruitNinja1.xml"));
            transformer.transform(source, result);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
                 
             }
             if (gameStrategy instanceof Level2) {
                                     DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();
            
            Element root = doc.createElement("FruitNinja");
            doc.appendChild(root);
            Element level2 = doc.createElement("level2");
            root.appendChild(level2);
                  Element maxscore = doc.createElement("score");
            String y = Integer.toString(score);
                 maxscore.appendChild(doc.createTextNode(y));
                 level2.appendChild(maxscore);
           
                       TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("FruitNinja2.xml"));
            transformer.transform(source, result);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
                 
             }
             if (gameStrategy instanceof Level3) {
                          DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();
            
            Element root = doc.createElement("FruitNinja");
            doc.appendChild(root);
            Element level3 = doc.createElement("level3");
            root.appendChild(level3);
                      Element maxscore = doc.createElement("score");
            String z = Integer.toString(score);
                 maxscore.appendChild(doc.createTextNode(z));
                 level3.appendChild(maxscore);
       
                       TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("FruitNinja3.xml"));
            transformer.transform(source, result);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
             }
       
         }
         catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadGame(GameStrategy s) {
        try {
            int score;
            if (s instanceof Level1) {   
        File inputFile = new File("FruitNinja1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList score1 = doc.getElementsByTagName("level1");
                Node sNode = score1.item(0);
                if (sNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) sNode;
                    String mString = eElement.getTextContent();
                    score=(Integer.parseInt(mString));
                    s.setHighscore(score);
                }}
          else if (s instanceof Level2) {   
        File inputFile = new File("FruitNinja2.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList score1 = doc.getElementsByTagName("level2");
                Node sNode = score1.item(0);
                if (sNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) sNode;
                    String mString = eElement.getTextContent();
                    score=(Integer.parseInt(mString));
                    s.setHighscore(score);
                }}
         else if (s instanceof Level3) {   
        File inputFile = new File("FruitNinja3.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList score1 = doc.getElementsByTagName("level3");
                Node sNode = score1.item(0);
                if (sNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) sNode;
                    String mString = eElement.getTextContent();
                    score=(Integer.parseInt(mString));
                    s.setHighscore(score);
                }}
             
        } catch (Exception e) {
            e.printStackTrace();}
    }

    @Override
    public void ResetGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Gameobject> fillArray(GameStrategy GStrategy, ArrayList<Gameobject> Obj) {
        int startX = 200;
        int starty = 750;
        Random r = new Random();
        int m = r.nextInt(5) + 1;
        for (int i = 0; i < m; i++) {
            Gameobject go = GStrategy.createGameObject();
            go.setXlocation(startX);
            go.setYlocation(starty);
            startX += 150;
            Obj.add(go);
        }
        return Obj;
    }

    public ArrayList<cross> fillArraycross(ArrayList<Gameobject> Obj) {
        int xstart = 900;
        int ystart = 100;
        cross.clear();
        for (int i = 0; i < Obj.size(); i++) {
            if (Obj.get(i) instanceof Bomb && Obj.get(i).isSliced()) {
                    cross c = new cross();
                    c.setXpos(xstart);
                    c.setYpos(ystart);
                    xstart += 100;
                    cross.add(c);
                
            }
            if (Obj.get(i).hasMovedOffScreen() == true && Obj.get(i).isSliced() == false) {
                if (!(Obj.get(i) instanceof Specialbomb) && !(Obj.get(i) instanceof Bomb)) {
                    
                        cross c = new cross();
                        c.setXpos(xstart);
                        c.setYpos(ystart);
                        xstart += 100;
                        cross.add(c);
                    
                }

            }
            if (cross.size()==3) {
                   Endofgame=true;
                } 
        }
        return cross;
    }

}
