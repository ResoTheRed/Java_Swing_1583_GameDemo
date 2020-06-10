

public class Model{

    private Time time;
    private View view;    

    public Model(){
        time = new Time();
    }

    /*
        have gameloop here
        have it controlled by nanosecond timer
            it needs to be in its own thread
        a. update view
        b. get input from controller
        c. change state of model
        d. return to a.
    */

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
            //time.getInterpolation();
            /*
                gameLoop Content here
            */
            view.update();
            // count++;
            // if(count%10==0)
            //     System.out.print(count%60+" ");
            time.updateFrame();
            //if(count > 1000)
            //    break;
        }    
    }

}