package com.example.library.service;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.StuffRequest;
import com.example.library.dto.response.StuffResponse;
import com.example.library.entity.Stuff;
import com.example.library.repository.StuffRepository;

@Service
public class StuffService {

	private final StuffRepository stuffRepository;
	private final ModelMapper modelMapper;
	public StuffService(StuffRepository stuffRepository, ModelMapper modelMapper) {
		this.stuffRepository = stuffRepository;
		this.modelMapper = modelMapper;
	}
	@Transactional
	public StuffResponse addStuff(StuffRequest request) {
		var stuff=modelMapper.map(request, Stuff.class);
		var addedStuff=stuffRepository.save(stuff);
		return modelMapper.map(addedStuff, StuffResponse.class);
	}
	@Transactional
	public StuffResponse updateStuff(Long id,StuffRequest request) {
		var stuff=stuffRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		modelMapper.map(request, stuff);
		return modelMapper.map(stuffRepository.saveAndFlush(stuff), StuffResponse.class);
		
	}
	@Transactional
	public StuffResponse deleteStuff(Long id) {
		var stuff=stuffRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		stuffRepository.deleteById(id);
		return modelMapper.map(stuff, StuffResponse.class);
		
	}
	public StuffResponse findById(Long id) {
		return modelMapper.map(stuffRepository.findById(id).orElseThrow(()->new EntityNotFoundException()), StuffResponse.class);
				
	}
	public List<StuffResponse> findAll(){
		return stuffRepository.findAll()
				.stream()
				.map(stuff->modelMapper.map(stuff, StuffResponse.class))
				.sorted(Comparator.comparing(StuffResponse::getName))
				.toList();
	}
	
	
	
	
	
}
