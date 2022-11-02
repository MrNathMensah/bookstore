package com.book.store.bookstore.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
	
	@Id
	@GeneratedValue
	private Integer bookId;
	private String title;
	private String category;
	private String author;
	
	

}
