package com.example.library.utilities.rules;

import com.example.library.entity.Book;
import com.example.library.entity.BookType;
import com.example.library.entity.Stuff;

public class StuffReservationRules {

	public static RuleResult reserve(Book book,Stuff student) {
		if(!book.isBorrowed())
			return new RuleResult(false,"Book is in the library, you can borrow it", null);
		if(book.isReserved())
			return new RuleResult(false, "Book is already reserved", null);
		if(book.getBookType().equals(BookType.TIMED))
			return new RuleResult(false, "Timed book can not be reserved", null);
		return new RuleResult(true, "Book rule is success", null);
		
	}
}
