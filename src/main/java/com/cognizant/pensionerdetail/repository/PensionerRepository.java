package com.cognizant.pensionerdetail.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.cognizant.pensionerdetail.model.PensionerDetail;

/**
 * 
 * @author 
 * PensionRepository is an interface that has method declared to readAllCSVData
 */

public interface PensionerRepository {

	/**
	 * @return List of Pensioner Details from CSV
	 * @throws IOException
	 * @throws ParseException
	 */
	List<PensionerDetail> readAllCsvData() throws IOException, ParseException;

}
