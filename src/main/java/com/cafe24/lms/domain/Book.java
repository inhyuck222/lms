package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import test.BookCategory;

@Entity
@DiscriminatorValue("Book")
@PrimaryKeyJoinColumn(name="book_no")
public class Book extends Item {

	private String ISBN;

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + "]";
	}

}
