package Persistence;

import java.io.File;
import java.io.Serializable;

public class FileSerializable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private File file;
	
	public FileSerializable(File file){
		this.file=file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
