package com.example.library.service;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.StudentAddRequest;
import com.example.library.dto.request.StudentUpdateRequest;
import com.example.library.dto.response.StudentResponse;
import com.example.library.entity.Student;
import com.example.library.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;

	public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	public StudentResponse addStudent(StudentAddRequest request) {
		var ogrenci = modelMapper.map(request, Student.class);

		var addedStudent = studentRepository.save(ogrenci);

	
		return modelMapper.map(addedStudent, StudentResponse.class);
	}

	@Transactional
	public StudentResponse updateStudent(Long identity, StudentUpdateRequest request) {
		var student = studentRepository.findById(identity).orElseThrow(() -> new EntityNotFoundException());
		modelMapper.map(request, student);
		return modelMapper.map(studentRepository.saveAndFlush(student), StudentResponse.class);

	}
	@Transactional
	public StudentResponse patchStudent(Long id,Map<String, Object> request) {
		var student=studentRepository.findById(id)
				.orElseThrow(()->new EntityNotFoundException());
		request.forEach((property,value)->{
			Field declaredField;
			try {
				declaredField=Student.class.getDeclaredField(property);
				if(property.equals("phone")) {
					declaredField.setAccessible(true);
					declaredField.set(student, value.toString());
					declaredField.setAccessible(false);
				}
			} catch (Exception e) {
				
			}
		});
		return modelMapper.map(
				studentRepository.save(student), 
				StudentResponse.class);
	}
	@Transactional
	public StudentResponse deleteById(Long id) {
		var student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		studentRepository.deleteById(id);
		return modelMapper.map(student, StudentResponse.class);
	}

	public StudentResponse findById(Long id) {
		return modelMapper.map(studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()),
				StudentResponse.class);
	}

	public List<StudentResponse> getAll(int pageNo,int pageSize) {
		return studentRepository.findAll(PageRequest.of(pageNo, pageSize))
				.stream()
				.map(student->modelMapper.map(student, StudentResponse.class))
				.sorted(Comparator.comparing(StudentResponse::getName))
				.toList();
	}

	
	

}
