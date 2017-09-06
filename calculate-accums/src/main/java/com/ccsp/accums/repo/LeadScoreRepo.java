package com.ccsp.accums.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccsp.accums.model.LeadScore;

public interface LeadScoreRepo extends JpaRepository<LeadScore, Long> {

    LeadScore findOneByLeadId(final UUID leadId);

}
