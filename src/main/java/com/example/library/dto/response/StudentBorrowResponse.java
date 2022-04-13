package com.example.library.dto.response;

import java.time.LocalDate;

import com.example.library.entity.Book;
import com.example.library.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentBorrowResponse {

	private Long id;
	private boolean isActive;
	private LocalDate borrowedDate;
	private LocalDate expectedDeliverDate;
	private LocalDate deliveredDate;
	private Student student;
	private Book book;
	
}
