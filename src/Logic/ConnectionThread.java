package Logic;

import java.net.Socket;

import Persistence.Message;

public class ConnectionThread implements Runnable {
	
	private Server ss;
	
	public ConnectionThread(Server ss){
		this.ss=ss;
	}

	@Override
	public void run() {
		do {
			Socket c=ss.accepConection();
			String user=c.getInetAddress()+" "+ss.readMessages(c);
			if(ss.findUser(user)==false)
				ss.writeFile("files/users.txt", user);
			else
				System.out.println("Usuario ya existe");
//			System.out.println("Enviando objeto");
//			Folder f=new Folder(false, "C:/Python27");
//			f.listFilesAndFilesSubDirectories(f.getFolderName(), f);
//			Message m=new Message(Message.TREE_SENDED, f);
//			ss.sendObject(m, c);
//			System.out.println("Objeto enviado");
			
			for (int i = 0; i < ss.getClients().size(); i++) {
				Message m=new Message(Message.GET_CATEGORY, "");
				ss.sendObject(m, ss.getClients().get(i));
				String category=ss.readMessages(ss.getClients().get(i));
				if(user.split(" ")[3].equals(category)){
					Message m1=new Message(Message.CONNECT_TO, ss.getClients().get(i).getInetAddress());
					ss.sendObject(m1, ss.getClients().get(i));
				}
			}
			
		} while (true);
	}

}
