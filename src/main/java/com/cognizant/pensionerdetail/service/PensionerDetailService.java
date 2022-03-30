package com.cognizant.pensionerdetail.service;

import java.io.IOException;
import java.text.ParseException;
import com.cognizant.pensionerdetail.exception.InvalidAadhaarException;
import com.cognizant.pensionerdetail.model.PensionerDetail;

/**
 * 
 * @author 
 * Interface PensionerDetailService has method to find details for pensioner on basis of their aadhaar
 *
 */
public interface PensionerDetailService {


	/**
	 * findDataByAadhaar() will find and return pensioner details from array list on basis of their aadhaar number
	 * @param aadhaar
	 * @return PensionerDetail
	 * @throws IOException
	 * @throws ParseException
	 * @throws InvalidAadhaarException
	 */
	public PensionerDetail findDataByAadhaar(Long aadhaar) throws IOException, ParseException, InvalidAadhaarException;

}
