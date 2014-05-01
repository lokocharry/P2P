import java.io.IOException;


public class TestServer {

	public static void main(String[] args) {
		Server s=null;
		try {
			s = new Server(1125);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(s.readMessages(s.connectTo("", 0)));
	}
}
