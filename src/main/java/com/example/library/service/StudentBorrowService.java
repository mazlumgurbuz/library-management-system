package com.example.library.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.BookRequest;
import com.example.library.dto.request.StudentBorrowRequest;
import com.example.library.dto.request.StudentUpdateRequest;
import com.example.library.dto.response.StudentBorrowResponse;
import com.example.library.dto.response.StudentReservationResponse;
import com.example.library.entity.Book;
import com.example.library.entity.Student;
import com.example.library.entity.StudentBorrow;
import com.example.library.repository.StudentBorrowRepository;
import com.example.library.utilities.rules.RuleResult;
import com.example.library.utilities.rules.StudentBorrowRules;

@Service
public class StudentBorrowService {

	private final StudentBorrowRepository studentBorrowRepository;
	private final BookService bookService;
	private final ModelMapper modelMapper;
	private final StudentService studentService;

	public StudentBorrowService(StudentBorrowRepository studentBorrowRepository, BookService bookService,
			ModelMapper modelMapper, StudentService studentService) {
		this.studentBorrowRepository = studentBorrowRepository;
		this.bookService = bookService;
		this.modelMapper = modelMapper;
		this.studentService = studentService;
	}

	@Transactional
	public StudentBorrowResponse addStudentBorrow(StudentBorrowRequest request) throws Exception {
		var student = modelMapper.map(studentService.findById(request.getStudent().getId()), Student.class);
		var book = modelMapper.map(bookService.getById(request.getBook().getId()), Book.class);
		RuleResult result = StudentBorrowRules.borrow(book, student);
		
		var borrow = modelMapper.map(request, StudentBorrow.class);
		if (!result.isDecision()) {
			throw new Exception(result.getMessage());
		}
		student.setBorrowedBookNum(student.getBorrowedBookNum() + 1);
		book.setBorrowed(true);
		borrow.setDeliveredDate(borrow.getDeliveredDate().plusDays(15));
		studentService.updateStudent(student.getId(), modelMapper.map(student, StudentUpdateRequest.class));
		bookService.update(book.getId(), modelMapper.map(book, BookRequest.class));
		return modelMapper.map(studentBorrowRepository.save(borrow), StudentBorrowResponse.class);
	}

	public StudentBorrowResponse getById(Long id) {
		var studentBorrow = studentBorrowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(studentBorrow, StudentBorrowResponse.class);

	}

	public List<StudentBorrowResponse> getAll() {
		return studentBorrowRepository.findAll().stream()
				.map(borrow -> modelMapper.map(borrow, StudentBorrowResponse.class)).toList();
	}
	@Transactional
	public StudentBorrowResponse deActiveBorrow(Long id) {
		var borrow = studentBorrowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		borrow.setActive(false);
		return modelMapper.map(borrow, StudentBorrowResponse.class);

	}
	@Transactional
	public StudentBorrowResponse delete(Long id) {
		var borrow=studentBorrowRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		studentBorrowRepository.deleteById(id);
		return modelMapper.map(borrow, StudentBorrowResponse.class);
				
	}
}




