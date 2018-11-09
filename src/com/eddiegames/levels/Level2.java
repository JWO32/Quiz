package com.eddiegames.levels;
import com.eddiegames.characters.Bats;
import com.eddiegames.characters.Colisions;
import com.eddiegames.characters.Player;
import com.eddiegames.game.Game;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.eddiegames.characters.Treasure;
import java.io.IOException;
import java.net.URL;
import java.util.logging.*;
import javax.sound.sampled.*;
/**
 *
 * @author Blueace
 */
public class Level2 extends JPanel implements ActionListener 
{
    private final Game game;
    private Timer timer;
    private BufferedImage fullLifes,emptyLifes,score,background,pedestal;
    private final Player thePlayer;
    private final Bats[] theBats;
    private final int NUMBER_OF_BATS = 3;
    private final Treasure[] theTreasure;
    private final int NUMBER_OF_TREASURE = 17;
    private final Colisions[] theTopCollisions,theRightCollisions,theLeftCollisions,theBotomCollisions;
    private final int NUMBER_OF_TOP_COLISIONS = 10;
    private final int NUMBER_OF_RIGHT_COLISIONS = 8;
    private final int NUMBER_OF_LEFT_COLISIONS = 7;
    private final int NUMBER_OF_BOTTOM_COLISIONS = 4;
    Clip coins,backgroundMusic,death,victory,attack;

    
    
    public Level2(Game theGame) throws UnsupportedAudioFileException, IOException
    {
        game = theGame;
        thePlayer = new Player();
        theBats = new Bats[NUMBER_OF_BATS];
        theTreasure = new Treasure[NUMBER_OF_TREASURE];
        theTopCollisions = new Colisions[NUMBER_OF_TOP_COLISIONS];
        theRightCollisions = new Colisions[NUMBER_OF_RIGHT_COLISIONS];
        theLeftCollisions = new Colisions[NUMBER_OF_LEFT_COLISIONS];
        theBotomCollisions = new Colisions[NUMBER_OF_BOTTOM_COLISIONS];

        // All the Treasure  
        for(int i = 0; i < NUMBER_OF_TREASURE;i++)
        {    
            theTreasure[0] = new Treasure(195 , 228);
            theTreasure[1] = new Treasure(245 , 228);
            theTreasure[2] = new Treasure(295 , 228);
            theTreasure[3] = new Treasure(345 , 228);
            theTreasure[4] = new Treasure(395 , 228);
            theTreasure[5] = new Treasure(445 , 228);
            theTreasure[6] = new Treasure(495 , 228);
            theTreasure[7] = new Treasure(1060 , 200);
            theTreasure[8] = new Treasure(1110 , 200);
            theTreasure[9] = new Treasure(1160 , 200);
            theTreasure[10] = new Treasure(1210 , 200);
            theTreasure[11] = new Treasure(1260 , 200);
            theTreasure[12] = new Treasure(1310 , 200);
            theTreasure[13] = new Treasure(1360 , 200);
            theTreasure[14] = new Treasure(1410 , 200);
            theTreasure[15] = new Treasure(1460 , 200);
            theTreasure[16] = new Treasure(1510 , 200);     
        }
        
        //Bat location 
        for(int j = 0; j < NUMBER_OF_BATS;j++)
        {
            theBats[0] = new Bats(565,100);
            theBats[1] = new Bats(1760,250-50);
            theBats[2] = new Bats(880,340-40);
        }
        
        //Top Colision boundries
        theTopCollisions[0] = new Colisions(0,469,2400,1);
        theTopCollisions[1] = new Colisions(310,445,166,1);
        theTopCollisions[2] = new Colisions(180,290,465,1);
        theTopCollisions[3] = new Colisions(0,168,514,1);
        theTopCollisions[4] = new Colisions(475,420,498,1);
        theTopCollisions[5] = new Colisions(972,307,68,1);
        theTopCollisions[6] = new Colisions(1038,264,523,1);
        theTopCollisions[7] = new Colisions(1562,307,73,1);
        theTopCollisions[8] = new Colisions(1635,399,179,1);
        theTopCollisions[9] = new Colisions(1840,275,313,1);
        
        //The Right boundries 
        theRightCollisions[0] = new Colisions(644,28,1,265);
        theRightCollisions[1] = new Colisions(180,300,1,12);
        theRightCollisions[2] = new Colisions(310,447,1,12);
        theRightCollisions[3] = new Colisions(476,425,1,8);
        theRightCollisions[4] = new Colisions(972,314,1,89);
        theRightCollisions[5] = new Colisions(1039,272,1,20);
        theRightCollisions[6] = new Colisions(1840,280,1,20);
        theRightCollisions[7] = new Colisions(2382,29,1,430);
        
        
        //The Left boundries
        theLeftCollisions[0] = new Colisions(502,173,12,16);
        theLeftCollisions[1] = new Colisions(819,28,16,297);
        theLeftCollisions[2] = new Colisions(1551,274,12,15);
        theLeftCollisions[3] = new Colisions(1624,305,12,68);
        theLeftCollisions[4] = new Colisions(1799,408,14,46);
        theLeftCollisions[5] = new Colisions(2138,285,13,13);
        theLeftCollisions[6] = new Colisions(20,200,1,265);

        //The bottom boundries
        theBotomCollisions[0] = new Colisions(0,18,12,2400);
        theBotomCollisions[1] = new Colisions(25,19,488,8);
        theBotomCollisions[2] = new Colisions(180,316,654,9);
        theBotomCollisions[3] = new Colisions(1838,295,313,8);
        
        
       
        
       
       
        //Game sounds
        try {
            
            //Coin sounds
            URL url = getClass().getResource("/Sounds/smw_coin.wav");
            AudioInputStream audioIn;
            audioIn = AudioSystem.getAudioInputStream(url);
            coins = AudioSystem.getClip();
            coins.open(audioIn);
            
            //Background music
            URL url2 = getClass().getResource("/Sounds/Castle.wav");
            AudioInputStream audioIn2;
            audioIn2 = AudioSystem.getAudioInputStream(url2);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioIn2);
            
            //Death sound
            URL url3 = getClass().getResource("/Sounds/You Died.wav");
            AudioInputStream audioIn3;
            audioIn3 = AudioSystem.getAudioInputStream(url3);
            death = AudioSystem.getClip();
            death.open(audioIn3);
            
            //Victory sound
            URL url4 = getClass().getResource("/Sounds/victory.wav");
            AudioInputStream audioIn4;
            audioIn4 = AudioSystem.getAudioInputStream(url4);
            victory = AudioSystem.getClip();
            victory.open(audioIn4);
            
            URL url5 = getClass().getResource("/Sounds/Swoosh.wav");
            AudioInputStream audioIn5;
            audioIn5 = AudioSystem.getAudioInputStream(url5);
            attack = AudioSystem.getClip();
            attack.open(audioIn5);
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        init();
    }
    

