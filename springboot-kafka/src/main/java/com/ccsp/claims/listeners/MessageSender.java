package com.ccsp.claims.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);
	
	@Value("${kafka.topic.entryTopicName}")
	private String entryTopicName;
	
	@Value("${kafka.topic.parallelTopicName}")
	private String parallelTopicName;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Scheduled(fixedDelay = 600000L)
	public void sendMessage(){
		send(entryTopicName, "Hello Spring Kafka");
		send(parallelTopicName, "Parallely placed message on Kafka");
	}
	
	public void send(String topic, String payload) {
		LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
		kafkaTemplate.send(topic, payload);
	}
	
}
