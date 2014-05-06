package Logic;

import java.net.Socket;

import Persistence.Folder;
import Persistence.Message;

public class ConnectionThread implements Runnable {
	
	private Server ss;
	
	public ConnectionThread(Server ss){
		this.ss=ss;
	}

	@Override
	public void run() {
		do {
			Socket c=ss.connectTo("", 0);
			ss.getClients().add(c);
			String user=c.getInetAddress()+" "+ss.readMessages(c);
			if(ss.findUser(user)==false)
				ss.writeFile("files/users.txt", user);
			else
				System.out.println("Usuario ya existe");
			System.out.println("Enviando objeto");
			Folder f=new Folder(false, "C:/Python27");
			f.listFilesAndFilesSubDirectories(f.getFolderName(), f);
			Message m=new Message("Tree object", f);
			ss.sendObject(m, c);
			System.out.println("Objeto enviado");
		} while (true);
	}

}
