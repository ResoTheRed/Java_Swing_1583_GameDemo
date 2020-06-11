

public class Model{

    private Time time;
    private View view;    

    public Model(){
        time = new Time();
    }

    //Starts a new thread and runs the game loop in it.
    public void runGame(){
        Thread loop = new Thread(){
            public void run(){
                gameLoop();
            }
        };
        loop.start();
    }
    
    public void gameLoop(){
        view = new View();
        boolean isRunning = true;
        int count = 0;
        while(isRunning){    
            time.updateTime();
            view.update();
            time.updateFrame();
        }    
    }

}