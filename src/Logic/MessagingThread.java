package Logic;

public class MessagingThread implements Runnable {
	
	private Client client;
	
	public MessagingThread(Client client){
		this.client=client;
	}

	@Override
	public void run() {
		do {
			if(client.getS().isConnected()){
				String mess=client.readMessages(client.getS());
				System.out.println(mess);
			}
		} while (true);
	}

}
