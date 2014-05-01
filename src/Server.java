import java.io.IOException;
import java.net.ServerSocket;


public class Server implements Messaging{
	
	private ServerSocket ss;
	public final static int PORT=15430;
	
	public Server(){
		try {
			ss=new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init(){
		
	}

	@Override
	public void sendMessage(String message) {
		
	}

	@Override
	public String readMessage() {
		
		return null;
	}

}
