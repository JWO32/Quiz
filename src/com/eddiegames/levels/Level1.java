package com.eddiegames.levels;
import com.eddiegames.characters.Arrow;
import com.eddiegames.characters.Colisions;
import com.eddiegames.characters.Enemy;
import com.eddiegames.characters.Player;
import com.eddiegames.game.Game;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import com.eddiegames.characters.Treasure;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.logging.*;
import javax.sound.sampled.*;
/**
 *
 * @author Blueace
 */
public class Level1 extends JPanel implements ActionListener
{
    private final Game game;
    private Timer timer;
    private BufferedImage fullLifes,emptyLifes,background,score;
    private final Player thePlayer;
    private final Arrow[] theArrow;
    private final int NUMBER_OF_ARROWS = 3;
    private final Enemy[] theEnemy;
    private final int NUMBER_OF_ENEMYS = 3;
    private final Treasure[] theTreasure;
    private final int NUMBER_OF_TREASURE = 16;
    private final Colisions[] theTopCollisions,theRightCollisions,theLeftCollisions;
    private final int NUMBER_OF_TOP_COLISIONS = 9;
    private final int NUMBER_OF_RIGHT_COLISIONS = 9;
    private final int NUMBER_OF_LEFT_COLISIONS = 9;
    Clip coins,backgroundMusic,death,victory,attack;  
    
