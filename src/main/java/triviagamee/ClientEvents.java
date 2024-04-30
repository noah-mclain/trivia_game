package triviagamee;

public class ClientEvents implements ClientListener {
        public void DataRecieved(String Data){
            System.out.println(Data);
        }
        public void Disconnected(){
            System.out.println("Disconnected...");
            System.exit(0);
        }
    
        public void Connected(){
            System.out.println("Connected...");
        }
    
        public void ErrorOccured(String ErrorDesc){
            
        }

        public void ErrorOccurred(String Errordesc) {
            throw new UnsupportedOperationException("Unimplemented method 'ErrorOccurred'");
        }
    }
