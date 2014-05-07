package Persistence;

import java.io.Serializable;

public class Message implements Serializable {
	
	public final static String CONNECT_TO="connect_to";
	public final static String TREE_SENDED="tree_sended";
	public final static String SEND_TREE="send_tree";
	public final static String GET_CATEGORY="get_category";

	private static final long serialVersionUID = 1L;
	
	private String messages;
	private Object object;
	
	public Message(String message, Object object){
		this.messages=message;
		this.object=object;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
