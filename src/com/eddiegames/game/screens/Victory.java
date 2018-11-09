package com.eddiegames.game.screens;

import com.eddiegames.game.Game;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.*;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author Blueace
 */
public class Victory extends JPanel implements ActionListener
{
 
    private final Game gameWindow;
    private BufferedImage backgroundImage,score;
    private final String SCREEN_TITLE = "Press Enter to Play Again";   
    private final String FINAL_SCORE;
    
    
    
    public Victory(Game theGame)
    {
        gameWindow = theGame;
        FINAL_SCORE = (""+ gameWindow.getScore());
        initPanel();
    }
    
    private void initPanel()
    {
        try
        {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/Victory.png"));
        }catch(Exception ex)
        {
            System.out.println("Error Loading Image");
        }
        
        try {
            score = ImageIO.read(getClass().getResource("/Images/Score.png"));
        } catch (IOException ex) {
            System.out.println("Error Loading Image");
        }
        
        addKeyListener(new TAdapter());
        setFocusable(true);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Font titleFont = new Font("Arial", Font.BOLD,25);
        g.setColor(Color.yellow);
        g.drawImage(backgroundImage, -250, 0, null);
        g.drawImage(score, 800, 127, null);
        g.setFont(titleFont);                
        g.drawString(SCREEN_TITLE, 800, 100);
        g.drawString(FINAL_SCORE, 940, 150);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class TAdapter extends KeyAdapter
    {
        
        @Override
        public void keyReleased(KeyEvent e)
        { 
            try {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    gameWindow.playGame();
                System.out.println("Test");//test
            } catch (UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            
        }
        
    }
    
}