package com.ccsp.accums.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ccsp.accums.repo.LeadScoreRepo;

@Service
public class AccumsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccumsService.class);

	@Autowired
    private LeadScoreRepo leadScoreRepo;

    @KafkaListener(topics = "${kafka.topic.calculateAccums}")
    public void onLeadCreatedCalculateScore(String payload) {
    	LOGGER.info("received payload='{}' , now storing it in LeadScore Table!", payload);
    	final int initialScore = calculateInitialScoreOfLead(payload);
        //leadScoreRepo.save(new LeadScore(UUID.fromString(payload), initialScore));
        LOGGER.info("LeadScore saved successfully in Table!");
    }

    private final int calculateInitialScoreOfLead(String leadId) {
        return 10;
    }

}
