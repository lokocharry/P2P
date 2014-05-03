package Logic;

import java.io.File;
import java.net.Socket;

import Persistence.FileSerializable;

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
			System.out.println("Enviando objeto");
			FileSerializable fs=new FileSerializable(new File("C:/Program Files"));
			ss.sendObject(fs, c);
			System.out.println("Objeto enviado");
		} while (true);
	}

}
