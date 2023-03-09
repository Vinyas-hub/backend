package com.travelzilla.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelzilla.exceptions.LoginException;
import com.travelzilla.models.LoginDTO;
import com.travelzilla.models.Session;
import com.travelzilla.models.UserType;
import com.travelzilla.services.LoginServices;


@RestController
@CrossOrigin(origins="*")

public class LoginController {

	@Autowired
	private LoginServices lServices;
	
	@PostMapping(path="/user/login",consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<Session> login(@RequestBody LoginDTO loginDto) throws LoginException{
		System.out.println(loginDto);
		Session session = lServices.login(loginDto);
		return new ResponseEntity<Session>(session, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path="/user/logout",consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<String> logout(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "userType") UserType userType)throws LoginException{
		String response =  lServices.logout(userId, userType);
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
}
