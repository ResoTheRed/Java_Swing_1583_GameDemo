import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class DrawCanvas extends JPanel{

    //contains all sprites to be rendered on the canvas
    private ArrayList<Sprite> sprites;

    public DrawCanvas(){
        sprites = new ArrayList<Sprite>();
    }

    public void setPlayer(Sprite sprite){
        sprites.add(sprite);
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        setBackground(Settings.WHITE);
        //render all sprites
        for(Sprite sprite : sprites)
            sprite.paint(graphics);
    }
}