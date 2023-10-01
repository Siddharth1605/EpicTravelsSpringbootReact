package com.example.demo.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Transportmode;
import com.example.demo.Repository.TransportmodeRepository;
import com.example.demo.Service.TransportmodeService;


@CrossOrigin("*")
@RestController
@RequestMapping("/transportmode")
public class TransportmodeController {

	@Autowired
	TransportmodeService modeservice;
	TransportmodeRepository repo;
	
	@GetMapping()
	public List<Transportmode> get()
	{
		return modeservice.getAll();
	}
	@GetMapping("/traveller/{travellerid}")
	public Optional<Transportmode> getmodebytravellerid(@PathVariable(value = "travellerid") int travellerid) 
	{
		return modeservice.getModeByTravelId(travellerid);
	}
	
	@GetMapping("/{id}")
	  public Optional<Transportmode> getmodebymodeid(@PathVariable(value = "id") int id) {
		return modeservice.getTravelModeByModeid(id);
	}
	
	@PostMapping("/traveller/{travellerid}")
	public ResponseEntity<Transportmode> create(@PathVariable(value = "travellerid") int travellerid, @RequestBody Transportmode transportmode)
	{
	  
	    return modeservice.createMode(travellerid, transportmode);
	}
	
	@PutMapping("/{id}")
	  public ResponseEntity<Transportmode> update(@PathVariable("id") int id, @RequestBody Transportmode moderequest) 
	{
	    return modeservice.updateMode(id, moderequest);
	}
	
	@DeleteMapping("/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) 
	 {
		 return modeservice.deleteComment(id);
	 }
	@DeleteMapping()
    public String deleteAllDetails()
    {
    	repo.deleteAll();
    	return "All employees deleted";
    }

}
