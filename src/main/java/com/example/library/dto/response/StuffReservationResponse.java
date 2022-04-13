package com.example.library.dto.response;

import java.time.LocalDate;

import com.example.library.entity.Book;
import com.example.library.entity.Stuff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuffReservationResponse {
	private LocalDate reservedDate;
	private LocalDate dueDate;
	private Stuff stuff;
	private Book book;
}
