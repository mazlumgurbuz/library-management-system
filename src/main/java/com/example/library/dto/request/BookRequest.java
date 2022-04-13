package com.example.library.dto.request;

import java.time.LocalDate;

import com.example.library.entity.BookType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BookRequest {
	private String  isbn;
	private String author;
	private String category;
	private String publisher;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate pressDate;
	private BookType bookType;
	private String title;
	private boolean isBorrowed;
	private boolean isReserved;
	
}
