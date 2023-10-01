package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.Traveller;
import com.example.demo.Repository.TravellerRepository;
import com.example.demo.Service.TravellerService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/traveller")
public class TravellerController {

    @Autowired
    private TravellerRepository repo;

    @Autowired
    TravellerService service;
    
    @GetMapping
    public List<Traveller> getAllTravellers(){
        return repo.findAll();
    }

    @PostMapping
    public Traveller createTraveller(@RequestBody Traveller employee) {
        return repo.save(employee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Traveller>> getTravellerById(@PathVariable  int id){
        Optional<Traveller> employee = repo.findById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("{id}")
	public Traveller updateTravellerDetails(@RequestBody Traveller b)
	{
		System.out.println("Changes Made Have Been Successfully Updated");
		return repo.save(b);		
	}
    @DeleteMapping("{id}")
	public String deleteDetails(@PathVariable int id)
	{
    	repo.deleteById(id);
		return "Id : "+id+" is deleted";
	}
    @DeleteMapping
    public String deleteAllDetails()
    {
    	repo.deleteAll();
    	return "All employees deleted";
    }
    
    //search
	 @GetMapping("/items")
	    public List<Traveller> search(@RequestParam("query") String query) {
	        // Perform the search using JPA query or criteria query
	        return repo.searchItems(query);
	    }
	 
	 @GetMapping("/sorting")
	 public List<Traveller> getItems(@RequestParam(required = false) String sort) {
	        if (sort != null && !sort.isEmpty()) {
	            Sort.Direction direction = Sort.Direction.ASC;
	            if (sort.startsWith("-")) {
	                direction = Sort.Direction.DESC;
	                sort = sort.substring(1);
	            }
	            Sort.Order order = new Sort.Order(direction, sort);
	            Sort sortObj = Sort.by(order);
	            return repo.findAll(sortObj);
	        } else {
	            return repo.findAll();
	        }
	    }
	 
	 @GetMapping("/sorttravel/{field}")
		public Iterable<Traveller> gettravelsorted(@PathVariable("field")String str)
		{
			return service.sorttravel(str);
		}
		
		//paging
		@GetMapping(value="/pagingfood/{pageNo}/{pageSize}")
		Page<Traveller> foodPaging(@PathVariable("pageNo")int pageno,@PathVariable("pageSize")int pagesize){
			return service.pagingtravel(pageno,pagesize);
			
		}

		//paging and sorting
	    @GetMapping(value = "/pagingSortingfood/{pageNo}/{pageSize}/{field}")
		Page<Traveller> foodPagingAndSorting(@PathVariable ("pageNo") int pageno,
				@PathVariable ("pageSize") int pageSize,@PathVariable ("field")String field) {
			return service.pagingandSortingtravel(pageno, pageSize,field);

		}
	
}