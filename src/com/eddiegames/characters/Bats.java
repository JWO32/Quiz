package com.eddiegames.characters;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author 1402183
 */
public class Bats extends JPanel implements ActionListener
{
    int x;
    int y;
    boolean Visible;
    int spriteHeight;
    int spriteWidth;
    boolean collisonCheck;
    private final BufferedImage[] BatSprite = new BufferedImage[8];
    
    
    int frameSpeed = 10;
    int currentFrame;
        public Bats(int newX, int newY)
    {
        x = newX;
        y = newY;
        Visible = true;
        initEnemy();
    }

    //Loading array with images
    private void initEnemy()
    {
        try
        {
            BatSprite[0] = ImageIO.read(getClass().getResource("/Images/Bat 1.png"));
            BatSprite[1] = ImageIO.read(getClass().getResource("/Images/Bat 2.png"));
            BatSprite[2] = ImageIO.read(getClass().getResource("/Images/Bat 3.png"));
            BatSprite[3] = ImageIO.read(getClass().getResource("/Images/Bat 4.png"));
            BatSprite[4] = ImageIO.read(getClass().getResource("/Images/Bat 1.png"));
            BatSprite[5] = ImageIO.read(getClass().getResource("/Images/Bat 2.png"));
            BatSprite[6] = ImageIO.read(getClass().getResource("/Images/Bat 3.png"));
            BatSprite[7] = ImageIO.read(getClass().getResource("/Images/Bat 4.png"));

        }catch(Exception ex)
        {
            System.err.println("Error loading Bat sprite");
        }
        
        spriteWidth = BatSprite[0].getWidth();
        spriteHeight = BatSprite[0].getHeight();
        
    }
    
    //set Collision check
    public void setCollisionCheck(boolean collision)
    {
        collisonCheck = collision;
    }
    //get collision check
    public boolean getCollisionCheck()
    {
        return collisonCheck;
    }
    //set x
    public void setX(int newX)
    {
        x = newX;
    }
    //get x
    public int getX()
    {
        return x;
    }
    //set y
    public void setY(int newY)
    {
        y = newY;
    }
    //get y
    public int getY()
    {
        return y;
    }
    
    public int getSpriteWidth()
    {
        return spriteWidth;
    }
    
    public int getSpriteHeight()
    {
        return spriteHeight;
    }
    
     public BufferedImage getSprite()
    {
        currentFrame++;
        if(currentFrame > 7 * frameSpeed)
        currentFrame = 0;

        return BatSprite [currentFrame / frameSpeed];
    }
    
    public void setVisible(boolean isvisible)
    {
        Visible = isvisible;
    }
    
    public boolean getVisible()
    {
        return Visible;
    }
    
    public void Enemymove(int levelWidth, int levelHeight)
    {
        int tempX = x;
        int tempY = y;
        x = tempX;
        y = tempY;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle (x,y,spriteWidth,spriteHeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
