package com.ccsp.claims.listeners;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ccsp.claims.constants.CommonConstants;

@Component
public class MessagePoster {

	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public MessagePoster(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Scheduled(fixedDelay = 10000L)
	public void sendMessage() {
		//final CustomMessage message = new CustomMessage("Hello there!", new Random().nextInt(50), false);
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(CommonConstants.topicName,CommonConstants.queueName, "Hello there");
	}

}
