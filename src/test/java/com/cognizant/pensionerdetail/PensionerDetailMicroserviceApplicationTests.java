package com.cognizant.pensionerdetail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.pensionerdetail.model.BankDetail;
import com.cognizant.pensionerdetail.model.PensionerDetail;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
class PensionerDetailApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testBankDetails() {
		EqualsVerifier.simple().forClass(BankDetail.class).verify();
	}
	
	@Test
	void testPensionerDeatils() {
		EqualsVerifier.simple().forClass(PensionerDetail.class).verify();
	}
	
	@Test
	void testmainMethod() {
		PensionerDetailsApplication.main(new String [] {});
	}
}
