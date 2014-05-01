package Logic;

import java.net.Socket;

public class ConnectionThread implements Runnable {
	
	private Server ss;
	
	public ConnectionThread(Server ss){
		this.ss=ss;
	}

	@Override
	public void run() {
		do {
			Socket c=ss.connectTo("", 0);
			ss.writeFile("files/users.txt", c.getInetAddress()+" "+ss.readMessages(c));
		} while (true);
	}

}
