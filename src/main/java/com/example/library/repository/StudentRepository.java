package com.example.library.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Student;
public interface StudentRepository extends JpaRepository<Student,Long>{

}
