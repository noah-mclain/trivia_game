package triviagamee;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
/*this code represents a client that connects to a server, sends data to it, and recieves data from it */
public class Client {
    private static String hostIP = "";
    private static int port = 8000; //the port that the client should connect to 
    private static Socket socket; // this handles the connection
    private ClientListener myListener;//to receieve events from thte client
    private BufferedInputStream in; //store input stream written to socket
    private PrintStream out; //store output stream written to socket
    private boolean connected = false;

    //diff constructors for client for diff parameters
    public Client(ClientListener listener){
        port = 23;
        myListener = listener;
    }
    public Client(ClientListener listener, String HostIP){
        myListener = listener;
        hostIP = HostIP;
        port = 23;
    }

    public Client (ClientListener listener, String HostIP, int Port){
        myListener = listener;
        hostIP= HostIP;
        port = Port;
    }

    //setters, pretty obvious
    public void setHostIP(String HostIP){
        hostIP = HostIP;
    }

    public void setPort(int Port){
        port = Port;
    }

    public void connect(){
        initiateConnection();
    }

    public void connect(String HostIP) {
        // Connect to the host
        hostIP = HostIP;
        initiateConnection();
        }
    
        public void connect(String HostIP, int Port) {
        // Connect to the host
        hostIP = HostIP;
        port = Port;
        initiateConnection();
        }
    
        public void disconnect() {
        // disconnects, closes socketand notifies user
       connected = false;
       try(Socket s= socket){
        myListener.Disconnected();
       }catch(IOException e){
        myListener.ErrorOccurred(e.getMessage());
       }
      }

      private void initiateConnection(){
            try{
                socket = new Socket(hostIP, port);
                in = new BufferedInputStream(socket.getInputStream());
                out = new PrintStream(socket.getOutputStream());

                if(myListener != null){
                    //we check if myListener object is nit null
                    Thread receiver = new Thread(new ReceiveHandler());
                    //we pass the new recieverhandler object to the constructor and assign it to the receiver variable 
                    receiver.setPriority(3);
                    receiver.start();
                }
                connected = true;
                myListener.Connected();
            }catch(UnknownHostException e){
                myListener.ErrorOccurred(e.getMessage());
            }catch(IOException e){
                myListener.ErrorOccurred(e.getMessage());
            }
      }

      public void send(String Data, boolean Newline){

        if(Newline) out.println(Data);

        else out.print(Data);
      }

      public void send(char byteToSend){
        out.print(byteToSend);
      }

      private class ReceiveHandler implements Runnable{
        public void run(){
            receiveMessage();
        }
        private void receiveMessage(){
            StringBuilder message = new StringBuilder();
            int readInt;
            while(connected){
                try{
                    if((readInt= in.read()) != -1 /*this is to block on read instead of sleeping */){
                        message.append((char) readInt);
                        myListener.DataRecieved(message.toString());
                        message.setLength(0); //to clear the string builder
                    }

                    else{
                        connected = false;
                        myListener.Disconnected();
                    }
                }catch(IOException e){
                    if(connected){
                        connected = false;
                        myListener.Disconnected();
                    }
                }
            }
        }
    }
}
