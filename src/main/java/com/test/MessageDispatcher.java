package com.test;

import org.apache.log4j.Logger;

public class MessageDispatcher {
	
	final static Logger logger = Logger.getLogger(MessageDispatcher.class);
	private static Consumer consumer1 = new Consumer();
	private static Consumer consumer2 = new Consumer();
	
	public static Consumer getConsumer1() {
		return consumer1;
	}


	public static Consumer getConsumer2() {
		return consumer2;
	}
	
	
	public void dispatch(Message message) {
		Consumer consumer = message.getMessageSeries().getConsumer();
		if(logger.isInfoEnabled()) {
			logger.info("Dispatched message : "+message+" to :: " + (consumer == consumer1 ? "Consumer-1" : "Consumer-2"));
		}
		consumer.processMessage(message);
		pushToResultset(message);
		
	}
	
	public void pushToResultset(Message message) {
		ResultSet rs = MessageSeries.getResultSet();
		rs.add(message);
	}

}
