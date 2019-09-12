package com.test;

import org.apache.log4j.Logger;

public class Consumer {
	
	final static Logger logger = Logger.getLogger(Consumer.class);
	private static final String PROCESSED = "processed";
	

	public void processMessage(Message message) {
		String rawMessage = message.getMessage();
		message.setMessage(rawMessage.concat(PROCESSED));
		if(logger.isInfoEnabled()) {
		logger.info("Processed message : "+message);
		}
	}
	
	

}
