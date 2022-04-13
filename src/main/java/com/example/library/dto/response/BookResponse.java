package com.example.library.dto.response;

import java.time.LocalDate;

import com.example.library.entity.BookType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BookResponse {

	private Long id;
	private String  isbn;
	private String author;
	private String category;
	private String publisher;
	private LocalDate pressDate;
	private BookType bookType;
	private String title;
	private boolean isBorrowed;
	private boolean isReserved;
	
}
