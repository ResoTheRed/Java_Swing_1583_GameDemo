import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interfaces
import javax.swing.*;    // Using Swing's components and containers


public class View extends JFrame implements WindowListener{

    private DrawCanvas canvas;
    private Player player;
    private boolean isRunning;

    public View(){
        setupPlayer();
        setupCanvas();
        playerMovementListener();
        setupJFrame();
    }

    private void setupPlayer(){
        //construct player given x,y,hieght,width
        player = new Player(Settings.PLAYER_START_X,Settings.PLAYER_START_Y,
                            Settings.PLAYER_HEIGHT,Settings.PLAYER_WIDTH);
    }

    private void setupCanvas(){
        //Set up the drawing Canvas (JPanel)
        canvas = new DrawCanvas();
        canvas.setPlayer(this.player);
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

        addWindowListener(this);
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

    //This event is delivered after the window has been closed as the result of a call to dispose.
    public void windowClosed(WindowEvent event){
        this.isRunning=false;
    }

    //This event is delivered when the user attempts to close the window from the window's system menu.
    public void windowClosing(WindowEvent e) {}
    //The window opened event. This event is delivered only the first time a window is made visible.
    public void windowOpened(WindowEvent e) {}
    //This event is delivered when the window has been changed from a normal to a minimized state.
    public void windowIconified(WindowEvent e) {}
    //This event is delivered when the window has been changed from a minimized to a normal state.
    public void windowDeiconified(WindowEvent e) {}
    //This event is delivered when the Window becomes the active Window. Only a Frame or a Dialog can be the active Window.
    public void windowActivated(WindowEvent e) {}
    //This event is delivered when the Window is no longer the active Window. Only a Frame or a Dialog can be the active Window.
    public void windowDeactivated(WindowEvent e) {}
    //This event is delivered when the Window becomes the focused Window, which means that the Window, or one of its subcomponents, 
    //will receive keyboard events.
    public void windowGainedFocus(WindowEvent e) {}
    //This event is delivered when a Window is no longer the focused Window, which means keyboard events will no longer be delivered
    // to the Window or any of its subcomponents.
    public void windowLostFocus(WindowEvent e) {}
    //This event is delivered when a Window's state is changed by virtue of it being iconified, maximized etc.
    public void windowStateChanged(WindowEvent e) {}

    
    public void setViewIsRunning(boolean bool){
        this.isRunning = bool;
    }

    public boolean getViewIsRunning(){
        return this.isRunning;
    }


}