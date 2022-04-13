package com.example.library.utilities.rules;

import com.example.library.entity.Book;
import com.example.library.entity.BookType;
import com.example.library.entity.Student;

public class StudentReservationRules {

	public static RuleResult reserve(Book book,Student student) {
		if(!book.isBorrowed())
			return new RuleResult(false, "Book is in the library, you can borrow it.", null);
		if(book.isReserved())
			return new RuleResult(false, "Book is already in reserve", null);
		if(!(book.getBookType().equals(BookType.NORMAL)))
			return new RuleResult(false,"You can only reserve normal book", null);
		
		return new RuleResult(true,"Book rule is success", null);
	}
}
