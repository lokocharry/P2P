package Logic;

import Persistence.Folder;
import Persistence.Message;

public class ObjectThread implements Runnable {

private Client client;
	
	public ObjectThread(Client client){
		this.client=client;
	}

	@Override
	public void run() {
		do {
			if(client.getS().isConnected()){
				Message m=(Message)client.readObject(client.getS());
				switch (m.getMessages()) {
				case Message.TREE_SENDED:
					Folder f=(Folder)m.getObject();
					f.addNodes(f, client.getUserFrame().getModel(), client.getUserFrame().getRoot());
					client.getUserFrame().updateTree();
					break;
				case Message.SEND_TREE:
					Folder folder=new Folder(false, client.getUser().getPath());
					client.sendObject(folder, null);//pendiente
					break;
					
				case Message.CONNECT_TO://pendiente
					
					break;
				}
			}
		} while (true);
	}

}
