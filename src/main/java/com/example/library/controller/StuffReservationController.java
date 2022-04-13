package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.library.dto.request.StuffReservationRequest;
import com.example.library.dto.response.StuffReservationResponse;
import com.example.library.service.StuffReservationService;

@RestController
@RequestMapping("/stuffReservation")
@RequestScope
@CrossOrigin
public class StuffReservationController {
	private final StuffReservationService stuffReservationService;

	public StuffReservationController(StuffReservationService stuffReservationService) {
		this.stuffReservationService = stuffReservationService;
	}
	@GetMapping("/getAll")
	public List<StuffReservationResponse> getAll(){
		return stuffReservationService.getAll();
	}
	@GetMapping("/getById/{identity}")
	public StuffReservationResponse getById(@PathVariable Long identity){
		return stuffReservationService.getById(identity);
	}
	@PostMapping
	public StuffReservationResponse addStudentReserve(@RequestBody StuffReservationRequest request) throws Exception {
		return stuffReservationService.addStuffReservation(request);
	}
	@GetMapping("/deActive/{identity}")
	public StuffReservationResponse deActive(@PathVariable Long identity) {
		return stuffReservationService.deActiveReserve(identity);
	}
	@GetMapping("/delete/{identity}")
	public StuffReservationResponse delete(@PathVariable Long identity) {
		return stuffReservationService.delete(identity);
	}
	
	
	
}
