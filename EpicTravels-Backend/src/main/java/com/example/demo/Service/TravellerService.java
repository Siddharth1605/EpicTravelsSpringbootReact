package com.example.demo.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Traveller;
import com.example.demo.Repository.TravellerRepository;
@Service
public class TravellerService {
	@Autowired
	TravellerRepository i;
	public Iterable<Traveller> sorttravel(String field)
	{
		return i.findAll(Sort.by(field));
	}
	
	public Page<Traveller> pagingtravel(int page,int pagesize)
	{
		Pageable paging = PageRequest.of(page,pagesize);
		return i.findAll(paging);
	}
	
	public Page<Traveller> pagingandSortingtravel(int offset, int pageSize, String field)
	{
		Pageable paging = PageRequest.of(offset, pageSize).withSort(Sort.by(field));
		return i.findAll(paging);
	}
	
}
