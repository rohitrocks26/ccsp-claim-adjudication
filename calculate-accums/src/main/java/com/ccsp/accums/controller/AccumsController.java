package com.ccsp.accums.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ccsp.accums.model.LeadScore;
import com.ccsp.accums.repo.LeadScoreRepo;

@RestController
@RequestMapping("/")
class AccumsController {

    @Autowired
    private LeadScoreRepo repo;
    
    @RequestMapping(method = RequestMethod.POST, value = "/calculateAccums")
	@ResponseStatus(value = HttpStatus.OK)
	public Boolean calculateAccums(@RequestBody String message) {
		if (message!=null) {
			return true;
		}
		return false;
	}

    @RequestMapping(value = "/{leadId}", method = RequestMethod.GET)
    @ResponseBody
    public LeadScore findByLeadId(@PathVariable("leadId") final UUID leadId) {
        final LeadScore resourceById = repo.findOneByLeadId(leadId);
        return resourceById;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<LeadScore> findAll() {
        return repo.findAll();
    }

}
