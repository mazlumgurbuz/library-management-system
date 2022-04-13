package com.example.library.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.request.StuffRequest;
import com.example.library.dto.response.StuffResponse;
import com.example.library.service.StuffService;

@RestController
@RequestMapping("/stuff")
@CrossOrigin
public class StuffController {

	private final StuffService stuffService;

	public StuffController(StuffService stuffService) {
		this.stuffService = stuffService;
	}
	@GetMapping("/getall")
	public List<StuffResponse> getAll(){
		return stuffService.findAll();
	}
	@GetMapping(value = "{identity}")
	public StuffResponse getById(@PathVariable Long identity) {
		return stuffService.findById(identity);
	}
	@PostMapping("/addStuff")
	public StuffResponse addStuff(@RequestBody @Validated StuffRequest request) {
		return stuffService.addStuff(request);
	}
	@PutMapping(value = "{identity}")
	public StuffResponse updateStuff(@PathVariable Long identity,
			@RequestBody
			@Validated StuffRequest request) {
		return stuffService.updateStuff(identity, request);
	}
	@DeleteMapping("{identity}")
	public StuffResponse deleteById(@PathVariable Long identity) {
		return stuffService.deleteStuff(identity);
	}
}
