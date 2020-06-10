import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingUtilities;

public class Main{

    

    public static void main(String[] args){
        AtomicBoolean isRunning = new AtomicBoolean(true);
        //Run GUI construction on the Event-Dispatching Thread for thread safety
        // SwingUtilities.invokeLater(new Runnable(){
        
        //     @Override
        //     public void run() {
        //         new View();     //The constructor will run the job
                
        //     }
        // });
        // Thread v = new Thread(){
        //     public void run(){
        //         new View();
        //     }
            
        // }
        
        Model model = new Model();
        model.runGame();
        // if(!view.getViewIsRunning()){
        //     System.out.println("The view window is closed.");
        // }


    }

}