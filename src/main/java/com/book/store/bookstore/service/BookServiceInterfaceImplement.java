package com.book.store.bookstore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.book.store.bookstore.dao.BookDao;
import com.book.store.bookstore.exception.BookNotFoundException;
import com.book.store.bookstore.exception.IdNotFoundException;
import com.book.store.bookstore.pojo.Book;
@Service
public class BookServiceInterfaceImplement implements BookServiceInterface {
	
	@Autowired
	private BookDao dao;
	
	@Override
	public boolean addBook(Book book) {
		
		Book add = new Book();
		add.setTitle(book.getTitle());
		
		Example<Book> bookExamp = Example.of(add);
		Optional<Book> option = dao.findOne(bookExamp);
		if(option.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public List<Book> getBookCategory(String category) {

		List<Book> cate = dao.findAll()
				.stream()
				.filter(book -> book.getCategory().equals(category))
				.collect(Collectors.toList());
		if(cate.isEmpty()) {
			throw new BookNotFoundException("Category not available");
		}
		return cate;
	}

	@Override
	public List<Book> getBookAuthor(String author) {
	
		List<Book> auth = dao.findAll()
				.stream()
				.filter(book -> book.getAuthor().equals(author))
				.collect(Collectors.toList());
		if(auth.isEmpty()) {
			throw new BookNotFoundException("Author not found");
		}
		return auth;
	}

	@Override
	public Book getBookById(Integer id) {
	
		return dao.findById(id)
				.stream()
				.filter(book -> book.getBookId()== id)
				.findAny()
				.orElseThrow(()-> new IdNotFoundException("Pls the id not found"));
	}

}
