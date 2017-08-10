package com.ccsp.claims.listeners;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);
	
	@Value("${kafka.topic.exitTopicName}")
	private String exitTopicName;
	
	@Autowired
	private MessageSender sender;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "${kafka.topic.parallelTopicName}")
	public void messageReceive(String payload) {
		LOGGER.info("received payload='{}' , now doing nothing!", payload);
		latch.countDown();
	}
	
	@KafkaListener(topics = "${kafka.topic.entryTopicName}")
	public void receive(String payload) {
		LOGGER.info("received payload='{}', now posting on exit topic: " +exitTopicName, payload);
		sender.send(exitTopicName, "Hi there, I am on exit topic!!");
		latch.countDown();
	}
	
	@KafkaListener(topics = "${kafka.topic.exitTopicName}")
	public void exitMessageReceive(String payload) {
		LOGGER.info("received payload from exit topic='{}', now doing nothing!", payload);
		latch.countDown();
	}
}
