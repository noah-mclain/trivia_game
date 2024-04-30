package triviagamee;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class ParallelProcessing implements Runnable{
    //we used Thread to we can run concurrently with other threads in the java program, 
    //basically to handle client connection on a seperate thread
    //start and run are a part of the thread class in java 

    private Socket s;//this is client socket that will be parallel processed
    SimpleDateFormat date_time = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
    private static final int buffer_size = 1024; //buffer for reading data from client 

    public ParallelProcessing(Socket soc, int myNumber) throws IOException{
        s = soc;
        new Thread(this).start();
        //this allocates memory and initializes various internal variables, then calls run
        //this starts the new thread, and when a thread is started, its run method is called
    }

    public void run(){
        //run is where i define the task that the thread is supposed to do 
        //i need both of these methods to utilize javas multithreading
       
        try(InputStream in = new BufferedInputStream(s.getInputStream())){
            //the try-with-resource ensure the resource is closed at the end of the statement
            byte[] buffer = new byte[buffer_size];
            int bytesRead;
            
            while((bytesRead = in.read(buffer)) != -1){
                //if the end of the input stream is reached, in.read() returns -1 and the loop ends
                String message = new String(buffer, 0, bytesRead); //converting bytes read from ckient to a string 
                System.out.println(message);
            }
        }catch(EOFException e){
            //end of file exception
            System.out.println("Reached end of stream unexpectedly: "+ e.getMessage());
        }catch(IOException e){
            System.out.println("Error reading data frm client: " + e.getMessage());

        }finally{
            try{
                s.close(); //close socket in finally block to make sure its always clsoed yay
            } catch(IOException e){
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }

    }
}
