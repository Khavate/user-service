package com.example.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.userservice.entity.User;
import com.example.userservice.exception.ResourceNotFoundException;
import com.example.userservice.repository.UserRepository;

@Service
public class UserService extends BaseService {

	@Autowired
	UserRepository userRepository;

	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User getUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(404, "Resource not found"));

		return user;
	}

	public Page<User> listUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Page<User> searchUsers(String lastName, Boolean isActive, Pageable pageable) {
		if (isActive == null) {
			return userRepository.findAllByLastName(lastName, pageable);
		} else {
			return userRepository.findAllByLastNameAndIsActive(lastName, isActive, pageable);
		}
	}

	@Transactional
	public User updateUser(Long id, User user) {
		User existingUser = getUser(id);
		
		User updatedUser = null;
		if (existingUser != null) {
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setEmail(user.getEmail());
			existingUser.setIsActive(user.getIsActive());
			existingUser.setLastModifiedBy(user.getLastModifiedBy());

			updatedUser = userRepository.save(existingUser);
		}

		return updatedUser;
	}

	@Transactional
	public void deleteUser(Long id) {
		User existingUser = getUser(id);

		userRepository.delete(existingUser);
	}
}
