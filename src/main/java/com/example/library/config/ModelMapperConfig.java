package com.example.library.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	/*private static final Converter<AuthorRequest, Author>
	CONVERT_AUTHOR_REQUEST_TO_AUTHOR = (context) -> {
		
		var updatedAuthorReq = context.getSource();
		var author =
				context.getDestination();
		author.setFullName(updatedAuthorReq.getFullName());
		//author.setId(updatedAuthorReq.getId());
		return author;

	};
	private static final Converter<Author,AuthorResponse> 
	CONVERT_AUTHOR_TO_AUTHOR_RESPONSE = (context) -> {
		var response = new AuthorResponse();
				//context.getDestination();
		var author = context.getSource();
		response.setFullName(author.getFullName());
		response.setId(author.getId());
		return response;

	};*/
	/*private static final Converter<BorrowRequest,StudentBorrow> 
	CONVERT_BORROW_REQUEST_TO_BORROW = (context) -> {
		System.err.println("model mapper baslangic");
		var request = context.getSource();
	//	System.err.println("model mapper baslangic"+request.getBookId());
		var borrow =new StudentBorrow();
		
	//	System.err.println("model mapper baslangic"+borrow.getBook().getId());
		
		borrow.setBorrowedDate(request.getBorrowedDate());
		
		return borrow;

	};*/
//	private static final Converter<Student, StudentAddResponse> 
//		STUDENT_TO_STUDENT_RESPONSE = (context) -> {
//			var response = new StudentAddResponse();
//			var student = context.getSource();
//			response.setStudentId(student.getUserId());
//		return response;
//	};

	@Bean
	public ModelMapper mapper() {
		var mapper = new ModelMapper();

		//mapper.addConverter(CONVERT_AUTHOR_REQUEST_TO_AUTHOR, AuthorRequest.class, Author.class);
		//mapper.addConverter(CONVERT_AUTHOR_TO_AUTHOR_RESPONSE, Author.class, AuthorResponse.class);
//		mapper.addConverter(STUDENT_TO_STUDENT_RESPONSE, Student.class, StudentAddResponse.class);
		//mapper.addConverter(CONVERT_BORROW_REQUEST_TO_BORROW,BorrowRequest.class,StudentBorrow.class);
		return mapper;
	}
	
}

