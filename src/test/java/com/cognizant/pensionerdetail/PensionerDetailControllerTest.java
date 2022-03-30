package com.cognizant.pensionerdetail;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.pensionerdetail.controller.PensionerDetailController;
import com.cognizant.pensionerdetail.exception.InvalidAadhaarException;
import com.cognizant.pensionerdetail.model.BankDetail;
import com.cognizant.pensionerdetail.model.PensionerDetail;
import com.cognizant.pensionerdetail.service.PensionerDetailService;
@SpringBootTest
@AutoConfigureMockMvc
public class PensionerDetailControllerTest {

	@InjectMocks
	private PensionerDetailController pensionerDetailController; 
	
	@Mock
    private PensionerDetailService pensionerDetailService;
	
    @Mock
    private Environment environment; 
    
    @Autowired
    MockMvc mockMvc;
    
    PensionerDetail pensionerDetail;
    long adhaarNumber;
    
    @BeforeEach
    void init() throws InvalidAadhaarException, IOException, ParseException {
    	List<PensionerDetail> expected=new ArrayList<>();
		BankDetail bankDetail=new BankDetail("HDFC",12345678, "public");
		Date date=new Date();
		pensionerDetail=new PensionerDetail(123456789,"murali", date,"BNM12345",Double.parseDouble("50000"),Long.parseLong("500"),"family", bankDetail);
		expected.add(pensionerDetail);
		adhaarNumber=123456789;
		when(environment.getProperty("controller.message")).thenReturn("in the /PensionerDetailByAadhaar");
		when(environment.getProperty("controller.return")).thenReturn("returning details of Pensioner");
		when(environment.getProperty("controller.bad")).thenReturn("bad request thrown");
    }
    @Test
	public void testNotNullService() throws Exception {
		assertNotNull(pensionerDetailService);
	}

	@Test
	public void testNotNullController() throws Exception {
		assertNotNull(pensionerDetailController);	
	}
	
    
	@Test
	void testValidDetailsControllerTest() throws InvalidAadhaarException, IOException, ParseException,Exception{
		when(pensionerDetailService.findDataByAadhaar(adhaarNumber)).thenReturn(pensionerDetail);
		mockMvc.perform(post("/pds/pensionerdetailbyaadhaar")).andReturn();
		ResponseEntity<PensionerDetail> actual= pensionerDetailController.getDetails("123456789");
		assertEquals(pensionerDetail, actual.getBody());
		
	}

	
	@Test
	void testCorrectStatusCodeTest() throws InvalidAadhaarException, IOException, ParseException,Exception{
		when(pensionerDetailService.findDataByAadhaar(adhaarNumber)).thenReturn(pensionerDetail);
		mockMvc.perform(post("/pds/pensionerdetailbyaadhaar")).andReturn();
		ResponseEntity<PensionerDetail> actual= pensionerDetailController.getDetails("123456789");
		assertEquals(HttpStatus.OK, actual.getStatusCode());
		
	}
	
	@Test
	void testInvalidDetailsControllerTest() throws InvalidAadhaarException, IOException, ParseException,Exception{
		
		when(pensionerDetailService.findDataByAadhaar((long)123456)).thenThrow(new InvalidAadhaarException());
		mockMvc.perform(post("/pds/pensionerdetailbyaadhaar")).andReturn();
		ResponseEntity<PensionerDetail> actual= pensionerDetailController.getDetails("123456");
		assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());

		
	}
	

}

