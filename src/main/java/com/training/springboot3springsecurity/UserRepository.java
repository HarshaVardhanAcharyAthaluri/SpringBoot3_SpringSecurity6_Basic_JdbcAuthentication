package com.training.springboot3springsecurity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUname(String uname);
}
