package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.StudentBorrow;

@Repository
public interface StudentBorrowRepository extends JpaRepository<StudentBorrow, Long>{

}
