/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eddiegames.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Blueace
 */
public class Treasure 
{

    private int x;
    private int y;
    private final int spriteWidth;
    private final int spriteHeight;
    private boolean isVisible;
    private int currentFrame = 0;
    private int frameSpeed = 20;
    
    private BufferedImage[] sprite = new BufferedImage[5];
    
    private int score;
    
    
    public Treasure(int newX, int newY)
    {
        
        x = newX;
        y = newY;
        score = 100;
        
        isVisible = true;
        
        try
        {
            sprite[0] = ImageIO.read(getClass().getResourceAsStream("/Images/Coin 1.png"));
            sprite[1] = ImageIO.read(getClass().getResourceAsStream("/Images/Coin 2.png"));
            sprite[2] = ImageIO.read(getClass().getResourceAsStream("/Images/Coin 3.png"));
            sprite[3] = ImageIO.read(getClass().getResourceAsStream("/Images/Coin 4.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading treasure sprite");
        }
        
        spriteWidth = sprite[0].getWidth();
        spriteHeight = sprite[0].getHeight();
    }
    
    public void paintComponent (Graphics g)
    {
        currentFrame++;
        if(currentFrame > 4 * frameSpeed)
            currentFrame = 0;
        
        g.drawImage(sprite[currentFrame/frameSpeed], x, y, null);
    }

    
    public BufferedImage getSprite()
    {
        return sprite[currentFrame/frameSpeed];
    }
    
    public Rectangle getBounds()
    {
        Rectangle objectRect = new Rectangle(x,y, spriteWidth, spriteHeight);
        return objectRect;
    }
    
    public void setX(int newX)
    {
        x = newX;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setY(int newY)
    {
        y = newY;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setScore(int newScore)
    {
        score = newScore;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }
    
    public boolean getVisible()
    {
        return isVisible;
    }
    
}
