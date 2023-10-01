package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Entity.Transportmode;
import com.example.demo.Repository.TransportmodeRepository;
import com.example.demo.Repository.TravellerRepository;

@Service
public class TransportmodeService {
	@Autowired
	TravellerRepository repo;
	
	@Autowired
	TransportmodeRepository mode;
	
	public List<Transportmode> getAll()
	{
		return mode.findAll();
	}
	public Optional<Transportmode> getModeByTravelId(int travellerid) 
	{
	  if (!repo.existsById(travellerid)) 
	  {
	    throw new ResourceNotFoundException("Not found traveller with id = " + travellerid);
	  }
	    Optional<Transportmode> transportmode = mode.findByTravellerId(travellerid);
	    return transportmode;
	}
	
	public Optional<Transportmode> getTravelModeByModeid(int id) {
		Optional<Transportmode> transportmode = mode.findById(id);
		return transportmode;
	}
	
	  
	public ResponseEntity<Transportmode> createMode(int travellerid, Transportmode transportmode)
	{  
		Transportmode tmode = repo.findById(travellerid).map(traveller -> {
		transportmode.setTraveller(traveller);
		return mode.save(transportmode);
	}).orElseThrow(() -> new ResourceNotFoundException("Not found travel with id = " + travellerid));
		   return new ResponseEntity<>(tmode, HttpStatus.CREATED);
	}
		
	 public ResponseEntity<Transportmode> updateMode(int id,Transportmode moderequest) 
	 {
	    Transportmode tmode = mode.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Travelid " + id + "not found"));
		return new ResponseEntity<>(mode.save(tmode), HttpStatus.OK);
	 }
		
	 public ResponseEntity<HttpStatus> deleteComment(int id) 
	 {
		 mode.deleteById(id);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 }
		
	  
}
