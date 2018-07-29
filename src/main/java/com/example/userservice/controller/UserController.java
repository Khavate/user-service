package com.example.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "Add new users")
	@RequestMapping(method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addUser(@RequestBody @Valid User user) {
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get user")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getUser(@PathVariable(value="id", required=true) Long id) {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@ApiOperation(value = "List users")
	@RequestMapping(method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> listUsers(Pageable pageable) {
		return new ResponseEntity<>(userService.listUsers(pageable), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Search users")
	@RequestMapping(value = "/search", method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> seacrhUsers(@RequestParam(value = "lastName", required = true) String lastName, 
			@RequestParam(value = "isActive", required = false) Boolean isActive, Pageable pageable) {
		return new ResponseEntity<>(userService.searchUsers(lastName, isActive, pageable), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update user")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateUser(@PathVariable(value = "id", required = true) Long id, 
			@RequestBody @Valid User user) {
		return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
	}

	
	@ApiOperation(value = "Delete user")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteUser(@PathVariable(value="id", required=true) Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
