package com.admin.app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import com.admin.app.dto.CaseWorkerRequestDto;
import com.admin.app.dto.CaseWorkerResponseDto;
import com.admin.app.dto.PlanRequestDto;
import com.admin.app.dto.PlanResponseDto;
import com.admin.app.enums.AccountStatus;

public interface AdminService {

	CaseWorkerResponseDto createAccount(@Valid CaseWorkerRequestDto dto);

	CaseWorkerResponseDto getCaseWorkerById(Long id);

	List<CaseWorkerResponseDto> getAllCaseWorkers();

	CaseWorkerResponseDto updateCaseWorker(Long id, @Valid CaseWorkerRequestDto dto);

	void updateStatus(Long id, AccountStatus status);

	void deleteCaseWorker(Long id);
	
	//............................Plans Service......................

	PlanResponseDto createPlan(@Valid PlanRequestDto dto);

	PlanResponseDto updatePlan(Long id, PlanRequestDto dto);

	List<PlanResponseDto> getPlans();

	PlanResponseDto deletePlan(Long id);

	PlanResponseDto getPlan(Long id);

	CaseWorkerResponseDto login(String email, String password);

	

}
