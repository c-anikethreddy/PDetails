package com.cognizant.pensionerdetail.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pensionerdetail.exception.InvalidAadhaarException;
import com.cognizant.pensionerdetail.model.PensionerDetail;
import com.cognizant.pensionerdetail.service.PensionerDetailService;
import lombok.extern.slf4j.Slf4j;

/**
 *  
 * @author Neelima, Ramya, Aniketh, Satya
 * 
 * PensionerDetailController is controller class to check for url mappings 
 * Annotated with @RestController for creating Restful controller.
 */

@RestController
@Slf4j
@PropertySource("classpath:messages.properties")
public class PensionerDetailController {

	/**
	 * Autowired to PensionDetailService Interface
	 */
	@Autowired
	private PensionerDetailService pensionerDetailService;
	
	/**
	 * Autowired Environment for fetching properties form properties file
	 */
	@Autowired
	private Environment environment;
	
	/**
	 * Fetch Pensioner Details from Service Layer and return details
	 * 
	 * @param aadhaarNumber
	 * @return PensionerDetail
	 * @throws IOException
	 * @throws ParseException
	 * @throws InvalidAadhaarException
	 */
	
	
	@PostMapping("/pensionerdetailbyaadhaar")
	public ResponseEntity<PensionerDetail> getDetails(@RequestBody final String aadhaarNumber)
			throws IOException, ParseException, InvalidAadhaarException {
		log.info("START :: Method :: getDetails() :: ");
		log.debug(environment.getProperty("controller.message"));
		PensionerDetail details = null;
		try {
			//System.out.println("pensionerdetailbyaadhaar");
			//System.out.println(aadhaarNumber);
			details = pensionerDetailService.findDataByAadhaar(Long.parseLong(aadhaarNumber.replaceAll("\\s", "")));
			
		} catch (InvalidAadhaarException exception) {
			log.debug(environment.getProperty("controller.bad"));
			return new ResponseEntity<PensionerDetail>(details, HttpStatus.BAD_REQUEST);
		}

		log.debug(environment.getProperty("controller.return"));
		log.info("END :: Method :: getDetails() :: ");
		//System.out.println(details);
		return new ResponseEntity<PensionerDetail>(details, HttpStatus.OK);
	}
}
