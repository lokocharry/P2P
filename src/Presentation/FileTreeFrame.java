package Presentation;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import Logic.FileSystemModel;

public class FileTreeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTree fileTree;
	private FileSystemModel fileSystemModel;
	private JTextArea fileDetailsTextArea = new JTextArea();

	public FileTreeFrame(File file) {
		super("Visualizador de archivos");
		
		fileDetailsTextArea.setEditable(false);
		
		fileSystemModel = new FileSystemModel(file);
		
		fileTree = new JTree(fileSystemModel);
		fileTree.setEditable(true);
		fileTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
				File file = (File) fileTree.getLastSelectedPathComponent();
				fileDetailsTextArea.setText(getFileDetails(file));
			}
		});
		
		fileTree.getModel().getRoot();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, new JScrollPane(
				fileTree), new JScrollPane(fileDetailsTextArea));
		getContentPane().add(splitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
	}

	private String getFileDetails(File file) {
		if (file == null)
			return "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("Nombre: " + file.getName() + "\n");
		buffer.append("Ruta: " + file.getPath() + "\n");
		buffer.append("Tamaño: " + file.length() + "\n");
		return buffer.toString();
	}

	public JTree getFileTree() {
		return fileTree;
	}

//	public static void main(String args[]) {
//		new FileTreeFrame("C:/Program Files/Adobe/Flash Player");
//	}

}