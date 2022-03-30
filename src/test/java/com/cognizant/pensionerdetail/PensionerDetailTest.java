package com.cognizant.pensionerdetail;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.cognizant.pensionerdetail.exception.InvalidAadhaarException;
import com.cognizant.pensionerdetail.model.BankDetail;
import com.cognizant.pensionerdetail.model.PensionerDetail;
import com.cognizant.pensionerdetail.repository.PensionerRepository;
import com.cognizant.pensionerdetail.service.PensionerDetailServiceImpl;

@SpringBootTest
public class PensionerDetailTest {

	@InjectMocks
	private PensionerDetailServiceImpl pensionerDetailServiceImpl;

	@Mock
	private PensionerRepository pensionerRepository;
	
	@Mock
	private Environment environment;

	private PensionerDetail pensionerDetail1;
	private PensionerDetail pensionerDetail2;

	@BeforeEach
	void init() throws IOException, ParseException {
		List<PensionerDetail> expected = new ArrayList<>();
		BankDetail bankDetail = new BankDetail("HDFC", 12345678, "public");
		Date date = new Date();
		pensionerDetail1 = new PensionerDetail(123456789, "murali", date, "BNM12345", Double.parseDouble("50000"),
				Long.parseLong("500"), "family", bankDetail);
		pensionerDetail2 = new PensionerDetail(456732198, "raj", date, "HJR34569", Double.parseDouble("80000"),
				Long.parseLong("550"), "self", bankDetail);
		
		expected.add(pensionerDetail1);
		expected.add(pensionerDetail2);
		when(pensionerRepository.readAllCsvData()).thenReturn(expected);
		when(environment.getProperty("service.message")).thenReturn("in the findDatabyAadhar method");
	}
	@Test
	public void testNotNullServiceImpl() throws Exception {
		assertNotNull(pensionerDetailServiceImpl);
	}

	@Test
	public void testNotNullRepository() throws Exception {
		assertNotNull(pensionerRepository);	
	}
	
	@Test
	void testfindDataByCorrectAadhar() throws Exception {

		long aadhaarNumber = 123456789;
		PensionerDetail actual = pensionerDetailServiceImpl.findDataByAadhaar(aadhaarNumber);
		Assertions.assertEquals(pensionerDetail1, actual);

	}
	
	@Test()
	void testfindDataByWrongAadhar() throws IOException, ParseException{
		
		long aadhaarNumber = 1234567;
		Assertions.assertThrows(InvalidAadhaarException.class, () -> {pensionerDetailServiceImpl.findDataByAadhaar(aadhaarNumber);});

	}
}
