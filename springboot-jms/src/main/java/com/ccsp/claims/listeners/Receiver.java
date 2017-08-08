package com.ccsp.claims.listeners;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ccsp.claims.constants.CommonConstants;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);

	@RabbitListener(queues = CommonConstants.queueName)
	public void receiveQueueMessage(Message message) {
		System.out.println("Received Queue Mesage :: <" + message + ">");
		latch.countDown();
	}
	
	/*@RabbitListener(topic = Application.topicName)
	public void receiveTopicMessage(String message) {
		System.out.println("Received Topic Mesage :: <" + message + ">");
		//latch.countDown();
	}*/

	public CountDownLatch getLatch() {
		return latch;
	}
}
