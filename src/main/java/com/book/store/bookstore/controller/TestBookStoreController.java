package com.book.store.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestBookStoreController {
	
	@GetMapping("/bookstore")
	public String getInfor() {
		return "<h1> Jesus is Lord</h1>";
	}

}
