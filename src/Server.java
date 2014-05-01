import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class Server extends ServerSocket implements Messaging {

	private OutputStream out;
	private DataOutputStream outStream;
	private ArrayList<Socket> clients;
	
	private HashMap<String, String> users;

	public Server(int port) throws IOException {
		super(port);
		clients=new ArrayList<>();
	}

	@Override
	public Socket connectTo(String ip, int port) {
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
	
	public void loadFile(String path){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				String [] aux=line.split(" ");
				users.put(aux[0], aux[1]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFile(String path, String line){
		try {
			File file = new File("files/users.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(line);
			bw.close();
			System.out.println("Done"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
