package com.cognizant.pensionerdetail.model;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 
 * Entity/model class for PensionerDetail
 * USe of Lombok for default constructor,parameterized constructor and getters and setters
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class PensionerDetail {

	/**
	 * instance variables for Pensioner Detail entity class
	 */

	/** Aaadhar Number */
	private long aadharNumber;
	/** Pensioner Name */
	private String name;
	/** DOB */
	//@JsonFormat(pattern = "dd/MM/yyy")
	private Date dateOfBirth;
	/** PAN Number */
	private String pan;
	/** Salary */
	private double salaryEarned;
	/** Allowances */
	private long allowances;
	/** Pension Type */
	private String pensionType;
	/** Bank Details */
	private BankDetail bankDetail;

}
