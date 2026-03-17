package com.admin.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.admin.app.enums.PlanStatus;

@Entity
@Table(name = "Plans_Dtls")
public class Plans {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long plan_id;

	private String name;

	private String category;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	@Enumerated(EnumType.STRING)
	private PlanStatus status;
	
	public Plans() {
		// TODO Auto-generated constructor stub
	}

	public Plans(Long plan_id, String name, String category, LocalDateTime startDate, LocalDateTime endDate,
			PlanStatus status) {
		super();
		this.plan_id = plan_id;
		this.name = name;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Long getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(Long plan_id) {
		this.plan_id = plan_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public PlanStatus getStatus() {
		return status;
	}

	public void setStatus(PlanStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Plans [plan_id=" + plan_id + ", name=" + name + ", category=" + category + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", status=" + status + "]";
	}

}
