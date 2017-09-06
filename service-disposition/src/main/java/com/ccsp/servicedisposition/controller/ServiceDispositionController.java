package com.ccsp.servicedisposition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ccsp.servicedisposition.service.ServiceDispositionService;

@RestController
@RequestMapping("/")
public class ServiceDispositionController {
	
	@Autowired
	private ServiceDispositionService benefitService;
	
	@Value("${accums.url}")
	private String url;
	
    @RequestMapping(method = RequestMethod.POST, value = "/disposeService")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean validateMessage(@RequestBody String message) {
       if(benefitService.validateMessage(message)){
    	   ResponseEntity<Boolean> response = new RestTemplate().postForEntity(url, message, Boolean.class);
			return response.getBody();
		}
		return false;
    }
}
