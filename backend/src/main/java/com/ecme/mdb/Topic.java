package com.ecme.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: Topic
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "MyTopic"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "MyTopic")
public class Topic implements MessageListener {

    /**
     * Default constructor. 
     */
    public Topic() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	MapMessage txtMsg = (MapMessage) message;
        try {
			System.out.println("Message recived from Message Driven Bean: "+txtMsg.getString("Subject"));
			System.out.println("Message recived from Message Driven Bean: "+txtMsg.getString("Content"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
