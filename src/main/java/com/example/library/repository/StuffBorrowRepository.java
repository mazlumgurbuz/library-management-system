package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.StuffBorrow;

public interface StuffBorrowRepository extends JpaRepository<StuffBorrow, Long>{

}
