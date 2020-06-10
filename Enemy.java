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


    public Enemy(int x, int y, int width, int height){
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
        URL imgURL = getClass().getClassLoader().getResource(Settings.ENEMY_FILE_NAME);
        if(imgURL != null){
            iconPlayer = new ImageIcon(imgURL);
        }else{
            System.err.println("Couldn't find file: "+ Settings.ENEMY_FILE_NAME);
        }
        super.image = iconPlayer.getImage();
    }



}