    public Level1(Game theGame) throws UnsupportedAudioFileException, IOException
    {
        game = theGame;
        thePlayer = new Player();
        theArrow = new Arrow[NUMBER_OF_ARROWS];
        theEnemy = new Enemy[NUMBER_OF_ENEMYS];
        theTreasure = new Treasure[NUMBER_OF_TREASURE];
        theTopCollisions = new Colisions[NUMBER_OF_TOP_COLISIONS];
        theRightCollisions = new Colisions[NUMBER_OF_RIGHT_COLISIONS];
        theLeftCollisions = new Colisions[NUMBER_OF_LEFT_COLISIONS];
        
        //Declear the initial lifes
        game.setHealth(3);
        
        //Declear the initial score
        game.setScore(0);
        
        //Treasure location
        for(int i = 0; i < NUMBER_OF_TREASURE;i++)
        {    
            theTreasure[0] = new Treasure(75 , 175);
            theTreasure[1] = new Treasure(125 , 175);
            theTreasure[2] = new Treasure(175 , 175);
            theTreasure[3] = new Treasure(480 , 105);
            theTreasure[4] = new Treasure(420 , 105);
            theTreasure[5] = new Treasure(360 , 105);
            theTreasure[6] = new Treasure(655 , 105);
            theTreasure[7] = new Treasure(705 , 105);
            theTreasure[8] = new Treasure(755 , 105);
            theTreasure[9] = new Treasure(1045 , 140);
            theTreasure[10] = new Treasure(1095 , 140);
            theTreasure[11] = new Treasure(1145 , 140);
            theTreasure[12] = new Treasure(1195 , 140);
            theTreasure[13] = new Treasure(1245 , 140);
            theTreasure[14] = new Treasure(1295 , 140);
            theTreasure[15] = new Treasure(1345 , 140);
        }
        
        //The enemy location
        for(int j = 0; j < NUMBER_OF_ENEMYS;j++)
        {
            theEnemy[0] = new Enemy(1100, 240);
            theEnemy[1] = new Enemy(700, 405);
            theEnemy[2] = new Enemy(1850, 275);
        }
        
        //The Arrow location    
        for(int a = 0; a < NUMBER_OF_ARROWS; a++)
        {
            theArrow[0] = new Arrow(1100-10,235+20);
            theArrow[1] = new Arrow(700-10,400+20);
            theArrow[2] = new Arrow(1850-10,275+20);
        }
            
        //the top collisions
        theTopCollisions[0] = new Colisions(0,469,2400,1);
        theTopCollisions[1] = new Colisions(170,361,360,1);
        theTopCollisions[2] = new Colisions(62,232,164,1);
        theTopCollisions[3] = new Colisions(330,157,523,1);
        theTopCollisions[4] = new Colisions(768,384,922,1);
        theTopCollisions[5] = new Colisions(935,301,591,1);
        theTopCollisions[6] = new Colisions(1030,191,408,1);
        theTopCollisions[7] = new Colisions(1817,336,313,1);
        theTopCollisions[8] = new Colisions(2142,233,199,1);
        
        //the right collisions
        theRightCollisions[0] = new Colisions(170,376,1,90);
        theRightCollisions[1] = new Colisions(529,28,1,135);
        theRightCollisions[2] = new Colisions(769,395,1,71);
        theRightCollisions[3] = new Colisions(935,312,1,76);
        theRightCollisions[4] = new Colisions(1030,200,1,10);
        theRightCollisions[5] = new Colisions(1172,217,1,89);
        theRightCollisions[6] = new Colisions(1817,348,1,10);
        theRightCollisions[7] = new Colisions(2142,244,1,10);
        theRightCollisions[8] = new Colisions(2340,28,1,210);
        
        //the left collisions
        theLeftCollisions[0] = new Colisions(217,243,9,10);
        theLeftCollisions[1] = new Colisions(53,28,9,203);
        theLeftCollisions[2] = new Colisions(348,390,12,70);
        theLeftCollisions[3] = new Colisions(640,28,13,134);
        theLeftCollisions[4] = new Colisions(1428,201,9,8);
        theLeftCollisions[5] = new Colisions(1514,310,10,77);
        theLeftCollisions[6] = new Colisions(1286,214,10,83);
        theLeftCollisions[7] = new Colisions(1681,391,11,68);
        theLeftCollisions[8] = new Colisions(2120,345,9,13);
   
        
        //Level 1 sounds
        try {
            
            //coin sounds
            URL url = getClass().getResource("/Sounds/coin.wav");
            AudioInputStream audioIn;
            audioIn = AudioSystem.getAudioInputStream(url);
            coins = AudioSystem.getClip();
            coins.open(audioIn);
            
            //background music
            URL url2 = getClass().getResource("/Sounds/Castle.wav");
            AudioInputStream audioIn2;
            audioIn2 = AudioSystem.getAudioInputStream(url2);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioIn2);
            
            //Death music
            URL url3 = getClass().getResource("/Sounds/You Died.wav");
            AudioInputStream audioIn3;
            audioIn3 = AudioSystem.getAudioInputStream(url3);
            death = AudioSystem.getClip();
            death.open(audioIn3);  
            
            URL url4 = getClass().getResource("/Sounds/Swoosh.wav");
            AudioInputStream audioIn4;
            audioIn4 = AudioSystem.getAudioInputStream(url4);
            attack = AudioSystem.getClip();
            attack.open(audioIn4);
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        init();
    }

        
    private void init()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        backgroundMusic.start();
        
        // Level1 level design
        
        //Background image
        try
        {
            background = ImageIO.read(getClass().getResource("/Images/level1layout2.png"));

        }catch(Exception ex)
        {
            System.err.println("Error loading background image");
        }
        
        //score image
        try
        {
            score = ImageIO.read(getClass().getResource("/Images/Score.png"));

        }catch(Exception ex)
        {
            System.err.println("Error loading background image");
        }
        
