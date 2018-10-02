package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.Credentials;
import com.revature.models.User;
import com.revature.services.UserService;

@CrossOrigin(origins = "http://revature-1808.cnxwdhy3jnk8.us-west-2.rds.amazonaws.com:5432/postgres")
@RestController
//@JsonIgnoreProperties
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService us;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User u){
		ResponseEntity<User> re = new ResponseEntity<User>(u, HttpStatus.CREATED);
		us.createUser(u);
		return re;
	}
	
	@GetMapping
	public List<User> findAll(){
		return us.findAll();
	}
	
	@PostMapping("login")
	public User login(@RequestBody Credentials u) {
		return us.findByUserIdAndPass(u.getUserId(), u.getPass());
	}
	
	@GetMapping("{userId}")
	public User findByUserId(@PathVariable int userId){
		User u = us.findByUserId(userId);
		return u;
	}
	
	@PostMapping("changePass")
	public User findByUserIdAndEmail(@RequestBody Credentials u) {
		return us.findByUserIdAndEmail(u.getUserId(), u.getEmail());
	}
	
	@GetMapping("role/{role}")
	public List<User> findByRole(@PathVariable int role){
		return us.findByRole(role);
	}
}