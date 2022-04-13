package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.library.dto.request.StudentBorrowRequest;
import com.example.library.dto.request.StuffBorrowRequest;
import com.example.library.dto.response.StudentBorrowResponse;
import com.example.library.dto.response.StuffBorrowResponse;
import com.example.library.service.StuffBorrowService;

@RestController
@RequestMapping("/stuffBorrow")
@RequestScope
@CrossOrigin
public class StuffBorrowController {

	private final StuffBorrowService stuffBorrowService;

	public StuffBorrowController(StuffBorrowService stuffBorrowService) {
		this.stuffBorrowService = stuffBorrowService;
	}
	@GetMapping("getAll")
	public List<StuffBorrowResponse> getAll() {
		return stuffBorrowService.getAll();
	}
    @GetMapping("getById/{identity}")
   	public StuffBorrowResponse getById(@PathVariable Long identity) {
   		return stuffBorrowService.getById(identity);
   	}
    
	@PostMapping
	public StuffBorrowResponse addBorrow(@RequestBody StuffBorrowRequest borrow) throws Exception {
		return stuffBorrowService.addStuffBorrow(borrow);
	}
	@GetMapping("deActive/{identity}")
   	public StuffBorrowResponse deActive(@PathVariable Long identity) {
   		return stuffBorrowService.deActiveReserve(identity);
   	}
	@DeleteMapping("delete/{identity}")
	public StuffBorrowResponse delete(@PathVariable Long identity) {
		return stuffBorrowService.delete(identity);
	}
}
