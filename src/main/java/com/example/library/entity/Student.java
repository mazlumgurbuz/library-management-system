package com.example.library.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="students")
public class Student{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String pinCode;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private LocalDate registeredDate = LocalDate.now();
	private int borrowedBookNum;
	private boolean isActive;
	private String faculty;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student")
	List<StudentBorrow> studentBorrows;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student")
	List<StudentReservation> studentReservation;
}
