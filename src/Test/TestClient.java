package Test;
import java.io.IOException;

import Logic.Client;
import Persistence.FileSerializable;
import Presentation.FileTreeFrame;


public class TestClient {
	
	public static void main(String[] args) {
		Client c=null;
		try {
			c = new Client(2234);
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.connectTo("192.168.0.10", 1125);
		c.sendMessage(c.getS(), "lokocharry");
		System.out.println("Leyendo objeto");
		FileSerializable fsm=(FileSerializable) c.readObject(c.getS());
		System.out.println(fsm.getFile().hashCode());
		System.out.println("objeto leido, creando ventana");
		new FileTreeFrame(fsm.getFile());
	}

}
