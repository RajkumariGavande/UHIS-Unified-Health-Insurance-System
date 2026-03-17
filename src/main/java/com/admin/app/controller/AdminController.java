package com.admin.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.admin.app.dto.CaseWorkerRequestDto;
import com.admin.app.dto.CaseWorkerResponseDto;
import com.admin.app.dto.PlanRequestDto;
import com.admin.app.dto.PlanResponseDto;
import com.admin.app.enums.AccountStatus;
import com.admin.app.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller", description = "APIs for managing CaseWorkers and Plans")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	private AdminService service;

	public AdminController(AdminService service) {
		this.service = service;
	}

	// ================= CaseWorker APIs =================

	@Operation(summary = "Create CaseWorker Account")
	@PostMapping("/caseworkers")
	public ResponseEntity<CaseWorkerResponseDto> createAccount(@Valid @RequestBody CaseWorkerRequestDto dto) {

		logger.info("Received request to create CaseWorker with email: {}", dto.getEmail());

		CaseWorkerResponseDto response = service.createAccount(dto);

		logger.info("CaseWorker created successfully");

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Get CaseWorker By ID")
	@GetMapping("/caseworkers/{id}")
	public ResponseEntity<CaseWorkerResponseDto> getCaseWorkerById(@PathVariable Long id) {

		logger.info("Fetching CaseWorker with id: {}", id);

		CaseWorkerResponseDto response = service.getCaseWorkerById(id);

		logger.info("CaseWorker fetched successfully with id: {}", id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get All CaseWorkers")
	@GetMapping("/caseworkers")
	public ResponseEntity<List<CaseWorkerResponseDto>> getAllCaseWorkers() {

		logger.info("Fetching all CaseWorkers");

		List<CaseWorkerResponseDto> list = service.getAllCaseWorkers();

		logger.info("Total CaseWorkers fetched: {}", list.size());

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@Operation(summary = "Update CaseWorker")
	@PutMapping("/caseworkers/{id}")
	public ResponseEntity<CaseWorkerResponseDto> updateCaseWorker(@PathVariable Long id,
			@Valid @RequestBody CaseWorkerRequestDto dto) {

		logger.info("Updating CaseWorker with id: {}", id);

		CaseWorkerResponseDto response = service.updateCaseWorker(id, dto);

		logger.info("CaseWorker updated successfully with id: {}", id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Update CaseWorker Status")
	@PatchMapping("/caseworkers/{id}/status")
	public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam AccountStatus status) {

		logger.info("Updating status for CaseWorker id: {} to {}", id, status);

		service.updateStatus(id, status);

		logger.info("CaseWorker status updated successfully");

		return ResponseEntity.ok("Status Updated Successfully");
	}

	@Operation(summary = "Delete CaseWorker")
	@DeleteMapping("/caseworkers/{id}")
	public ResponseEntity<String> deleteCaseWorker(@PathVariable Long id) {

		logger.info("Deleting CaseWorker with id: {}", id);

		service.deleteCaseWorker(id);

		logger.info("CaseWorker deleted successfully with id: {}", id);

		return ResponseEntity.ok("Case Worker Deleted Successfully");
	}

	@Operation(summary = "Login CaseWorker")
	@GetMapping("/caseworkers/email/password")
	public ResponseEntity<CaseWorkerResponseDto> login(@RequestParam String email, @RequestParam String password) {
		logger.info("CaseWorker Login  with email and password: {}",email+password);
		return new ResponseEntity<CaseWorkerResponseDto>(service.login(email, password), HttpStatus.OK);
	}

	// ================= Plans APIs =================

	@Operation(summary = "Create Plan")
	@PostMapping("/plans")
	public ResponseEntity<PlanResponseDto> createPlan(@Valid @RequestBody PlanRequestDto dto) {

		logger.info("Received request to create Plan: {}", dto.getName());

		PlanResponseDto response = service.createPlan(dto);

		logger.info("Plan created successfully");

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Get Plan By ID")
	@GetMapping("/plans/{id}")
	public ResponseEntity<PlanResponseDto> getPlan(@PathVariable Long id) {

		logger.info("Fetching Plan with id: {}", id);

		PlanResponseDto response = service.getPlan(id);

		logger.info("Plan fetched successfully with id: {}", id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get All Plans")
	@GetMapping("/plans")
	public ResponseEntity<List<PlanResponseDto>> getPlans() {

		logger.info("Fetching all Plans");

		List<PlanResponseDto> plans = service.getPlans();

		logger.info("Total plans fetched: {}", plans.size());

		return new ResponseEntity<>(plans, HttpStatus.OK);
	}

	@Operation(summary = "Update Plan")
	@PutMapping("/plans/{id}")
	public ResponseEntity<PlanResponseDto> updatePlan(@PathVariable Long id, @RequestBody PlanRequestDto dto) {

		logger.info("Updating Plan with id: {}", id);

		PlanResponseDto response = service.updatePlan(id, dto);

		logger.info("Plan updated successfully with id: {}", id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Delete Plan")
	@DeleteMapping("/plans/{id}")
	public ResponseEntity<PlanResponseDto> deletePlan(@PathVariable Long id) {

		logger.info("Deleting Plan with id: {}", id);

		PlanResponseDto response = service.deletePlan(id);

		logger.info("Plan deleted successfully with id: {}", id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}