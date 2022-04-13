package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.StudentReservation;

public interface StudentReservationRepository  extends JpaRepository<StudentReservation, Long>{

}
