package com.example.library.utilities.rules;

import com.example.library.entity.Book;
import com.example.library.entity.BookType;
import com.example.library.entity.Stuff;
import com.example.library.entity.StuffType;

public class StuffBorrowRules {

	public static RuleResult borrow(Book book,Stuff stuff) {
		if(stuff.getStuffType().equals(StuffType.INSTRUCTOR)&&stuff.getBorrowedBookNum()==5)
			return new RuleResult(false,"Already has 5 books", null);
		if(stuff.getStuffType().equals(StuffType.OFFICER)&&stuff.getBorrowedBookNum()==3)
			return new RuleResult(false,"Already has 5 books", null);
		if(book.getBookType().equals(BookType.TIMED))
			return new RuleResult(false, "Timed books can not be borrowed", null);
		return new RuleResult(true, "Borrow rule is success", null);
	}
}
