package com.example.demo.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.SignUp;
import com.example.demo.Repository.SignUpRepo;
@Service
public class SignUpService {
	@Autowired 
	SignUpRepo repo;
//	public Iterable<SignUp> GetUserPassService(String username){
//		return repo.findAllUsernamePassword(username);
//	}
	public Iterable<SignUp> GetAll(){
		return repo.findAll();
	}
	
}
