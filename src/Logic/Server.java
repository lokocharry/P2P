package Logic;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends ServerSocket implements Messaging {

	private OutputStream out;
	private DataOutputStream outStream;
	private ArrayList<Socket> clients;
	
	private Thread thread;

	public Server(int port) throws IOException {
		super(port);
		clients=new ArrayList<>();
		thread=new Thread(new ConnectionThread(this));
	}
	
	public void start(){
		thread.start();
	}

	public ArrayList<Socket> getClients() {
		return clients;
	}
	
	public Socket accepConection(){
		Socket client=null;
		try {
			client=this.accept();
			clients.add(client);
			System.out.println("Cliente conectado, ip: "+client.getInetAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public Socket connectTo(String ip, int port) {
		return null;
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
	
//	public void loadFile(String path){
//		BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader(path));
//			String line;
//			while ((line = br.readLine()) != null) {
//				String [] aux=line.split(" ");
//				users.put(aux[0], aux[1]);
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void writeFile(String path, String line){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
		    out.println(line+"\n");
		}catch (IOException e) {
		    e.printStackTrace();
		}
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
	
	public boolean findUser(String user){
		File file = new File("files/users.txt");
		boolean result=false;
		try {
		    @SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
		    System.out.println("Comprobando usuario");
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.equals(user)) {
		            result=true;
		        }
		    }
		    System.out.println("Usuario comprobado");
		} catch(FileNotFoundException e) { 
		    e.printStackTrace();
		}
		return result;
	}
	

}
