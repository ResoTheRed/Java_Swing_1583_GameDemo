import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

public abstract class Sprite{

    protected int x, y, width, height;
    protected Color bg_color;
    protected Image image;
    
    public Sprite(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public abstract void paint(Graphics graphics);
    public abstract void setupGraphics();
   
}

