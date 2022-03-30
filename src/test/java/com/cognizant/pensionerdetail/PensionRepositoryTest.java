package com.cognizant.pensionerdetail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.cognizant.pensionerdetail.filereader.CSVFileReader;
import com.cognizant.pensionerdetail.model.PensionerDetail;
import com.cognizant.pensionerdetail.repository.PensionRepositoryImpl;

import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class PensionRepositoryTest {

	@InjectMocks
	private PensionRepositoryImpl pensionRepositoryImpl;
	
	@Mock
	private Environment environment;
	
	@Mock
	private CSVFileReader read;
	
	String path = "data.csv";
	
	@BeforeEach
	void init() throws IOException {
		when(environment.getProperty("repo.message")).thenReturn(" ");
		when(read.getBufferReader()).thenReturn(new BufferedReader(new FileReader(path)));
	}
	
	@Test
	void testRepoNotNull() throws IOException, ParseException {
		List<PensionerDetail> actual = new ArrayList<>();
		actual = pensionRepositoryImpl.readAllCsvData();
		Assertions.assertNotNull(actual);
	}
}
