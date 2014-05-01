package Test;
import java.io.IOException;

import Logic.Client;


public class TestClient {
	
	public static void main(String[] args) {
		Client c=null;
		try {
			c = new Client(2234);
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.connectTo("localhost", 1125);
		c.sendMessage(c.getS(), "lokocharry");
	}

}
