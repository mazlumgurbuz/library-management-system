package com.example.library.controller;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.request.StudentAddRequest;
import com.example.library.dto.request.StudentUpdateRequest;
import com.example.library.dto.response.StudentResponse;
import com.example.library.service.StudentService;

@RestController
@RequestMapping("/ogrenci")
@CrossOrigin
public class StudentController {
    private final StudentService studentService;

	public StudentController(StudentService studentService) {
		
		this.studentService = studentService;
	}
	@GetMapping("/getall")
	public List<StudentResponse> getStudentByPage(
			@RequestParam(required = false,defaultValue = "0")
			@Min(0)
			int pageNo,
			@RequestParam(required = false,defaultValue = "20")
			@Max(50)
			int pageSize){
	    return studentService.getAll(pageNo, pageSize);	
	}
	@GetMapping(value="{identity}")
	public StudentResponse getById(@PathVariable Long identity) {
		return studentService.findById(identity);
	}
	@PostMapping("/addStudent")
	public StudentResponse addStudent(@RequestBody @Validated StudentAddRequest request) {
		return studentService.addStudent(request);
	}
	@PutMapping(value = "{identity}")
	public StudentResponse updateStudent(
			@PathVariable Long identity,
			@RequestBody @Validated StudentUpdateRequest request) {
		return studentService.updateStudent(identity, request);		
	}
	@PatchMapping("{identity}")
	public StudentResponse patchStudent(
			@PathVariable Long identity,
			@RequestBody Map<String,Object> request) {
		return studentService.patchStudent(identity, request);
	}
	@DeleteMapping("{identity}")
	public StudentResponse deleteById(
			@PathVariable Long identity) {	
		return studentService.deleteById(identity);
	}	
}
