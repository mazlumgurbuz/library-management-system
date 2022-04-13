package com.example.library.dto.request;

import java.time.LocalDate;

import com.example.library.entity.Book;
import com.example.library.entity.Stuff;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuffBorrowRequest {
	private Long id;
	private LocalDate borrowedDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate expectedDeliverDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate deliveredDate;
	private Stuff stuff;
	private Book book;
	
	
}
