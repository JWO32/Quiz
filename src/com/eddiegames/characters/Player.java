package com.eddiegames.characters;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.io.IOException;
/**
 *
 * @author 1402183
 */
public class Player
{
    private int x; 
    private int y; 
    private int dX;
    private boolean falling;
    String Direction;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    int ground;
    int gravity = 1;
    int vspeed = 1;
    int jump = - 18;
    
    public Player()           
    {
        x = 300;
        y = 100;
        falling = true;
        initCharacter();
    }
    
    public void setGround(int newGround)
    {
        ground = newGround;
    }
    
    private void initCharacter()
    {
        
        try
        {
            sprite = ImageIO.read(getClass().getResource("/images/knight-right.png"));

        }catch(Exception ex)
        {
            System.err.println("Error loading player sprite");
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight(); 
    }
    
    public void setJump(int newJump)
    {
        jump = newJump;
    }
    
    public void setVspeed(int newVspeed)
    {
        vspeed = newVspeed;
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
        return sprite;
    }
    
    public void setFall(boolean yes)
    {
        falling = yes;
    }
    
    public boolean getFall()
    {
        return falling;
    }
    
    public void updateMove()
    {
            if (y < ground){
                
                vspeed+=gravity;
        
          
            if (y > ground-vspeed)
            {
                vspeed = ground;
            }
        }
        else if (vspeed > 0)
        {
            vspeed = 0;
            y = ground;
        }
        y += vspeed; 

        x += dX;
    }
    
    public void updateSprite()
    {
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
    }
    
    public void move(int direction) throws IOException

    {
        switch (direction)
        {
            case 1:
                
                Direction = "Up";
                if( y >= ground)
                {
                vspeed += jump;
                }
                break;
         
            case 2:
                dX = -4;
                sprite = ImageIO.read(getClass().getResource("/images/knight-left-run.png"));
                Direction = "Left";
                break;
            case 3:
                dX = 4;
                sprite = ImageIO.read(getClass().getResource("/images/knight-right-run.png"));
                Direction = "Right";
                break;
            case 4:
                sprite = ImageIO.read(getClass().getResource("/images/knight-right-atack2.png"));
                Direction = "Attack";
                break;
            case 5:
                sprite = ImageIO.read(getClass().getResource("/images/Pixel Knight 4.png"));
                Direction = "Block";
            default:
                break;
        }    
    }

    public String getDirection()
        {
            return Direction;
        }

    public void stop() throws IOException
    {
        if(null != Direction)
        switch (Direction) {
            case "Right":
                sprite = ImageIO.read(getClass().getResource("/images/knight-right.png"));
                break;
            case "Left":
                sprite = ImageIO.read(getClass().getResource("/images/knight-left.png"));
                break;
            case "Attack":
                sprite = ImageIO.read(getClass().getResource("/images/knight-right.png"));
            case "Block":
                sprite = ImageIO.read(getClass().getResource("/images/knight-right.png")); 
                break;
        }
        dX = 0; 
    }

    public Rectangle getBounds()
    {
        Rectangle characterRect = new Rectangle(x,y, spriteWidth, spriteHeight);
        return characterRect;
    }
            
}
