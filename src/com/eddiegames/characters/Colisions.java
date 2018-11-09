package com.eddiegames.characters;

import java.awt.Rectangle;

/**
 *
 * @author Blueace
 */
public class Colisions 
{

    private final int x;
    private final int y;
    private final int width;
    private final int height;
        
    
    public Colisions(int newX, int newY,int newWidth,int newHeight)
    {
        
        x = newX;
        y = newY;
        width = newWidth;
        height = newHeight;
    }

    public int getY()
    {
        return y;
    }

    public Rectangle getBounds()
    {
        Rectangle objectRect = new Rectangle(x,y, width, height);
        return objectRect;
    }
   
    
}
