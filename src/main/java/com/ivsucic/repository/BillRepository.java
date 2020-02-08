package com.ivsucic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ivsucic.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
	Bill findById(long id);
}
