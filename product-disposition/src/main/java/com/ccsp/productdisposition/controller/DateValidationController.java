package com.ccsp.productdisposition.controller;

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

import com.ccsp.productdisposition.service.DateValidationService;

@RestController
@RequestMapping("/")
public class DateValidationController {

	@Autowired
	private DateValidationService dateValidationService;

	@Value("${servicedistribution.url}")
	private String url;

	@RequestMapping(method = RequestMethod.POST, value = "/validateDate")
	@ResponseStatus(value = HttpStatus.OK)
	public Boolean validateDate(@RequestBody String message) {
		if (dateValidationService.validateDate(message)) {
			ResponseEntity<Boolean> response = new RestTemplate().postForEntity(url, message, Boolean.class);
			return response.getBody();
		}
		return false;
	}
}
