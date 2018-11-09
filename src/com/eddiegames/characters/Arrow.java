package com.eddiegames.characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author 1402183
 */
public class Arrow 
{
    int x;
    int y;
    int b;
    boolean Visible;
    int speed = 5;
    int spriteHeight;
    int spriteWidth;
    boolean collisonCheck;
    private BufferedImage Arrowsprite;
    
    
    public Arrow(int newX, int newY)
    {
        b = newX;
        x = newX;
        y = newY;
        Visible = true;
        initArrow();
    }

    private void initArrow()
    {
        try
        {
            Arrowsprite = ImageIO.read(getClass().getResource("/Images/Arrow.png"));

        }catch(Exception ex)
        {
            System.err.println("Error loading aroow sprite");
        }
        spriteWidth = Arrowsprite.getWidth();
        spriteHeight = Arrowsprite.getHeight();
    }
    
    
    public void setCollisionCheck(boolean collision)
    {
        collisonCheck = collision;
        
    }
    
    public boolean getCollisionCheck()
    {
        return collisonCheck;
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
        return Arrowsprite; 
    }
    
    public void setVisible(boolean isvisible)
    {
        Visible = isvisible;
    }
    
    public boolean getVisible()
    {
        return Visible;
    }
    
    public void Arrowmove(int levelWidth, int levelHeight)
    {
        int tempX = x - speed;
        int tempY = y;
        if( tempX  <= -20 || (Visible == false && tempX  <= -20))
        {
            tempX = b;
            Visible = true;
        }

        x = tempX;
        y = tempY;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle (x,y,spriteWidth,spriteHeight);
    }
    
    
    
    
}
