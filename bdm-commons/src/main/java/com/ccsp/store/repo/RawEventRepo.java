package com.ccsp.store.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccsp.commons.persistence.event.RawEvent;

public interface RawEventRepo extends JpaRepository<RawEvent, Long> {

    RawEvent findOneByCorrelationId(UUID correlationId);

    List<RawEvent> findAllByCorrelationId(UUID correlationId);

}
