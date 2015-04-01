package de.java2enterprise.onlineshop;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.JMSException;

import de.java2enterprise.onlineshop.ejb.ChatBean;
import de.java2enterprise.onlineshop.model.ChatMessage;

@Named
@RequestScoped
public class ChatController implements Serializable{
	private static final long serialVersionID =1L;
	private String room;
	private String name;
	private String text;   
	
	@EJB
	private ChatBean chatBean;
	
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void send(){
		chatBean.send(room,name,text);
	}
	
//	public List<ChatMessage> getMessages() throws JMSException{
//		return chatBean.findAll();
//	}
   

}
