package com.example.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.SignUp;
import com.example.demo.Repository.SignUpRepo;

@RestController
@CrossOrigin
@RequestMapping("/signup")
public class SignUpController {
	@Autowired SignUpRepo repo;
	@PostMapping("/post")
	private SignUp PostUser(@RequestBody SignUp s){
		return repo.save(s);
	}
}
