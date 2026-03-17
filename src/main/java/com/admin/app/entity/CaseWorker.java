package com.admin.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.admin.app.enums.AccountStatus;



@Entity
@Table(name ="CaseWorker_Details")
public class CaseWorker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	private String fullName;
	
	private String mobileNo;
	
	private String email;
	
	private String password;
	
	private String birthDate;
	
	private String gender;
	
	private Long SSN;
	
	@Enumerated(EnumType.STRING)
	private AccountStatus status;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updatedDate;

	public CaseWorker() {
		// TODO Auto-generated constructor stub
	}

	public CaseWorker(Long accountId, String fullName, String mobileNo, String email, String password, String birthDate,
			String gender, Long sSN, AccountStatus status, LocalDateTime createDate, LocalDateTime updatedDate) {
		super();
		this.accountId = accountId;
		this.fullName = fullName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.gender = gender;
		SSN = sSN;
		this.status = status;
		this.createDate = createDate;
		this.updatedDate = updatedDate;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

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

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "CaseWorker [accountId=" + accountId + ", fullName=" + fullName + ", mobileNo=" + mobileNo + ", email="
				+ email + ", password=" + password + ", birthDate=" + birthDate + ", gender=" + gender + ", SSN=" + SSN
				+ ", status=" + status + ", createDate=" + createDate + ", updatedDate=" + updatedDate + "]";
	}
	
	
	
}
