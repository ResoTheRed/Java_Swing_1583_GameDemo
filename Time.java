

public class Time{

    private double lastUpdateTime; 
    private int lastSecondTime;
    private double now;
    private int seconds;
    private int frames;

    public Time(){
        lastUpdateTime = System.nanoTime();
        lastSecondTime = (int) (lastUpdateTime / Settings.NANO_SEC);
        seconds=0;
        frames=0;

    }

    //runs once every Frame
    public void updateTime(){
        now = System.nanoTime();
        //Can update game in loop 
        while( now - lastUpdateTime > Settings.TIME_BETWEEN_UPDATES){
            //increment lastUpdateTime by 0.01 seconds
            lastUpdateTime += Settings.TIME_BETWEEN_UPDATES;
        }
    }

    //Ingerpolation is points of time between frames
    public float getInterpolation(){
        //Render. To do so, we need to calculate interpolation for a smooth render. Smooth Game Physics 
        float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / Settings.TIME_BETWEEN_UPDATES) );
        return interpolation;
    }

    //Update the frame based on FPS (60 times a second)
    public void updateFrame(){
        updateTick();       
        int thisSecond = (int) (lastUpdateTime / Settings.NANO_SEC);
        if (thisSecond > lastSecondTime){//run every second
            seconds++;
            System.out.printf("Seconds Run: %d, FPS: %d\n",seconds,getTick());    
            frames=0;
            lastSecondTime = thisSecond;
        }
        pauseThread();
    }

    //Yield thread until it has been at least the target time between renders. \Prevents CPU hogging.
    private void pauseThread(){
        while ( now - lastUpdateTime < Settings.TIME_BETWEEN_UPDATES){
            Thread.yield();
            //Thread.sleep can be faulty on some systems.  not convinced this is the best solution
            try {Thread.sleep(1);} catch(Exception e) {} 
            now = System.nanoTime();
        }       
    }

    private void updateTick(){
        frames++;
    }

    public int getTick(){
        return this.frames;
    }

    public int getSeconds(){
        return this.seconds;
    }

}