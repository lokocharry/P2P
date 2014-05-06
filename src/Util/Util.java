package Util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;

public class Util {
	
	public static void serialize(JTree tree) {
		try {
			XMLEncoder o = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("tree1.xml")));
			o.writeObject(tree.getModel());
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JTree deSerialize(File file) {
		JTree tree=new JTree();
		try {
			XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
			tree.setModel((TreeModel) d.readObject());
			d.close();
		} catch (FileNotFoundException ex) {
		}
		return tree;
	}

}
