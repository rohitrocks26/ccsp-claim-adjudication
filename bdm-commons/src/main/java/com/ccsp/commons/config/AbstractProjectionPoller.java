package com.ccsp.commons.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.ccsp.commons.event.BaseEvent;
import com.ccsp.commons.persistence.event.RawEvent;
import com.ccsp.store.repo.RawEventRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractProjectionPoller {

    private ObjectMapper mapper;

    private Long lastProcessedId;

    @Autowired
    private RawEventRepo rawEventRepo;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
/*
    @Scheduled(fixedRate = 60000)
    void readNewEvents() {
        rawEventRepo.findAll().stream().filter(ev -> (ev.getId() > lastProcessedId)).forEach(e -> processEvent(e));
    }*/

    private final void processEvent(final RawEvent rawEvent) {
        BaseEvent event = null;
        try {
            event = (BaseEvent) mapper.readValue(rawEvent.getPayload(), Class.forName(rawEvent.getType()));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        event.setCorrelationId(rawEvent.getCorrelationId());
        lastProcessedId = rawEvent.getId();
        eventPublisher.publishEvent(event);
    }

    @PostConstruct
    void instantiate() {
        this.mapper = new ObjectMapper();
        this.lastProcessedId = -1L;
    }

}