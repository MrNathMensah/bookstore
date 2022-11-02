package com.book.store.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.bookstore.dao.BookDao;
import com.book.store.bookstore.pojo.Book;
import com.book.store.bookstore.service.BookServiceInterfaceImplement;


@RestController
public class BookController {
	
	@Autowired
	private BookServiceInterfaceImplement service;
	
	@Autowired
	private BookDao dao;
	
	//Saving new Book into the database
	@PostMapping("/create_book")
	public ResponseEntity<?> addBook(@RequestBody Book book){
		
		boolean serviceBook = service.addBook(book);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this is to add book");
		if(!serviceBook) {
			dao.save(book);
			return ResponseEntity.ok("Booked is saved successfully");
		}
		
		return new ResponseEntity<String>("Book with this title already exist", HttpStatus.OK);
	}
	
	//Searching for book using the name of the Author
	@GetMapping("author/{author}")
	public ResponseEntity<?> getAuthor(@PathVariable("author") String author){
		
		List<Book> auth = service.getBookAuthor(author);
		
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "get the author");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(auth);
	}
	// Searching book based on Category
	@GetMapping("/category/{category}")
	public ResponseEntity<?> getCategory(@PathVariable("category") String category){
		
		List<Book> cate = service.getBookCategory(category);
		
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "get the category");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(cate);
	}
	
	//Finding book using ID
	@GetMapping("/book/{id}")
	public ResponseEntity<?> findBookById(@PathVariable Integer id){
		Book bookserve = service.getBookById(id);
		return ResponseEntity.ok(bookserve);
	}
	
	//Updating Book Records
	@PutMapping("/update/{id}")
	public Book updateBook(@RequestBody Book newBook, @PathVariable Integer id){
		return dao.findById(id)
				.map(book -> {
				book.setTitle(newBook.getTitle());
				book.setCategory(newBook.getCategory());
				book.setAuthor(newBook.getAuthor());
				
				return dao.save(book);
				})
				.orElseGet(()->{
					newBook.setBookId(id);
					return dao.save(newBook);
				});
		
	}
	// Deleting Book Records from the database
	@DeleteMapping("/deletebook/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
		dao.deleteById(id);
		return ResponseEntity.ok("Book is deleted successfully");
		
	}
	
	//Select all or Find all from the database
	@GetMapping("/allbooks")
	public List<Book> getAllBooks(){
		return dao.findAll();
	}
}
