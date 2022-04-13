package com.example.library.utilities.rules;

import com.example.library.entity.Book;
import com.example.library.entity.BookType;
import com.example.library.entity.Student;

public class StudentBorrowRules {

	public static RuleResult borrow(Book book, Student student) {
		if (student.getBorrowedBookNum() == 3 && student.isActive()) {
			return new RuleResult(false, "Already has 3 books", null);
		}
		if ((!student.isActive()) && student.getBorrowedBookNum() == 1)
			return new RuleResult(false, "Already has 1 books", null);
		if (!(book.getBookType().equals(BookType.NORMAL)))
			return new RuleResult(false, "Student can borrow only normal book.", null);
		return new RuleResult(true, "Borrow rule is success", null);
	}

}
