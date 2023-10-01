package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Traveller;

public interface TravellerRepository extends JpaRepository<Traveller,Integer>, PagingAndSortingRepository<Traveller, Integer> {

    @Query("SELECT i FROM Traveller i WHERE LOWER(i.name) LIKE CONCAT('%', LOWER(:searchWord), '%') "
    		+ "OR LOWER(i.departure) LIKE CONCAT('%', LOWER(:searchWord), '%') OR LOWER(i.arrival) LIKE CONCAT('%', LOWER(:searchWord), '%')"
    		+ " OR CAST(i.age AS string) LIKE %:searchWord% OR CAST(i.budget AS string) LIKE %:searchWord%")
    List<Traveller> searchItems(@Param("searchWord") String searchWord);
    
    List<Traveller> findAll(Sort sort);
}
