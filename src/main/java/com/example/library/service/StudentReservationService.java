package com.example.library.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.StudentReservationRequest;
import com.example.library.dto.response.StudentReservationResponse;
import com.example.library.entity.Book;
import com.example.library.entity.Student;
import com.example.library.entity.StudentReservation;
import com.example.library.repository.StudentReservationRepository;
import com.example.library.utilities.rules.RuleResult;
import com.example.library.utilities.rules.StudentReservationRules;

@Service
public class StudentReservationService {

	private StudentReservationRepository reservationRepository;
	private ModelMapper modelMapper;
	private BookService bookService;
	private StudentService studentService;

	public StudentReservationService(StudentReservationRepository reservationRepository, ModelMapper modelMapper,
			BookService bookService, StudentService studentService) {
		this.reservationRepository = reservationRepository;
		this.modelMapper = modelMapper;
		this.bookService = bookService;
		this.studentService = studentService;
	}

	@Transactional
	public StudentReservationResponse addStudentReservation(StudentReservationRequest request) throws Exception {
		var student = modelMapper.map(studentService.findById(request.getStudent().getId()), Student.class);
		var book = modelMapper.map(bookService.getById(request.getBook().getId()), Book.class);
		RuleResult result = StudentReservationRules.reserve(book, student);
		var reservation = modelMapper.map(request, StudentReservation.class);
		if (result.isDecision()) {
			throw new Exception(result.getMessage());
		}
		book.setReserved(true);
		return modelMapper.map(reservationRepository.save(reservation), StudentReservationResponse.class);
	}

	@Transactional
	public StudentReservationResponse deActiveReserve(Long id) {
		var reserve =reservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		reserve.setActive(false);
		return modelMapper.map(reserve, StudentReservationResponse.class);
	}
	public StudentReservationResponse delete(Long id) {
		var reserve = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		reservationRepository.deleteById(id);
		return modelMapper.map(reserve, StudentReservationResponse.class);
	}

	public StudentReservationResponse getById(Long id) {
		var studentReserved = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(studentReserved, StudentReservationResponse.class);
	}

	public List<StudentReservationResponse> getAll() {
		return reservationRepository.findAll().stream()
				.map(reserve -> modelMapper.map(reserve, StudentReservationResponse.class)).toList();
	}
}





