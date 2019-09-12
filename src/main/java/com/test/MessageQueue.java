package com.test;

import java.util.concurrent.PriorityBlockingQueue;

public class MessageQueue {
	
	private static PriorityBlockingQueue<Message> queue = new PriorityBlockingQueue<>();
	
	private static MessageDispatcher md = new MessageDispatcher();
	
	public static void pushMessage(Message m ) {
		queue.add(m);
	}
	
	public static void sendToDispatcher() {
		while(!queue.isEmpty()) {
			Message message = queue.poll();
			md.dispatch(message);
		}
	}
	

}
