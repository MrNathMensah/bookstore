package com.book.store.bookstore.service;

import java.util.List;

import com.book.store.bookstore.exception.BookNotFoundException;
import com.book.store.bookstore.exception.IdNotFoundException;
import com.book.store.bookstore.pojo.Book;

public interface BookServiceInterface {
	
	boolean addBook(Book book);
	List<Book> getBookCategory(String category)throws BookNotFoundException;
	List<Book> getBookAuthor(String author)throws BookNotFoundException;
	Book getBookById(Integer id)throws IdNotFoundException;
}
