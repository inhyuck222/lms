package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
