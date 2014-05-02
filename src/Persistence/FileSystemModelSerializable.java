package Persistence;

import java.io.Serializable;

import Logic.FileSystemModel;

public class FileSystemModelSerializable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private FileSystemModel fsm;
	
	public FileSystemModelSerializable(FileSystemModel fsm){
		this.fsm=fsm;
	}

	public FileSystemModel getFsm() {
		return fsm;
	}

	public void setFsm(FileSystemModel fsm) {
		this.fsm = fsm;
	}
	
	

}
