package com.ecme.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: Queue
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "MyQueue"), 
							 @ActivationConfigProperty( propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "MyQueue")
public class Queue implements MessageListener {

    /**
     * Default constructor. 
     */
    public Queue() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	TextMessage txtMsg = (TextMessage) message;
        try {
    	   System.out.println("Message recived from Message Driven Bean MyQueue: "+txtMsg.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
