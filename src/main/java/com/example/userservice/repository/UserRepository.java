package com.example.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Page<User> findAllByLastName(String lastName, Pageable pageable);

	public Page<User> findAllByLastNameAndIsActive(String lastName, Boolean isActive, Pageable pageable);

}
