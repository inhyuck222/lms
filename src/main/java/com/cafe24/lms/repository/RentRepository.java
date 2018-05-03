package com.cafe24.lms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Rent;

public interface RentRepository extends JpaRepository<Rent, Long>{
	
	@Query("select r from Rent r where r.item.no = :itemNo")
	List<Rent> findByItemNo(@Param("itemNo") Long itemNo);
	
	@Query("select r from Rent r where r.reservation.rentOrder = 0")
	Page<Rent> findAll(Pageable pageable);
	
	@Query("select r from Rent r where r.reservation.rentOrder != 0")
	Page<Rent> findAllReservation(Pageable pageable);
	
}
