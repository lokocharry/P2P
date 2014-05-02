package Logic;

import java.io.File;
import java.net.Socket;

import Persistence.FileSystemModelSerializable;

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
			ss.sendObject(new FileSystemModelSerializable(new FileSystemModel(new File("C:/Program Files/Adobe/Flash Player"))), c);
			System.out.println("Objeto enviado");
		} while (true);
	}

}
