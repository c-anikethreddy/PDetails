package com.cognizant.pensionerdetail.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.cognizant.pensionerdetail.filereader.CSVFileReader;
import com.cognizant.pensionerdetail.model.BankDetail;
import com.cognizant.pensionerdetail.model.PensionerDetail;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 
 * 
 * Class PensionRepositoryImpl implements interface PensionRepository and 
 * invoke method that read data from file and store in list and return the list
 * Annotated with @Configuration enables auto-configuration.
 * Annotated with @PropertySource for adding property sources to the environment. 
 */

@Repository
@NoArgsConstructor
@Slf4j
@Configuration
@PropertySource("classpath:messages.properties")
public class PensionRepositoryImpl implements PensionerRepository {

	/** environment reference of Environment is autowired */
	@Autowired
	private Environment environment;
	
	/** read reference of CSVFileReader is autowired*/
	@Autowired
	private CSVFileReader read;
	
	/**
	 * method to invoke getBufferReader() from CSVFileReader class and returns List<PensionerDetail>
	 * @throws IOException
	 * @throws ParseException
	 * @return list of pensioner details
	 */
	@Override
	public List<PensionerDetail> readAllCsvData() throws IOException, ParseException {
		
		log.debug(environment.getProperty("repo.message"));
		
		/**
		 * initializing list to store all details 
		 */
		List<PensionerDetail> alldetails = new ArrayList<PensionerDetail>();

		/**
		 * reader will store data returned from getBufferReader()
		 */
		final BufferedReader reader = read.getBufferReader();

		String[] temp;
		String line;

		Date date;
		BankDetail bankDetail;
		PensionerDetail detail;

		while ((line = reader.readLine()) != null) {
			temp = line.split(",");

			date = new SimpleDateFormat("dd/MM/yyyy").parse(temp[2]);
			bankDetail = new BankDetail(temp[7], Long.parseLong(temp[8]), temp[9]);
			detail = new PensionerDetail(Long.parseLong(temp[0]), temp[1], date, temp[3], Double.parseDouble(temp[4]),
					Long.parseLong(temp[5]), temp[6], bankDetail);

			alldetails.add(detail);
		}
		reader.close();
		return alldetails;
	}

	
	
}
