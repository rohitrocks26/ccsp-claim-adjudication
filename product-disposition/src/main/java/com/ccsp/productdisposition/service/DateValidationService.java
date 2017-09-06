package com.ccsp.productdisposition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DateValidationService {

	@Value("${kafka.topic.serviceDetermination}")
	private String serviceDeterminationTopic;

	@Value("${kafka.topic.productDispositionFailure}")
	private String productDispositionFailure;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "${kafka.topic.productDisposition}")
	public void productDisposition(String message) {
		if (validateDate(message)) {
			kafkaTemplate.send(serviceDeterminationTopic, message);
		} else {
			kafkaTemplate.send(productDispositionFailure, message);
		}
	}

	public boolean validateDate(String message) {
		if (message != null) {
			return true;
		}
		return false;
	}
}
