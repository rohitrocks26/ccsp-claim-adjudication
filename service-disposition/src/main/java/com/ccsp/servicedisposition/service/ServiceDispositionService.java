package com.ccsp.servicedisposition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceDispositionService {

	@Value("${kafka.topic.calculateAccums}")
	private String accumsTopic;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "${kafka.topic.serviceDisposition}")
	public void serviceDetermination(String message) {
		if (validateMessage(message)) {
			kafkaTemplate.send(accumsTopic, message);
		}
	}

	public boolean validateMessage(String message) {
		if (message != null) {
			return true;
		}
		return false;
	}
}
