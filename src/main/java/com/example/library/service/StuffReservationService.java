package com.example.library.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.StuffReservationRequest;
import com.example.library.dto.response.StuffReservationResponse;
import com.example.library.entity.Book;
import com.example.library.entity.Stuff;
import com.example.library.entity.StuffReservation;
import com.example.library.repository.StuffReservationRepository;
import com.example.library.utilities.rules.RuleResult;
import com.example.library.utilities.rules.StuffReservationRules;

import net.bytebuddy.asm.Advice.Return;

@Service
public class StuffReservationService {

	private final StuffReservationRepository stuffReservationRepository;
	private final ModelMapper modelMapper;
	private final BookService bookService;
	private final StuffService stuffService;

	public StuffReservationService(StuffReservationRepository stuffReservationRepository, ModelMapper modelMapper,
			BookService bookService, StuffService stuffService) {
		this.stuffReservationRepository = stuffReservationRepository;
		this.modelMapper = modelMapper;
		this.bookService = bookService;
		this.stuffService = stuffService;
	}

	@Transactional
	public StuffReservationResponse addStuffReservation(StuffReservationRequest request) throws Exception {
		var stuff = modelMapper.map(stuffService.findById(request.getStuff().getId()), Stuff.class);
		var book = modelMapper.map(bookService.getById(request.getBook().getId()), Book.class);
		RuleResult result = StuffReservationRules.reserve(book, stuff);
		var reservation = modelMapper.map(request, StuffReservation.class);
		if (result.isDecision()) {
			throw new Exception(result.getMessage());
		}
		book.setReserved(true);
		return modelMapper.map(stuffReservationRepository.save(reservation), StuffReservationResponse.class);
	}

	@Transactional
	public StuffReservationResponse deActiveReserve(Long id) {
		var reserve=stuffReservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		reserve.setActive(false);
		return modelMapper.map(reserve, StuffReservationResponse.class);
	}
	public StuffReservationResponse delete(Long id) {
		var reserve=stuffReservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		stuffReservationRepository.deleteById(id);
		return modelMapper.map(reserve,StuffReservationResponse.class);
	}
	public StuffReservationResponse getById(Long id) {
		var stuffReserve=stuffReservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		return modelMapper.map(stuffReserve, StuffReservationResponse.class);
	}
	public List<StuffReservationResponse> getAll(){
		return stuffReservationRepository.findAll().stream()
				.map(reserve->modelMapper.map(reserve, StuffReservationResponse.class))
				.toList();
	}
}
