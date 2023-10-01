package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.Entity.Transportmode;


public interface TransportmodeRepository extends JpaRepository<Transportmode, Integer>, PagingAndSortingRepository<Transportmode, Integer> {
	 Optional<Transportmode> findByTravellerId(int travellerid);

}
