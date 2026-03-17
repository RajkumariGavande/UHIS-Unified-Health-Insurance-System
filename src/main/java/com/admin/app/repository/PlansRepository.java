package com.admin.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.app.entity.Plans;

@Repository
public interface PlansRepository  extends JpaRepository<Plans,Long>{

	boolean existsByName(String name);
	

}
