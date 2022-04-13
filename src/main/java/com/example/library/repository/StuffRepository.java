package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Stuff;

public interface StuffRepository extends JpaRepository<Stuff, Long>{

}
