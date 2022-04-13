package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.StuffReservation;

public interface StuffReservationRepository extends JpaRepository<StuffReservation,Long >{

}
