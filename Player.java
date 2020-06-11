import java.awt.*;  // Using awt's graphics and Color functions
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.*;

public class Player extends Sprite{

    private int deltaX= 0, deltaY=0;

    public Player(int x, int y, int width, int height){
        super(x,y,width,height);
        super.bg_color = Settings.WHITE;
        setupGraphics();
    }

    public void paint(Graphics graphics){
        graphics.setColor(super.bg_color);
        graphics.fillRect(x, y, width, height);
        graphics.drawImage(super.image,x,y,null);
    }

    public void setupGraphics(){
        ImageIcon iconPlayer = null;
        URL imgURL = getClass().getClassLoader().getResource(Settings.PLAYER_FILE_NAME);
        if(imgURL != null){
            iconPlayer = new ImageIcon(imgURL);
        }else{
            System.err.println("Couldn't find file: "+ Settings.PLAYER_FILE_NAME);
        }
        super.image = iconPlayer.getImage();
    }

    public Image getPlayerImage(){
        return super.image;
    }

    public void move(String direction, DrawCanvas canvas){
        int savedX = super.x; 
        int savedY = super.y;
        switch(direction){
            case "Left": {
                super.x -=Settings.PLAYER_SPEED;
                repaint(savedX, super.y,canvas);
            }break;
            case "Right": {
                super.x +=Settings.PLAYER_SPEED;;
                repaint(savedX, super.y,canvas);
            }break;
            case "Up": {
                super.y -=Settings.PLAYER_SPEED;;
                repaint(super.x, savedY,canvas);
            }break;
            case "Down":{
                super.y +=Settings.PLAYER_SPEED;;
                repaint(super.x, savedY,canvas);
            }break;
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_LEFT)
            deltaX = -Settings.PLAYER_SPEED;
        if(key==KeyEvent.VK_RIGHT)
            deltaX = Settings.PLAYER_SPEED;
        if(key==KeyEvent.VK_UP)
            deltaY = -Settings.PLAYER_SPEED;
        if(key==KeyEvent.VK_DOWN)
            deltaY = Settings.PLAYER_SPEED;
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_LEFT)
            deltaX = 0;
        if(key==KeyEvent.VK_RIGHT)
            deltaX = 0;
        if(key==KeyEvent.VK_UP)
            deltaY = 0;
        if(key==KeyEvent.VK_DOWN)
            deltaY = 0;
    }

    public void updateSprite(DrawCanvas canvas){
        int savedX = super.x;
        int savedY = super.y;
        super.x += deltaX;
        super.y += deltaY;
        repaint(savedX, savedY,canvas);
    }
    
    /*Move and repaint should be updated */
    private void repaint(int x, int y, DrawCanvas canvas){
        wallCollision();
        //Repaint only the affected areas, not the entire JFrame for efficiency
        canvas.repaint(x,y, Settings.PLAYER_WIDTH,Settings.PLAYER_HEIGHT);
        canvas.repaint(super.x,super.y, Settings.PLAYER_WIDTH,Settings.PLAYER_HEIGHT);
    }

    private void wallCollision(){
        if(super.y > Settings.CANVAS_HEIGHT-Settings.PLAYER_HEIGHT)
            super.y = Settings.CANVAS_HEIGHT-Settings.PLAYER_HEIGHT;
        if(super.y < 0)
            super.y = 0;
        if(super.x>Settings.CANVAS_WIDTH-Settings.PLAYER_WIDTH)
            super.x=Settings.CANVAS_WIDTH-Settings.PLAYER_WIDTH;
        if(super.x < 0)
            super.x=0;
    }

}