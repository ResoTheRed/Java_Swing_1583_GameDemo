import java.awt.*;  // Using awt's graphics and Color functions
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.*;
import java.util.Random;

public class Enemy extends Sprite{

    private static final int X = 0;
    private static final int Y = 1;
    private static Random dice = new Random();

    public static Enemy generateEnemy(){
        int randomX =  dice.nextInt(Settings.CANVAS_WIDTH);
        int randomY = dice.nextInt(Settings.CANVAS_HEIGHT);
        int[] coord = evaluateLocation(randomX, randomY);
        return new Enemy(coord[X],coord[Y],Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
    }

    public static int[] generateCoord(){
        int randomX =  dice.nextInt(Settings.CANVAS_WIDTH);
        int randomY = dice.nextInt(Settings.CANVAS_HEIGHT);
        return evaluateLocation(randomX, randomY);
    }

    //prevent enemy from spawning off of the map
    private static int[] evaluateLocation(int x, int y){
        if(x>Settings.CANVAS_WIDTH-Settings.ENEMY_WIDTH){
            x = Settings.CANVAS_WIDTH-Settings.ENEMY_WIDTH;
        }
        if(y>Settings.CANVAS_HEIGHT-Settings.ENEMY_HEIGHT){
            y = Settings.CANVAS_HEIGHT-Settings.ENEMY_HEIGHT;
        }
        int[] temp = {x,y};
        return temp;
    }


    private int[] playerCoord;
    private int deltaX =0, deltaY = 0;
    private double acceleration = 0.0;
    
    public Enemy(int x, int y, int width, int height){
        super(x,y,width,height);
        super.bg_color = Settings.WHITE;
        playerCoord = new int[2];
        setupGraphics();
    }

    public void updateSprite(DrawCanvas canvas){
       move(canvas);
    }

    public void paint(Graphics graphics){
        graphics.setColor(super.bg_color);
        graphics.fillRect(x, y, width, height);
        graphics.drawImage(super.image,x,y,null);
    }

    public void setupGraphics(){
        ImageIcon iconPlayer = null;
        URL imgURL = getClass().getClassLoader().getResource(Settings.ENEMY_FILE_NAME);
        if(imgURL != null){
            iconPlayer = new ImageIcon(imgURL);
        }else{
            System.err.println("Couldn't find file: "+ Settings.ENEMY_FILE_NAME);
        }
        super.image = iconPlayer.getImage();
    }

    public void setPlayerCoord(int x, int y){
        this.playerCoord[Enemy.X] = x;
        this.playerCoord[Enemy.Y] = y;
    }

    public void setCoord(int[] coord){
        super.x=coord[X];
        super.y=coord[Y];
    }

    public void setVelocity(){
        if(playerCoord[X]>super.x){
            deltaX = Settings.ENEMY_SPEED;
        }else if(playerCoord[X]<super.x){
            deltaX = -Settings.ENEMY_SPEED;
        }
        if(playerCoord[Y]>super.y){
            deltaY = Settings.ENEMY_SPEED;
        }else if(playerCoord[Y]<super.y){
            deltaY = -Settings.ENEMY_SPEED;
        }
    }

    //update this method every frame
    private void move(DrawCanvas canvas){
        if (acceleration < Settings.ENEMY_ACCELERATION_CAP)
            acceleration += Settings.ENEMY_ACCELERATION;
        else
            acceleration = 0.0;
        int savedX = super.x;
        int savedY = super.y;
        super.x += deltaX + (int) acceleration;
        super.y += deltaY + (int) acceleration;
        repaint(savedX,savedY,canvas);       
    }

    private void repaint(int x, int y, DrawCanvas canvas){
        //Repaint only the affected areas, not the entire JFrame for efficiency
        canvas.repaint(x,y, Settings.ENEMY_WIDTH,Settings.ENEMY_HEIGHT);
        canvas.repaint(super.x,super.y, Settings.ENEMY_WIDTH,Settings.ENEMY_HEIGHT);
    }

    
}