    Level2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    private void init()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        backgroundMusic.start();
        
        //Background image
        try
        {
            background = ImageIO.read(getClass().getResource("/Images/Level 2 layout.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading background image");
        }
        //Score image
        try
        {
            score= ImageIO.read(getClass().getResource("/Images/Score.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading background image");
        }
        //***********************************
        
        // Health meter
        
        //Full health
        try
        {
            fullLifes = ImageIO.read(getClass().getResource("/Images/Health 1.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading health image");
        }
        //Empty health
        try
        {
            emptyLifes = ImageIO.read(getClass().getResource("/Images/Health 2.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading empty health image");
        }
        //************************************
        
        timer = new Timer(10, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //Pedestal image
         try
        { 
            pedestal = ImageIO.read(getClass().getResource("/Images/Feather Pointer Plinth.png"));
        } catch(Exception ex)
        {
            System.out.println("Error loading image");
        }

         //Victory conditions
         if(thePlayer.getX() >= 1850 && thePlayer.getY() >= 200)
         {
             victory.start();
             game.setScore(game.getScore() + (game.getHealth() * 500));
             backgroundMusic.stop();
             timer.stop();
             game.Victory();
         }
         
         
         
        //Draw Background
            g.drawImage(background, 0, 0, null);
            g.drawImage(score, 20, 100, null);
            g.drawImage(pedestal, 1850, 150, null);
        
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
        
        //Daw Obstacles
   
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
        
        for(int j = 0; j < NUMBER_OF_BATS;j++)
        {
            if(theBats[j].getVisible() == true)
            {
                g.drawImage(theBats[j].getSprite(),theBats[j].getX(),theBats[j].getY(),null); 
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
        Rectangle currentBatsBounds;
        Rectangle currentColisionsBounds;
        
        //Treasure collision rules
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
        
        //Bat enemy collision rules
        for(int j = 0; j < NUMBER_OF_BATS; j++)
        {
            currentBatsBounds = theBats[j].getBounds();
            
            if(theBats[j].getVisible() == true)
            {
                if(playerBounds.intersects(currentBatsBounds) && "Attack".equals(thePlayer.getDirection()))
                {
                    game.setScore(game.getScore()  + 50);
                    theBats[j].setVisible(false);
                }else
                {
                    if(playerBounds.intersects(currentBatsBounds))
                    {
                        if(theBats[j].getCollisionCheck() == false)
                        {
                            theBats[j].setCollisionCheck(true);
                            game.setHealth(game.getHealth() - 1);
                        }
                    }else
                    {
                        theBats[j].setCollisionCheck(false);
                    }
                }
            }
        }

        //Top Collision rules
        for(int c = 0; c < NUMBER_OF_TOP_COLISIONS; c++)
        {
            currentColisionsBounds = theTopCollisions[c].getBounds();
            
                if(playerBounds.intersects(currentColisionsBounds))
                {
                    thePlayer.setFall(false);
                    thePlayer.setGround(theTopCollisions[c].getY()-73);
                    break;
                }else
                {
                    thePlayer.setGround(390);
                }
        }
        
        //Right Collision rules
        for(int c = 0; c < NUMBER_OF_RIGHT_COLISIONS; c++)
        {
            currentColisionsBounds = theRightCollisions[c].getBounds();
            
            if(playerBounds.intersects(currentColisionsBounds) && "Right".equals(thePlayer.getDirection()) )
                {
                    try {
                        thePlayer.stop();
                    } catch (IOException ex) {
                        Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
        }
        
        //Left Collision rules
        for(int c = 0; c < NUMBER_OF_LEFT_COLISIONS; c++)
        {
            currentColisionsBounds = theLeftCollisions[c].getBounds();
            

                if(playerBounds.intersects(currentColisionsBounds) && "Left".equals(thePlayer.getDirection()) )
                {
                    try {
                        thePlayer.stop();
                    } catch (IOException ex) {
                        Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
        }
        
        //Bottom Collision rules
        for(int c = 0; c < NUMBER_OF_BOTTOM_COLISIONS; c++)
        {
            currentColisionsBounds = theBotomCollisions[c].getBounds();
            

                if(playerBounds.intersects(currentColisionsBounds) && "Up".equals(thePlayer.getDirection()) )
                {
                        thePlayer.setVspeed(0);
                }
            
        }
        
        
        
  
    }
    //Update movements
    public void doMovement()
    {
        thePlayer.updateMove();
        thePlayer.updateSprite();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {       
            checkCollisions();
            doMovement();
            repaint(); 
    }
    
    //Key map
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
