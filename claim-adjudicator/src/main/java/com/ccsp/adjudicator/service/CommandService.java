package com.ccsp.adjudicator.service;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ccsp.adjudicator.commands.CreateLead;
import com.ccsp.event.store.events.LeadCreated;
import com.ccsp.event.store.service.EventStore;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CommandService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandService.class);

	@Autowired
	private EventStore eventStore;

	@Value("${kafka.topic.productDisposition}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "${kafka.topic.claimAdjudicator}")
	public void processClaims(String message) {
		CreateLead createLeadCommand;
		try {
			createLeadCommand = new ObjectMapper().readValue(message, CreateLead.class);
			storeClaim(createLeadCommand);
			send(topicName,message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void storeClaim(CreateLead createLeadCommand) {
		final LeadCreated event = new LeadCreated(UUID.randomUUID(), UUID.randomUUID(), createLeadCommand.getName());
		eventStore.save(event);
	}

	public void send(String topic, String payload) {
		LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
		kafkaTemplate.send(topic, payload);
	}

}