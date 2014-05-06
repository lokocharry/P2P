package Test;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Logic.Client;
import Persistence.Folder;


public class TestClient {
	
	public static void main(String[] args) {
		Client c=null;
		try {
			c = new Client(2234, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.connectTo(c.getIp(), c.getPort());
		c.sendMessage(c.getS(), "lokocharry");
		System.out.println("Leyendo objeto");
		Folder f=(Folder) c.readObject(c.getS());
		System.out.println("objeto leido, creando ventana");
		f.printFolder(f);
		JFrame frame=new JFrame();
		DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("Usuario");
		DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
		JTree tree=new JTree(modelo);
		f.addNodes(f, modelo, abuelo);
		frame.add(new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		frame.setVisible(true);
	}

}
