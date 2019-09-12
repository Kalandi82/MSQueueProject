package com.test;

import com.test.MessageQueue;
import com.test.MessageSeries;
import com.test.ProducerManager;
import com.test.ResultSet;

public class ClientMain {

	
	public static void main(String[] args) {
		Runnable ra = createRunnableClientFor('A',10);
		Thread aThread = new Thread(ra,"THREAD-A");
		
		Runnable rb = createRunnableClientFor('B',50);
		Thread bThread = new Thread(rb,"THREAD-B");
		
		Runnable rc = createRunnableClientFor('C',75);
		Thread cThread = new Thread(rc,"THREAD-C");
		
		Runnable rd = createRunnableClientFor('D',100);
		Thread dThread = new Thread(rd,"THREAD-D");
		
		aThread.start();
		dThread.start();
		cThread.start();
		bThread.start();
		MessageQueue.sendToDispatcher();
		try {
			aThread.join();
			bThread.join();
			cThread.join();
			dThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MessageQueue.sendToDispatcher();
		ResultSet rs = MessageSeries.getResultSet();
		System.out.println("OUTPUT : \n"+rs.getAllList().toString());
	}

	private static Runnable createRunnableClientFor(char producerType, int delayMultiplier) {
		ProducerManager pm = new ProducerManager();
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for(int i=1;i<=5;i++) {
					try {
						Thread.sleep(10 * delayMultiplier);
					} catch (InterruptedException e) {}
					pm.createMessageAndPutOnQueue(producerType);
				}
				
			}
			
		};
		return r;
	}

}
