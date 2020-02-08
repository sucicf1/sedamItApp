package com.ivsucic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivsucic.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	Service findByExternId(long externId);
}
