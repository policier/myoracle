package de.java2enterprise.chat;

import java.util.Properties;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.jms.JMSException;

public class ChatReceiver {

	private ConnectionFactory connectionFactory;
	private Session session;
	private Destination destination;
	public static void main(String[] args) throws NamingException, javax.jms.JMSException{
		new ChatReceiver();
	}
	
	public ChatReceiver() throws NamingException, javax.jms.JMSException{
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		p.put(Context.PROVIDER_URL, "file:///C:imq_admin_objects");
		Context context = new InitialContext(p);
		connectionFactory =(ConnectionFactory) context.lookup("jms/ChatQueueConnectionFactory");
		 destination =(Destination) context.lookup("jms/ChatQueue");
		
		try(Connection connection =connectionFactory.createConnection()){
			    session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
				Scanner scanner = new Scanner(System.in);
				
				MessageConsumer consumer =session.createConsumer(destination);
				connection.start();
				
				while(true){
					Message receive = consumer.receive();
					System.out.println(((TextMessage)receive).getText());
					receive.acknowledge();
				}
		}
	}
}
