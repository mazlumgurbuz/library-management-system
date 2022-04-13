package com.example.library.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.library.dto.request.BookRequest;
import com.example.library.dto.response.BookResponse;
import com.example.library.entity.Book;
import com.example.library.service.BookService;

@RestController
@RequestScope
@RequestMapping("/book")
@CrossOrigin
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(value = "/getall")
	public List<BookResponse> getAll() {
		return bookService.getAll();
	}

	@GetMapping(value = "/getbyId/{id}")
	public BookResponse getById(@PathVariable Long id) {
		return bookService.getById(id);
	}

	@PostMapping()
	public BookResponse addBook(@RequestBody BookRequest request) {

		return bookService.add(request);
	}

	@PutMapping(value = "{identity}")
	public BookResponse updateAuthor(@PathVariable Long identity, @RequestBody BookRequest request) {

		return bookService.update(identity, request);
	}

	@DeleteMapping(value = "{id}")
	public Book delete(@PathVariable Long id) {

		return bookService.deleteById(id);
	}
}
