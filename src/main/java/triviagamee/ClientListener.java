package triviagamee;

public interface ClientListener {
    public abstract void DataRecieved(String Data);

    public abstract void Connected();

    public abstract void Disconnected();

    public abstract void ErrorOccurred(String Errordesc);

}
