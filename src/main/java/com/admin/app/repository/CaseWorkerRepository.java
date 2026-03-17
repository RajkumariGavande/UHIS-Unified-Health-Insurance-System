package com.admin.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.app.entity.CaseWorker;

@Repository
public interface CaseWorkerRepository extends JpaRepository<CaseWorker,Long>{

	boolean existsByEmail(String email);

	CaseWorker findByEmail(String email);

	CaseWorker findByEmailAndPassword(String email,String password);

}
