import javax.swing.SwingUtilities;

public class Main{

    public static void main(String[] args){
        //Run GUI construction on the Event-Dispatching Thread for thread safety
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run() {
                new View();     //The constructor will run the job
                
            }
        });
    }

}