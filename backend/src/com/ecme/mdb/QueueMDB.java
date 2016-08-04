package com.ecme.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * Message-Driven Bean implementation class for: QueueMDB
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "MyQueue"), 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
				//,@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
		}, 
		mappedName = "MyQueue")
@TransactionManagement(TransactionManagementType.BEAN)
public class QueueMDB implements MessageListener {
	private Integer counter=0;

    /**
     * Default constructor. 
     */
    public QueueMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	synchronized (counter) {
        	counter+=1;

		}
        TextMessage msg =  (TextMessage) message;
        try {
			System.out.println("A new message to a QUEUE has arrive ("+counter+"):"+ msg.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
