package com.cognizant.pensionerdetail.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cognizant.pensionerdetail.exception.InvalidAadhaarException;
import com.cognizant.pensionerdetail.model.PensionerDetail;
import com.cognizant.pensionerdetail.repository.PensionerRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 
 *	Service class that will define findDataByAadhaar() and return Pensioner Details
 * Implements PensionerDetailService
 */

@Slf4j
@Service
@PropertySource("classpath:messages.properties")
public class PensionerDetailServiceImpl implements PensionerDetailService {


	/** pensionRepository reference of PensionRepository is Autowired*/
	@Autowired
	private PensionerRepository pensionRepository;
	
	/** environment reference of Environment is autowired */
	@Autowired
	private Environment environment;

	/**
	 *  function to extract details of pensioner from list on basis of aadhaar and return
	 *  @param aadhaar
	 */
	@Override
	public PensionerDetail findDataByAadhaar(Long aadhaar) throws IOException, ParseException, InvalidAadhaarException {
		log.info("START :: Method :: findDataByAadhaar() :: ");
		log.debug(environment.getProperty("service.message"));
		final List<PensionerDetail> allDetails = pensionRepository.readAllCsvData();
		PensionerDetail details = null;
		for (PensionerDetail pensionerDetail : allDetails) {
			
			if (pensionerDetail.getAadharNumber() == aadhaar) {
				
				details = pensionerDetail;
			}
		}
		
		if (details == null) {
			
			throw new InvalidAadhaarException();
		}
		log.info("END :: Method :: findDataByAadhaar() :: ");
		return details;

	}


}
