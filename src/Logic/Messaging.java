package Logic;
import java.net.Socket;


public interface Messaging {
	
	public Socket connectTo(String ip, int port);
	public void desconnect();
	public void sendMessage(Socket client, String messages);
	public String readMessages(Socket client);
	public void sendObject(Object o, Socket client);
	public Object readObject(Socket client);

}
