package com.ccsp.adjudicator.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ccsp.adjudicator.commands.CreateLead;
import com.ccsp.adjudicator.commands.LeadClickOnPromotionalLink;
import com.ccsp.adjudicator.service.CommandService;
import com.ccsp.event.store.events.LeadClickedOnPromotionalLink;
import com.ccsp.event.store.service.EventStore;

@RestController
@RequestMapping("/commands")
public class CommandController {
	
	private static final Logger log = LoggerFactory.getLogger(CommandController.class);

	@Autowired
	private CommandService commandService;
	
	@Autowired
	private EventStore eventStore;
	
	@Value("${productdisposition.url}")
	private String url;

	@RequestMapping(value = "/processClaim", method = RequestMethod.POST)
	public Boolean processClaims(final @Valid @RequestBody CreateLead createLeadCommand) {
		log.info("Inside method processClaims");
		commandService.storeClaim(createLeadCommand);
		ResponseEntity<Boolean> response = new RestTemplate().postForEntity( url, createLeadCommand.getName() , Boolean.class );
		return response.getBody();
	}

	@RequestMapping(value = "/leads/activity", method = RequestMethod.POST)
	public void leadClickOnPromotionalLink(
			final @Valid @RequestBody LeadClickOnPromotionalLink leadClickOnPromotionalLinkCommand) {
		final UUID correllationId = UUID.randomUUID();
		final LeadClickedOnPromotionalLink event = new LeadClickedOnPromotionalLink(
				leadClickOnPromotionalLinkCommand.getIdOfLead());
		event.setCorrelationId(correllationId);
		eventStore.save(event);
	}

}