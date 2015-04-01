package de.java2enterprise.chat;

import java.util.Properties;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.jms.JMSException;

public class ChatSender {

	private ConnectionFactory connectionFactory;
	private Session session;
	private Destination destination;
	public static void main(String[] args) throws NamingException, javax.jms.JMSException{
		new ChatSender();
	}
	
	public ChatSender() throws NamingException, javax.jms.JMSException{
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		p.put(Context.PROVIDER_URL, "file:///C:imq_admin_objects");
		Context context = new InitialContext(p);
		connectionFactory =(ConnectionFactory) context.lookup("jms/ChatQueueConnectionFactory");
		 destination =(Destination) context.lookup("jms/ChatQueue");
		
		try(Connection connection =connectionFactory.createConnection()){
			    session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
				@SuppressWarnings("resource")
			    Scanner scanner = new Scanner(System.in);
				
				MessageProducer producer = session.createProducer(this.destination);
				
				while(true){
					String in = scanner.nextLine();
					TextMessage message = session.createTextMessage(in);
					producer.send(message);
				}
		}
	}
}
