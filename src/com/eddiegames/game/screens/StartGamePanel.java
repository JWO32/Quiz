package com.eddiegames.game.screens;

import com.eddiegames.game.Game;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author Blueace
 */

public class StartGamePanel extends JPanel
{
    private final Game gameWindow;
    private BufferedImage backgroundImage = null;
    private final String SCREEN_TITLE1 = "Main Menu Please press 'SPACE' to play.";    
    private final String SCREEN_TITLE2 = "Use arrow keys to move and 'SPACE' bar to attack. For Block press 'SHIFT'.";    
    
    
    public StartGamePanel(Game theGame)
    {
        gameWindow = theGame;
        initPanel();
    }
    
    //Load title screen
    private void initPanel()
    {
        try
        {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/TitleScreen.png"));
        }catch(Exception ex)
        {
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
        g.setFont(titleFont);                
        g.drawString(SCREEN_TITLE1, 700, 400);
        g.drawString(SCREEN_TITLE2, 500, 435);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private class TAdapter extends KeyAdapter
    {
        
        @Override
        public void keyReleased(KeyEvent e)
        {
            try {
                if(e.getKeyCode() == KeyEvent.VK_SPACE)
                    gameWindow.playGame();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StartGamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            
        }
        
    }
    
}
