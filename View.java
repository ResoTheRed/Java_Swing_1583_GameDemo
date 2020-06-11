import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interfaces
import javax.swing.*;    // Using Swing's components and containers


public class View extends JFrame{

    private DrawCanvas canvas;
    private Player player;
    private Enemy enemy;
    //private boolean isRunning;

    public View(){
        setupPlayer();
        setupEnemy();
        setupCanvas();
        addKeyListener(new TAdapter());
        //playerMovementListener();
        setupJFrame();
    }

    public void update(){
        player.updateSprite(canvas);
        enemy.updateSprite(canvas);
        trackEnemy();
    }

    private void setupPlayer(){
        //construct player given x,y,hieght,width
        player = new Player(Settings.PLAYER_START_X,Settings.PLAYER_START_Y,
                            Settings.PLAYER_HEIGHT,Settings.PLAYER_WIDTH);
    }

    private void setupEnemy(){
        if(enemy==null)
            enemy = Enemy.generateEnemy();
        else
            enemy.setCoord(Enemy.generateCoord());
        enemy.setPlayerCoord(player.getX(), player.getY());
        enemy.setVelocity();
    }

    private void trackEnemy(){
        if(enemy.getX()>Settings.ENEMY_OUT_X||enemy.getX()<-Settings.ENEMY_OUT){
            setupEnemy();
        }else if(enemy.getY()>Settings.ENEMY_OUT_Y||enemy.getY()<-Settings.ENEMY_OUT){
            setupEnemy();
        }
    }

    private void setupCanvas(){
        //Set up the drawing Canvas (JPanel)
        canvas = new DrawCanvas();
        canvas.addSprite(this.player);
        canvas.addSprite(this.enemy);
        canvas.setPreferredSize(new Dimension(Settings.CANVAS_WIDTH,Settings.CANVAS_HEIGHT));
    
    }

    private void setupJFrame(){
        //Add panels to this JFrame
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(canvas, BorderLayout.CENTER);
        //Setup JFrame Specs
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("A Game in Java Swing!");
        pack();             //Pack all of the components in the JFrame
        setVisible(true);   //Render the JFrame
        requestFocus();     // "super" JFrame request focus to recieve KeyEvent
    }
    
    //alter to change delta instead of move rate
    private void playerMovementListener(){
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent event){
                switch(event.getKeyCode()){
                    case KeyEvent.VK_LEFT:  player.move("Left",canvas); break;
                    case KeyEvent.VK_RIGHT: player.move("Right",canvas);break;
                    case KeyEvent.VK_UP:    player.move("Up",canvas);   break;
                    case KeyEvent.VK_DOWN:  player.move("Down",canvas); break;
                }
            }
        });
    }











   
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }

}