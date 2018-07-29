package com.example.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, insertable = false, updatable = false)
	private Long userId;

	@NotEmpty
	@Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 100)
	private String firstName;

	@NotEmpty
	@Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 100)
	private String lastName;

	@NotEmpty
	@Email
	@Column(name = "email", nullable = false, insertable = true, updatable = true, length = 100)
	private String email;

	@Column(name = "flag_active", nullable = true, insertable = true, updatable = true)
	private Boolean isActive;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
