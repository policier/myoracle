package de.java2enterprise.onlineshop.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.java2enterprise.onlineshop.model.ChatMessage;

//@Stateless
//@LocalBean
public class ChatBean {
//	@Inject
//	@JMSConnectionFactory("jms/ChatTopicConnectionFactory")
//    private JMSContext context;
//	
//	@Resource(lookup="jms/ChatTopic")
//	Topic destination;
//	
//	@PersistenceContext
//	EntityManager em;
	
	public void send(String room,String name, String text){
//		try {
//			JMSProducer producer = context.createProducer();
//			TextMessage textMessage = context.createTextMessage();
//			textMessage.setStringProperty("ROOM", room);
//			textMessage.setStringProperty("Name", name);
//			textMessage.setText(text);
//			producer.send(destination, textMessage);
//		} catch (JMSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
//	public List<ChatMessage> findAll(){
////		TypedQuery<ChatMessage> query = em.createQuery("select m from ChatMessage m", ChatMessage.class);
//		return query.getResultList();
//		
//	}
	
	

}
