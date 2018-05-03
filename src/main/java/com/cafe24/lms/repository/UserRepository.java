package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
}
