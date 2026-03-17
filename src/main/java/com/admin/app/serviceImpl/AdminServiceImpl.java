package com.admin.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.admin.app.dto.CaseWorkerRequestDto;
import com.admin.app.dto.CaseWorkerResponseDto;
import com.admin.app.dto.PlanRequestDto;
import com.admin.app.dto.PlanResponseDto;
import com.admin.app.entity.CaseWorker;
import com.admin.app.entity.Plans;
import com.admin.app.enums.AccountStatus;
import com.admin.app.exception.DuplicateResourceException;
import com.admin.app.exception.InvalidCredentialsException;
import com.admin.app.exception.InvalidRequestException;
import com.admin.app.exception.PlanAlreadyExistsException;
import com.admin.app.exception.ResourceNotFoundException;
import com.admin.app.repository.CaseWorkerRepository;
import com.admin.app.repository.PlansRepository;
import com.admin.app.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	private CaseWorkerRepository workerRepository;
	private PlansRepository plansRepository;
	private ModelMapper mapper;

	public AdminServiceImpl(CaseWorkerRepository workerRepository, PlansRepository plansRepository,
			ModelMapper mapper) {
		this.workerRepository = workerRepository;
		this.plansRepository = plansRepository;
		this.mapper = mapper;
	}

	// ================= Case Worker =====================

	@Override
	public CaseWorkerResponseDto createAccount(@Valid CaseWorkerRequestDto dto) {

		if (workerRepository.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException("Email already exists: " + dto.getEmail());
		}
		logger.info("Creating new CaseWorker account for email: {}", dto.getEmail());

		CaseWorker caseWorker = mapper.map(dto, CaseWorker.class);

		caseWorker.setPassword(generateRandomPassword(6));
		caseWorker.setStatus(AccountStatus.ACTIVE);
		caseWorker.setCreateDate(LocalDateTime.now());
		caseWorker.setUpdatedDate(LocalDateTime.now());

		CaseWorker savedWorker = workerRepository.save(caseWorker);

		logger.info("CaseWorker created successfully with id: {}", savedWorker.getAccountId());

		return mapper.map(savedWorker, CaseWorkerResponseDto.class);
	}

	@Override
	public CaseWorkerResponseDto getCaseWorkerById(Long id) {

		logger.info("Fetching CaseWorker with id: {}", id);
		if (workerRepository.existsById(id)) {
			CaseWorker worker = workerRepository.findById(id).get();
			return mapper.map(worker, CaseWorkerResponseDto.class);
		} else {
			logger.error("CaseWorker not found with id: {}", id);
			throw new ResourceNotFoundException("Resource Not Found");
		}

	}

	@Override
	public List<CaseWorkerResponseDto> getAllCaseWorkers() {

		logger.info("Fetching all CaseWorkers");

		List<CaseWorker> workers = workerRepository.findAll();
		List<CaseWorkerResponseDto> responseList = new ArrayList<>();

		for (CaseWorker worker : workers) {
			responseList.add(mapper.map(worker, CaseWorkerResponseDto.class));
		}

		logger.info("Total CaseWorkers found: {}", responseList.size());

		return responseList;
	}

	@Override
	public void deleteCaseWorker(Long id) {

		logger.info("Deleting CaseWorker with id: {}", id);
		if (workerRepository.existsById(id)) {
			workerRepository.deleteById(id);

			logger.info("CaseWorker deleted successfully with id: {}", id);
		} else {

			logger.error("CaseWorker not found with id: {}", id);
		}
	}

	@Override
	public CaseWorkerResponseDto updateCaseWorker(Long id, @Valid CaseWorkerRequestDto dto) {

		logger.info("Updating CaseWorker with id: {}", id);

		CaseWorker worker = workerRepository.findById(id).orElseThrow(() -> {
			logger.error("CaseWorker not found with id: {}", id);
			return new RuntimeException("CaseWorker Not Found");
		});

		mapper.map(dto, worker);

		worker.setUpdatedDate(LocalDateTime.now());

		CaseWorker updated = workerRepository.save(worker);

		logger.info("CaseWorker updated successfully with id: {}", id);

		return mapper.map(updated, CaseWorkerResponseDto.class);
	}

	@Override
	public void updateStatus(Long id, AccountStatus status) {

		logger.info("Updating status for CaseWorker id: {}", id);

		CaseWorker worker = workerRepository.findById(id).orElseThrow(() -> {
			logger.error("CaseWorker not found with id: {}", id);
			return new RuntimeException("CaseWorker Not Found");
		});

		worker.setStatus(status);
		worker.setUpdatedDate(LocalDateTime.now());

		workerRepository.save(worker);

		logger.info("Status updated successfully for CaseWorker id: {}", id);
	}

	@Override
	public CaseWorkerResponseDto login(String email, String password) {
		if (workerRepository.existsByEmail(email)) {
			CaseWorker worker = workerRepository.findByEmailAndPassword(email,password);
			CaseWorkerResponseDto responseDto = mapper.map(worker, CaseWorkerResponseDto.class);
			return responseDto;
		} else {
			throw new InvalidCredentialsException("Invalid Credentials...!");
		}
	}

	// ================= Random Password =================

	public static String generateRandomPassword(int length) {

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$";

		Random random = new Random();
		StringBuilder password = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			password.append(characters.charAt(index));
		}

		return password.toString();
	}

	// ================= Plans CRUD =====================

	@Override
	public PlanResponseDto createPlan(@Valid PlanRequestDto dto) {

		if (plansRepository.existsByName(dto.getName())) {
			throw new PlanAlreadyExistsException(dto.getName());
		}
		logger.info("Creating new Plan: {}", dto.getName());

		Plans plan = mapper.map(dto, Plans.class);

		Plans savedPlan = plansRepository.save(plan);

		logger.info("Plan created successfully with id: {}", savedPlan.getPlan_id());

		return mapper.map(savedPlan, PlanResponseDto.class);
	}

	@Override
	public PlanResponseDto deletePlan(Long id) {

		logger.info("Deleting Plan with id: {}", id);

		Plans plan = plansRepository.findById(id).orElseThrow(() -> {
			logger.error("Plan not found with id: {}", id);
			return new RuntimeException("Plan Not Found");
		});

		plansRepository.delete(plan);

		logger.info("Plan deleted successfully with id: {}", id);

		return mapper.map(plan, PlanResponseDto.class);
	}

	@Override
	public PlanResponseDto getPlan(Long id) {

		logger.info("Fetching Plan with id: {}", id);

		Plans plan = plansRepository.findById(id).orElseThrow(() -> {
			logger.error("Plan not found with id: {}", id);
			return new RuntimeException("Plan Not Found");
		});

		return mapper.map(plan, PlanResponseDto.class);
	}

	@Override
	public List<PlanResponseDto> getPlans() {

		logger.info("Fetching all Plans");

		List<Plans> plans = plansRepository.findAll();
		List<PlanResponseDto> responseList = new ArrayList<>();

		for (Plans plan : plans) {
			responseList.add(mapper.map(plan, PlanResponseDto.class));
		}

		logger.info("Total Plans found: {}", responseList.size());

		return responseList;
	}

	@Override
	public PlanResponseDto updatePlan(Long id, PlanRequestDto dto) {

		logger.info("Updating Plan with id: {}", id);

		Plans plan = plansRepository.findById(id).orElseThrow(() -> {
			logger.error("Plan not found with id: {}", id);
			return new RuntimeException("Plan Not Found");
		});

		plan.setName(dto.getName());
		plan.setCategory(dto.getCategory());
		if (dto.getStartDate().isAfter(dto.getEndDate())) {
			throw new InvalidRequestException("Start date cannot be after End date");
		}
		plan.setStartDate(dto.getStartDate());
		plan.setEndDate(dto.getEndDate());
		plan.setStatus(dto.getStatus());

		Plans updatedPlan = plansRepository.save(plan);

		logger.info("Plan updated successfully with id: {}", id);

		return mapper.map(updatedPlan, PlanResponseDto.class);
	}
}