package com.ccsp.servicedetermination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeterminationService {

	@Value("${kafka.topic.serviceDisposition}")
	private String serviceDispositionTopic;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "${kafka.topic.serviceDetermination}")
	public void serviceDetermination(String message) {
		if (validateMessage(message)) {
			kafkaTemplate.send(serviceDispositionTopic, message);
		}
	}

	public boolean validateMessage(String message) {
		if (message != null) {
			return true;
		}
		return false;
	}
}
