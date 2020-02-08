package com.ivsucic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ivsucic.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String name);
}
