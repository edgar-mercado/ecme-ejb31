package com.ecme.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: TopicMDB
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty( propertyName = "destination", propertyValue = "MyTopic"), 
				@ActivationConfigProperty( propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"), 
				@ActivationConfigProperty( propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "MyTopic")
public class TopicMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public TopicMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	//MapMessage msg=(MapMessage) message;
    	TextMessage msg=(TextMessage) message;
        try {
        	System.out.println("A new message has arrive with SUBJECT: '"+ msg.getText());
			//System.out.print("' and CONTENT: '"+ msg.getString("Content")+"'");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
