package Test;
import java.io.IOException;

import Logic.Server;


public class TestServer {

	public static void main(String[] args) {
		Server s=null;
		try {
			s = new Server(1125);
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.start();
	}
}
