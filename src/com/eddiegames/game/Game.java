package com.eddiegames.game;


import com.eddiegames.game.screens.GameOver;
import com.eddiegames.game.screens.StartGamePanel;
import com.eddiegames.game.screens.Victory;
import com.eddiegames.levels.Level1;
import com.eddiegames.levels.Level2;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author Blueace
 */

public class Game 
{
    private final int WINDOW_WIDTH = 1920;
    private final int WINDOW_HEIGHT = 500;
    
    JFrame gameWindow; //Main Game Window 
    StartGamePanel startScreen;
    Level1 lvl1;
    Level2 lvl2;
    Victory victory;
    GameOver gameOver;
    
    int health;
    int score;
    
    public void setHealth(int newHealth)
    {
        health = newHealth;
    }
    public int getHealth()
    {
        return health;
    }
    
    public void setScore(int newScore)
    {
        score = newScore;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public Game()
    {
        initWindow();      
    }
    
    
    private void initWindow()
    {
        gameWindow = new JFrame();
        gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setTitle("Feather Knight");
    }
    
    public void showStartScreen()
    {
        startScreen = new StartGamePanel(this);
        startScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameWindow.getContentPane().setLayout(new CardLayout());
        gameWindow.getContentPane().add(startScreen, "INTRO");
        gameWindow.setVisible(true);
        startScreen.requestFocus();
    }
    
    public static void main (String[] args)
    {
        Game window = new Game();
        window.showStartScreen();
    }

    public void playGame() throws UnsupportedAudioFileException, IOException
    {
        lvl1 = new Level1(this);
        lvl1.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameWindow.getContentPane().add(lvl1, "LVL1");
        CardLayout cl = (CardLayout)gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        lvl1.requestFocus();
    }
    
    public void playLevel2() throws UnsupportedAudioFileException, IOException
            
    {
        lvl2 = new Level2(this);
        lvl2.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameWindow.getContentPane().add(lvl2, "LVL2");
        CardLayout cl = (CardLayout)gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        lvl2.requestFocus();
    }
    
       public void Victory()
    {
        victory = new Victory(this);
        victory.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameWindow.getContentPane().add(victory, "Victory");
        CardLayout cl = (CardLayout)gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        victory.requestFocus();
    }
       
    
       public void GameOver()
    {
        gameOver = new GameOver(this);
        gameOver.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameWindow.getContentPane().add(gameOver, "GameOver");
        CardLayout cl = (CardLayout)gameWindow.getContentPane().getLayout();
        cl.last(gameWindow.getContentPane());
        gameOver.requestFocus();
    }
    
    
}
