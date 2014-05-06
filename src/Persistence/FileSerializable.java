package Persistence;

import java.io.Serializable;

import Presentation.FileTreeFrame;

public class FileSerializable implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private FileTreeFrame ftf;
    
    public FileSerializable(FileTreeFrame ftf){
        this.ftf=ftf;
    }

	public FileTreeFrame getFtf() {
		return ftf;
	}

	public void setFtf(FileTreeFrame ftf) {
		this.ftf = ftf;
	}

    

}
