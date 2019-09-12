package com.test;

import org.apache.log4j.Logger;

public class ProducerManager {
	
	final static Logger logger = Logger.getLogger(ProducerManager.class);
	
	public void createMessageAndPutOnQueue(char producerType){
		
		MessageSeries messageSeries = MessageSeries.getValueFor(producerType);
		Message message = createMessageForProducer(messageSeries);
		if(logger.isInfoEnabled()) {
		logger.info("Message Created : " + message +" :: Pushing to Queue..");
		}
		MessageQueue.pushMessage(message);
	}
	

	private Message createMessageForProducer(MessageSeries messageSeries){
		Producer producer = messageSeries.getProducer();
		Message message =  producer.createNextMessage(messageSeries);
		return message;
	}
	

}
