package com.example.library.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.StuffBorrowRequest;
import com.example.library.dto.request.StuffRequest;
import com.example.library.dto.response.StuffBorrowResponse;
import com.example.library.entity.Book;
import com.example.library.entity.Stuff;
import com.example.library.entity.StuffBorrow;
import com.example.library.repository.StuffBorrowRepository;
import com.example.library.utilities.rules.RuleResult;
import com.example.library.utilities.rules.StuffBorrowRules;

@Service
public class StuffBorrowService {

	private final StuffBorrowRepository stuffBorrowRepository;
	private final BookService bookService;
	private final ModelMapper modelMapper;
	private final StuffService stuffService;

	public StuffBorrowService(StuffBorrowRepository stuffBorrowRepository, BookService bookService,
			ModelMapper modelMapper, StuffService stuffService) {
		this.stuffBorrowRepository = stuffBorrowRepository;
		this.bookService = bookService;
		this.modelMapper = modelMapper;
		this.stuffService = stuffService;
	}

	@Transactional
	public StuffBorrowResponse addStuffBorrow(StuffBorrowRequest request) throws Exception {
		var stuff = modelMapper.map(stuffService.findById(request.getStuff().getId()), Stuff.class);
		var book = modelMapper.map(bookService.getById(request.getBook().getId()), Book.class);
		RuleResult result = StuffBorrowRules.borrow(book, stuff);
		var borrow = modelMapper.map(request, StuffBorrow.class);
		if (!result.isDecision()) {
			throw new Exception(result.getMessage());
		}
		stuff.setBorrowedBookNum(stuff.getBorrowedBookNum() + 1);
		book.setBorrowed(true);
		stuffService.updateStuff(stuff.getId(), modelMapper.map(stuff, StuffRequest.class));
		return modelMapper.map(stuffBorrowRepository.save(borrow), StuffBorrowResponse.class);
	}
	
	public StuffBorrowResponse getById(Long id) {
		var stuffBorrow=stuffBorrowRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
	    return modelMapper.map(stuffBorrow, StuffBorrowResponse.class);	
	}

	public List<StuffBorrowResponse> getAll(){
		return stuffBorrowRepository.findAll().stream()
				.map(stuff->modelMapper.map(stuff, StuffBorrowResponse.class))
				.toList();
	}
	@Transactional
	public StuffBorrowResponse deActiveReserve(Long id) {
		var reserve=stuffBorrowRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		reserve.setActive(false);
		return modelMapper.map(reserve, StuffBorrowResponse.class);
	}
	public StuffBorrowResponse delete(Long id) {
		var reserve=stuffBorrowRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
	    stuffBorrowRepository.deleteById(id);
		return modelMapper.map(reserve, StuffBorrowResponse.class);
	}
}
















