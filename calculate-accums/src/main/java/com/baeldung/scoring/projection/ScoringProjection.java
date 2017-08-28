package com.baeldung.scoring.projection;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.baeldung.scoring.persistence.model.LeadScore;
import com.baeldung.scoring.persistence.repo.LeadScoreRepo;
import com.baeldung.store.events.LeadClickedOnPromotionalLink;

@Service
public class ScoringProjection {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScoringProjection.class);

	@Autowired
    private LeadScoreRepo leadScoreRepo;

    //@EventListener
    @KafkaListener(topics = "${kafka.topic.leadTopic}")
    public void onLeadCreatedCalculateScore(String payload) {
    	LOGGER.info("received payload='{}' , now storing it in LeadScore Table!", payload);
    	final int initialScore = calculateInitialScoreOfLead(payload);
        leadScoreRepo.save(new LeadScore(UUID.fromString(payload), initialScore));
        LOGGER.info("LeadScore saved successfully in Table!");
    }

    //@EventListener
    @KafkaListener(topics = "${kafka.topic.linkTopic}")
    public void onLeadClickedOnPromotionalLinkReCalculateScore(final LeadClickedOnPromotionalLink event) {
        final LeadScore existingScore = leadScoreRepo.findOneByLeadId(event.getIdOfLead());
        final int newScore = recalculateScoreOfLead(event, existingScore.getScore());
        existingScore.setScore(newScore);

        leadScoreRepo.save(existingScore);
    }

    private final int calculateInitialScoreOfLead(String leadId) {
        return 10;
    }

    private final int recalculateScoreOfLead(final LeadClickedOnPromotionalLink event, final int oldScore) {
        return oldScore + 1;
    }

}
