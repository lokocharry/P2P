package Persistence;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class Folder implements Serializable {
	
	private static final long serialVersionUID = -3499576722489603317L;
	
	private boolean isFile;
	private String folderName;
	public ArrayList<Folder> subFolders = new ArrayList<Folder>();
	public ArrayList<String> files = new ArrayList<String>();
	
	public Folder(boolean isFile, String name){
		this.isFile=isFile;
		this.folderName=name;
	}
	
	public void listFilesAndFilesSubDirectories(String directoryName, Folder folder){
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList){
			if (file.isFile()){
				folder.files.add(file.getName());
			} else if (file.isDirectory()){
				Folder f=new Folder(false, file.getAbsolutePath());
				folder.subFolders.add(f);
				listFilesAndFilesSubDirectories(file.getAbsolutePath(), f);
			}
		}
	}
	
	public void printFolder(Folder folder){
		for (Folder f : folder.subFolders) {
            System.out.println(f.getFolderName());
            for (int i=0; i<f.files.size(); i++) {
				System.out.println("	->"+f.files.get(i));
			}
            printFolder(f);
        }
	}
	
	public void addNodes(Folder folder, DefaultTreeModel model, MutableTreeNode parent) {
	    if (folder.files.size() == 0) {
	        return;
	    } else {
	        addNodesToTree(folder, model, parent);
	    }
	}
	
	protected void addNodesToTree(Folder node, DefaultTreeModel tModel, MutableTreeNode parent) {
	    if (node != null) {
	        MutableTreeNode newParent = new DefaultMutableTreeNode(node.getFolderName());
	        tModel.insertNodeInto(newParent, parent, Math.max(0, parent.getChildCount() - 1));
	        for (int i = 0; i < node.files.size(); i++) {
	        	tModel.insertNodeInto(new DefaultMutableTreeNode(node.files.get(i)), newParent, Math.max(0, newParent.getChildCount() - 1));
			}
	        for (int index = 0; index < node.subFolders.size(); index++) {
	            Folder child = node.subFolders.get(index);
	            addNodes(child, tModel, newParent);
	        }
	    }
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
}