        //Health bars
        try
        {
            fullLifes = ImageIO.read(getClass().getResource("/Images/Health 1.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading health image");
        }
        
        try
        {
            emptyLifes = ImageIO.read(getClass().getResource("/Images/Health 2.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading empty health image");
        }
        //**********************************
        
        timer = new Timer(10, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        
        //Draw Background
        g.drawImage(background, 0, 0, null);
        
        //Draw score
        g.drawImage(score, 20, 100, null);
        
        //Draw health bars
        if (game.getHealth() == 3)
        {
            g.drawImage(fullLifes, 20, 20, null);
            g.drawImage(fullLifes, 80, 20, null);
            g.drawImage(fullLifes, 140, 20, null);
        }
        else if (game.getHealth() == 2)
        {
            g.drawImage(fullLifes, 20, 20, null);
            g.drawImage(fullLifes, 80, 20, null);
            g.drawImage(emptyLifes, 140, 20, null);
        }
        else if (game.getHealth() == 1)
        {
            g.drawImage(fullLifes, 20, 20, null);
            g.drawImage(emptyLifes, 80, 20, null);
            g.drawImage(emptyLifes, 140, 20, null);
        }
        
        //Draw Game over message
        if(game.getHealth() <= 0)
        {
        death.start();
        backgroundMusic.stop();
        timer.stop();
        game.GameOver();
        }
        
        //Amount of treasure on level 1
        for(int i = 0; i < NUMBER_OF_TREASURE; i++)
        {
            if(theTreasure[i].getVisible() == true)
            {
                theTreasure[i].paintComponent(g);
            }
        }
        
        
        
        //Draw Characters 
        
        g.drawImage(thePlayer.getSprite(), thePlayer.getX(), thePlayer.getY(), null);
        
        //Draw Enemy
        
        for(int j = 0; j < NUMBER_OF_ENEMYS;j++)
        {
            if(theEnemy[j].getVisible() == true)
            {
                g.drawImage(theEnemy[j].getSprite(),theEnemy[j].getX(),theEnemy[j].getY(),null);
                
            }
        }
        
        //Draw enemy arrows
        for(int a = 0; a < NUMBER_OF_ARROWS; a++)
        {
            if(theEnemy[a].getVisible() == true)
            {
                if(theArrow[a].getVisible() == true)
                {
                    g.drawImage(theArrow[a].getSprite(), theArrow[a].getX(),theArrow[a].getY(), null);
                }
            }
            else
            {
                theArrow[a].setVisible(false); 
            }
        }

        //Draw curent score
        Font uiFont = new Font("Futura", Font.PLAIN, 30);
        g.setColor(Color.yellow);
        g.setFont(uiFont);
        g.drawString("" + game.getScore(), 165, 130);

        g.dispose();
    }
    
    public void checkCollisions()
    {
        
        Rectangle playerBounds = thePlayer.getBounds();
        Rectangle currentTreasureBounds;
        Rectangle currentEnemyBounds;
        Rectangle currentArrowBounds;
        Rectangle currentColisionsBounds;
        
        //Conditions for colliding with treasures
        for(int i = 0; i < NUMBER_OF_TREASURE; i++)
        {
            if(theTreasure[i].getVisible() == true)
            {
                currentTreasureBounds = theTreasure[i].getBounds();
                
                if(playerBounds.intersects(currentTreasureBounds) == true)
                {
                    
                    if(!coins.isRunning())
                    {
                        
                        coins.setFramePosition(0);
                        coins.start();
                    }else
                    {
                       coins.setFramePosition(0);
                       coins.start(); 
                    }
                    
                    game.setScore(game.getScore() + 100);
                    theTreasure[i].setVisible(false);
                }
            }
        }

        //Conditions for colliding with enemy
        for(int j = 0; j < NUMBER_OF_ENEMYS; j++)
        {
            currentEnemyBounds = theEnemy[j].getBounds();
            
            if(theEnemy[j].getVisible() == true)
            {
                if(playerBounds.intersects(currentEnemyBounds) && "Attack".equals(thePlayer.getDirection()))
                {
                    game.setScore(game.getScore() + 50);
                    theEnemy[j].setVisible(false);
                }else
                {
                    if(playerBounds.intersects(currentEnemyBounds))
                    {
                        if(theEnemy[j].getCollisionCheck() == false)
                        {
                            theEnemy[j].setCollisionCheck(true);
                            game.setHealth(game.getHealth() - 1);
                        }
                    }else
                    {
                        theEnemy[j].setCollisionCheck(false);
                    }
                }
            }
        }
        
        //Conditions for colliding with arrows
         for(int j = 0; j < NUMBER_OF_ARROWS; j++)
        {
            currentArrowBounds = theArrow[j].getBounds();
            
            if(theArrow[j].getVisible() == true)
            {
                if(playerBounds.intersects(currentArrowBounds) && "Block".equals(thePlayer.getDirection()))
                {
                    theArrow[j].setVisible(false);
                }else
                {
                    if(playerBounds.intersects(currentArrowBounds))
                    {
                        if(theArrow[j].getCollisionCheck() == false)
                        {
                            theArrow[j].setCollisionCheck(true);
                            theArrow[j].setVisible(false);
                            game.setHealth(game.getHealth() - 1);
                        }
                    }else
                    {
                        theArrow[j].setCollisionCheck(false);
                    }
                }
            }
        }
         
        //Top collision conditions
        for(int c = 0; c < NUMBER_OF_TOP_COLISIONS; c++)
        {
            currentColisionsBounds = theTopCollisions[c].getBounds();
            
                if(playerBounds.intersects(currentColisionsBounds))
                {
                    thePlayer.setFall(false);
                    thePlayer.setGround(theTopCollisions[c].getY()-72);
                    break;
                }else
                {
                    thePlayer.setGround(390);
                }
        }
        
        //Right collision conditions
        for(int c = 0; c < NUMBER_OF_RIGHT_COLISIONS; c++)
        {
            currentColisionsBounds = theRightCollisions[c].getBounds();
            
            {
                if(playerBounds.intersects(currentColisionsBounds) && "Right".equals(thePlayer.getDirection()) )
                {
                    try {
                        thePlayer.stop();
                    } catch (IOException ex) {
                        Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }   
        }    
        
        //Left collision conditions
        for(int c = 0; c < NUMBER_OF_LEFT_COLISIONS; c++)
        {
            currentColisionsBounds = theLeftCollisions[c].getBounds();
            
            {
                if(playerBounds.intersects(currentColisionsBounds) && "Left".equals(thePlayer.getDirection()) )
                {
                    try {
                        thePlayer.stop();
                    } catch (IOException ex) {
                        Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }  
        
        //Accesing level 2 conditions
        if(thePlayer.getX() == 1920)
        {
            try {
                backgroundMusic.stop();
                timer.stop();
                game.playLevel2();
            } catch (UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

  
    }
    
    //Updating movement
    public void doMovement()
    {
        thePlayer.updateMove();
        thePlayer.updateSprite();
        for( int a =0; a < NUMBER_OF_ARROWS; a ++)
        theArrow[a].Arrowmove(1200,500);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
            checkCollisions();
            doMovement();
            repaint();
    }

    //Control map
    private class TAdapter extends KeyAdapter
    {
        
        @Override
        public void keyPressed(KeyEvent e)
        {
         
            int move = 0;
            
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    move = 1;
                  
                    break;
                case KeyEvent.VK_LEFT:
                    move = 2;
                    break;
                case KeyEvent.VK_RIGHT:
                    move = 3;
                    break;
                case KeyEvent.VK_SPACE:
                    if(!attack.isRunning())
                    {
                        
                        attack.setFramePosition(0);
                        attack.start();
                    }else
                    {
                        attack.setFramePosition(0);
                        attack.start(); 
                    }
                    move = 4;
                    break;
                case KeyEvent.VK_SHIFT:
                    move = 5;
                default:
                    break;
            }
            try {
                thePlayer.move(move);
            } catch (IOException ex) {
                Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        @Override
        public void keyReleased(KeyEvent e)
        {
                     try {
                thePlayer.stop();
            } catch (IOException ex) {
                Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
