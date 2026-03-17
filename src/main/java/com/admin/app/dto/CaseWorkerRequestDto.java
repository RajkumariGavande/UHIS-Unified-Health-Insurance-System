package com.admin.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CaseWorkerRequestDto {

	@NotBlank(message = "Full Name is required")
	@Size(min = 3, max = 50, message = "Full Name must be between 3 and 50 characters")
	private String fullName;

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must be 10 digits")
	private String mobileNo;

	@NotBlank(message = "Email is required")
	@Email(message = "Enter a valid email address")
	private String email;

	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	@NotBlank(message = "Birth date is required")
	private String birthDate;

	@NotBlank(message = "Gender is required")
	private String gender;

	@NotNull(message = "SSN is required")
	private Long SSN;

	// Getters and Setters

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getSSN() {
		return SSN;
	}

	public void setSSN(Long sSN) {
		SSN = sSN;
	}
}