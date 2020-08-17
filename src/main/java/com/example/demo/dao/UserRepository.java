package com.example.demo.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsername(String userName);
	
}
