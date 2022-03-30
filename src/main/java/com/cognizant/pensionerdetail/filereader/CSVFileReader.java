package com.cognizant.pensionerdetail.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
* 
* @author 
* Class CSVFileReader is used to read data using BufferedReader from CSV File
* Annotated with @Configuration enables auto-configuration.
* Annotated with @PropertySource for adding property sources to the environment.
*
*/
@Configuration
@PropertySource("classpath:messages.properties")
public class CSVFileReader {

	/**
	 * Environment is autowired using @Autowired annotation
	 * To read from messages.properties file
	 */
	@Autowired
	private Environment env;

	/**
	 *  Function to read data from CSV file
	 *   
	 */
	public BufferedReader getBufferReader() throws IOException {
		final String path = env.getProperty("file.data");
		
		URL url = new URL(path);
		return new BufferedReader(new InputStreamReader(url.openStream()));
		//return new BufferedReader(new FileReader(path));
		
	}
}

