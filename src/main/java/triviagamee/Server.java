package triviagamee;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
/*
 * before we start, how does a socket work:
 * 1) it is created with a socket systemcall.
 * in java we use Socket class for a client and
 * ServerSocket for a server
 * 2) a server socket is bound by a specific pory number 
 * and the server uses this port to listen for incoming client connections
 * 3)the server socket listens for incoming client connections 
 * using the listen() method
 * 4)when a client wants to connect to the server
 * it creates a client socket  and initiates connection to the server
 * 5) the server socket accepts the connection from the client, and this
 * creates a new socket on the server, distinct from the server socket
 * this socket is used for communication with the client 
 * 6) once the connection is establishe, the client and server can communicate 
 * by reading from and writing to the socket. this is typically done using input output streams
 * 7)after communication is finished, the client and server both close their respective sockets
 */
public class Server {
    static String ListenPort="8000";
    //the port number that the server will listen to for incoming connections 
    //we can change it 3ady 
    public Server(){
        //the constructor is empty bc all the work is done in the main method 
    }
    public static void main(String args[]) throws Exception{
        new Server(); //creates new instance of server class
        SimpleDateFormat date_time = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
        
        try{
            int port = Integer.parseInt(ListenPort);
            try(ServerSocket ss = new ServerSocket(port) /*this creates a server socket that listens on the part specified */){
                //used a try-with-resources statement (these only work with closeable such as inputstream, outputstream, reader, serversocket, socket, connection) 3lshan to ake sure that the serversocket ss becomes closed when i no longer need it 
                System.out.println(date_time.format(new java.util.Date())+ ": Listening on TCP port(" + ListenPort + ")"); //this indicates that the server is listening for connections 

                while(true){
                    //infinite loop for the server to continue accepting connections until it is manually stopped
                    Socket connection = ss.accept(); //waiting for client to connect and then returns a socket for communicating with the client
                    String IP = connection.getInetAddress().toString().replaceAll("/", "");//getting the IP of the client that just connected and formatting its look 
                    System.out.println(date_time.format(new java.util.Date())+ ":Incoming connection from IP (" + IP + ")");
        
                    try{new ParallelProcessing( connection, 1);} catch(Exception e){ System.out.println(e);} //handles error in client connection
                    continue;
                }
            }
        }catch(NumberFormatException e){
            System.out.println("Error: Listen port is not a valid integar.");

        }catch(IOException e){
            System.out.println("Error: could not create server socket");
        }
       
    }


    public static String[] split(String textline, char separator){
        return textline.split(Character.toString(separator));
        //will return an array of strings at each occurence of separator character 
        //if the separator does not exist in the textline then the returned array will contain only one element which is the textline itself 
    }
}

