package Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Persistence.User;
import Presentation.UserFrame;


public class Client extends ServerSocket implements Messaging {
	
	private OutputStream out;
	private DataOutputStream outStream;
	private Socket s;
	private User user;
	private int port;
	private String ip;
	private ObjectThread objectThread;
	
	private UserFrame userFrame;

	public Client(int port, UserFrame userFrame) throws IOException {
		super(port);
		this.userFrame=userFrame;
		user=new User();
		s=new Socket();
		this.port=port;
		objectThread=new ObjectThread(this);
		new Thread(objectThread).start();
		
	}

	@Override
	public Socket connectTo(String ip, int port) {
		try {
			System.out.println("Conectando");
			s.connect(new InetSocketAddress(ip, port));
			System.out.println("Conexi�n exitosa");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void desconnect() {
		
	}

	@Override
	public void sendMessage(Socket client, String messages) {
		try {
			out=client.getOutputStream();
			outStream=new DataOutputStream(out);
			outStream.writeUTF(messages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String readMessages(Socket client) {
		try{
			InputStream input = client.getInputStream();
			DataInputStream inputStream = new DataInputStream(input);
			return inputStream.readUTF();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public Socket getS() {
		return s;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPort() {
		return port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public UserFrame getUserFrame() {
		return userFrame;
	}

	@Override
	public void sendObject(Object o, Socket client) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(o);
            out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object readObject(Socket client) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(client.getInputStream());
			return in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
