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

import com.example.library.dto.request.StudentReservationRequest;
import com.example.library.dto.response.StudentReservationResponse;
import com.example.library.service.StudentReservationService;


@RestController
@RequestScope
@RequestMapping("/studentReservation")
@CrossOrigin
public class StudentReservationController {

	private final StudentReservationService studentReservationService;

	public StudentReservationController(StudentReservationService studentReservationService) {
		this.studentReservationService = studentReservationService;
	}
	@GetMapping("/getAll")
	public List<StudentReservationResponse> getAll(){
		return studentReservationService.getAll();
	}
	@GetMapping("/getById/{identity}")
	public StudentReservationResponse getById(@PathVariable Long identity){
		return studentReservationService.getById(identity);
	}
	@PostMapping
	public StudentReservationResponse addStudentReserve(@RequestBody StudentReservationRequest request) throws Exception {
		return studentReservationService.addStudentReservation(request);
	}
	@GetMapping("/deActive/{identity}")
	public StudentReservationResponse deActive(@PathVariable Long identity) {
		return studentReservationService.deActiveReserve(identity);
	}
	@GetMapping("/delete/{identity}")
	public StudentReservationResponse delete(@PathVariable Long identity) {
		return studentReservationService.delete(identity);
	}
	
	
}
