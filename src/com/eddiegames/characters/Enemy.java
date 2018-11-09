package com.eddiegames.characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author 1402183
 */
public class Enemy 
{
    int x;
    int y;
    boolean Visible;
    int spriteHeight;
    int spriteWidth;
    boolean collisonCheck;
    private BufferedImage Enemysprite;
    
    
    public Enemy(int newX, int newY)
    {
        x = newX;
        y = newY;
        
        Visible = true;
        initEnemy();
    }

    //Load enemt sprite
    private void initEnemy()
    {
        try
        {
            Enemysprite = ImageIO.read(getClass().getResource("/Images/Skeleton 2.png"));

        }catch(Exception ex)
        {
            System.err.println("Error loading player sprite");
        }
        
        spriteWidth = Enemysprite.getWidth();
        spriteHeight = Enemysprite.getHeight();
    }
    
    
    public void setCollisionCheck(boolean collision)
    {
        collisonCheck = collision;
    }
    
    public boolean getCollisionCheck()
    {
        return collisonCheck;
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
        return Enemysprite;
    }
    
    public void setVisible(boolean isvisible)
    {
        Visible = isvisible;
    }
    
    public boolean getVisible()
    {
        return Visible;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle (x,y,spriteWidth,spriteHeight);
    }

}
