package com.example.library.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.BookRequest;
import com.example.library.dto.response.BookResponse;
import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService{

	private BookRepository bookRepository;
	private ModelMapper modelMapper;
	
	public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
	}

	public BookResponse add(BookRequest bookRequest) {
		var book = modelMapper.map(bookRequest, Book.class);
		return modelMapper.map(bookRepository.save(book), BookResponse.class);
	}
	
	public Book deleteById(Long identity) {
		var deletedBook = bookRepository.findById(identity).orElseThrow();
		bookRepository.deleteById(identity);
		return deletedBook;
	}
	public BookResponse update(Long identity, BookRequest request) {
		var updated = bookRepository.findById(identity).orElseThrow(()->new EntityNotFoundException());
		modelMapper.map(request, updated);
		
		return modelMapper.map(bookRepository.save(updated), BookResponse.class);

	}
	public BookResponse getById(Long identity) {
		return modelMapper.map(bookRepository.findById(identity)
				.orElseThrow(()->new EntityNotFoundException()), BookResponse.class);
	}

	public List<BookResponse> getAll() {
		return bookRepository.findAll()
				.stream().map(book -> modelMapper.map(book, BookResponse.class))
				.toList();
	}
}
