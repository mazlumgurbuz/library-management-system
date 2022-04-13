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
public class StudentReservationResponse {

	private LocalDate reservedDate;
	private LocalDate dueDate;
	private Student student;
	private Book book;
}
